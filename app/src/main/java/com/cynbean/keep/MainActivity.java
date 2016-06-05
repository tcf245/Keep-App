package com.cynbean.keep;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.util.Log;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.cynbean.keep.book.BooksFragment;
import com.cynbean.keep.db.TokenDao;
import com.cynbean.keep.fragment.NotesFragment;
import com.cynbean.keep.game.GameActivity;
import com.cynbean.keep.util.BaseApplication;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private Toolbar mToolbar;
    private TokenDao tokenDao;
    private NavigationView navigationView;
    private BaseApplication application ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        application = BaseApplication.getInstance();

        initDrawer();

        Log.i("GameActivity : " ,"GameActivity start.");
        tokenDao = new TokenDao(this);
        String token = tokenDao.getToken();
        if(token.equals("") || token == null){
            Toast.makeText(MainActivity.this, "获取Token失败，请重新登录", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivityForResult(intent, 0);
        }else {
            Log.i("登录成功  token ==>",token);
            application.setToken(token);
            switchToNote();
        }

        /** token写入文件 */
//        try {
////          token = FileUtils.readFileToString(new File(Constant.TOKEN_FILE));
//            token = FileUtil.readFile(Constant.TOKEN_FILE,GameActivity.this);
//            Log.d("GameActivity  token",token);
//            tvInfo.setText(token);
//            if(!token.equals("") && token != null){
//                application.setToken(token);
//                switchToNote();
//            }else{
//                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
//                startActivityForResult(intent, 0);
//            }
//
//        } catch (IOException e) {
//            e.printStackTrace();
//            Toast.makeText(GameActivity.this, "获取token失败，请重新登录", Toast.LENGTH_SHORT).show();
//            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
//            startActivityForResult(intent, 0);
//        }
    }

    /**
     * 启动NoteFragment
     */
    private void switchToNote() {
        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, new NotesFragment()).commit();
        mToolbar.setTitle("Notes");
    }

    /**
     * 启动BooksFragment
     */
    private void switchToBook() {
        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, new BooksFragment()).commit();
        mToolbar.setTitle("Books");
    }

    /**
     * 初始化抽屉数据
     */
    private void initDrawer() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        Menu menu = navigationView.getMenu();

        // TODO 为了使文件夹列表能够全部取消选中
        MenuItem mItemNoFolder = menu.add(R.id.group_folder, Menu.NONE, Menu.NONE,
                getResources().getString(R.string.no_folder)).setVisible(false);
        addFolderItem(menu);

        menu.setGroupCheckable(R.id.group_folder, true, true);
//        menu.setGroupCheckable(R.id.group_tag, true, false);
//        menu.setGroupCheckable(R.id.group_location, true, false);
    }

    /**
     * 添加抽屉文件夹项
     */
    private void addFolderItem(Menu menu) {

        int[] folderColors = {R.color.red, R.color.green,
                R.color.yello, R.color.blue};
        String[] colorNames = {"RED","GREEN","YELLOW","BLUE"};

        for (int i = 0; i < 4; i++) {
            MenuItem item = menu.add(R.id.group_folder, Menu.NONE, Menu.NONE,
                    colorNames[i])
                    .setCheckable(true).setIcon(R.drawable.ic_folder_white_24dp);
            item.setIcon(item.getIcon().mutate());
            item.getIcon().setColorFilter(getResources().getColor(folderColors[i]), PorterDuff.Mode.MULTIPLY);
        }
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        if (id == R.id.tags){
            Toast.makeText(this, "Tags", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, TagsActivity.class);
            startActivity(intent);
            return true;
        }
//        if(id =  R.id.tags){
//            Toast.makeText(this, "Tags", Toast.LENGTH_SHORT).show();
//            Intent intent = new Intent(this, TagsActivity.class);
//            startActivity(intent);
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }

    private long exitTime = 0;

    public void doExitApp() {
        if ((System.currentTimeMillis() - exitTime) > 2000) {
            Toast.makeText(this, R.string.press_again_exit_app, Toast.LENGTH_SHORT).show();
            exitTime = System.currentTimeMillis();
        } else {
            finish();
        }
    }

    /**
     * 根据选中的文件夹来列出相关条目
     * 单选
     */
    private void updateRecyclerViewByFolder(MenuItem menuItem) {

        int id = menuItem.getItemId();


//        // 取消选中其他所有的项
//        Menu menu = navigationView.getMenu();
//        for (int i = 3; i < menu.size(); i++) {
//            menu.getItem(i).setChecked(false);
//        }
//        menu.findItem(R.id.item_all).setChecked(true);
//
//
//        menuItem.setChecked(true);
//        getEntriesByFolder(menuItem.getTitle().toString());
//        updateRecyclerView();
//        mDrawerLayout.closeDrawers();

    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        int groupId = item.getGroupId();
        if (groupId == R.id.group_folder){
            updateRecyclerViewByFolder(item);
        }

        if (id == R.id.item_all) {
            switchToNote();
        }else if (id == R.id.nav_books){
            switchToBook();
        }
//        else if (id == R.id.item_archive) {
//
//        }
        else if (id == R.id.nav_changepass) {
            Intent intent = new Intent(getApplicationContext(), PasswordActivity.class);
            startActivity(intent);
        }else if (id == R.id.nav_logout) {
            application.setToken("");
            tokenDao.delete();
            Toast.makeText(MainActivity.this, "已退出，请重新登录。", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivityForResult(intent, 0);
        } else if(id == R.id.nav_game){
            Toast.makeText(MainActivity.this, "欢迎进入。", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getApplicationContext(), GameActivity.class);
            startActivityForResult(intent, 0);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
