package com.cynbean.keep.game;

import android.content.Context;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.GridLayout;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * Created by 9507 on 2015/7/30.
 */
public class GameView extends GridLayout{

    public GameView(Context context) {
        super(context);
        initGameView();
    }


    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initGameView();
    }

    public GameView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initGameView();
    }

    private void initGameView() {
        setColumnCount(4);
        setBackgroundColor(0xffbbada0);

        setOnTouchListener(new OnTouchListener() {

            public float startX, startY, offsetX, offsetY;

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        startX = event.getX();
                        startY = event.getY();
                        break;
                    case MotionEvent.ACTION_UP:
                        offsetX = event.getX() - startX;
                        offsetY = event.getY() - startY;
                        break;
                }
                if (Math.abs(offsetX) > Math.abs(offsetY)) {
                    if (offsetX < -5) {
                        swipeLeft();
                    }
                    if (offsetX > 5) {
                        swipeRight();
                    }
                } else {
                    if (offsetY < -5) {
                        swipeUp();
                    }
                    if (offsetY > 5) {
                        swipeDown();
                    }
                }

                return true;
            }
        });
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        int cardWidth = (Math.min(w,h)-10) / 4;

        addCard(cardWidth, cardWidth);

        startGame();
    }

    private void startGame(){

        //GameActivity.getInstance().clearScore();

        for (int y = 0;y < 4;y++){
            for(int x = 0;x < 4;x++){
                cardsMap[x][y].setNum(0);
            }
        }

        addRandomNum();
        addRandomNum();
    }

    private void addCard(int cardWidth,int cardHeight) {
        Card card;
        for (int y = 0; y < 4 ;y++){
            for (int x = 0 ;x < 4 ;x++){
                card = new Card(getContext());
                card.setNum(0);
                addView(card, cardWidth, cardHeight);

                cardsMap[x][y] = card;

            }
        }
    }

    private void addRandomNum(){

        emptyPoints.clear();
        for (int y = 0;y < 4;y++){
            for (int x = 0; x < 4;x++){

                if(cardsMap[x][y].getNum() <= 0)
                emptyPoints.add(new Point(x,y));
            }
        }

        Point p = emptyPoints.remove((int)(Math.random()*emptyPoints.size()));
        cardsMap[p.x][p.y].setNum(Math.random()>0.1? 2:4);

    }

    public void swipeLeft(){

        boolean merge = false;
        for ( int y = 0;y < 4;y++){
            for ( int x = 0;x < 4;x++){

                for (int x1 = x+1;x1 < 4;x1++){
                    if (cardsMap[x1][y].getNum() > 0) {

                        if (cardsMap[x][y].getNum() <= 0) {
                            cardsMap[x][y].setNum(cardsMap[x1][y].getNum());
                            cardsMap[x1][y].setNum(0);
                            merge = true;
                            x--;

                        } else if (cardsMap[x][y].equals(cardsMap[x1][y])) {
                            cardsMap[x][y].setNum(cardsMap[x][y].getNum() * 2);
                            cardsMap[x1][y].setNum(0);
                            GameActivity.getInstance().addScore(cardsMap[x][y].getNum());
                            merge = true;
                        }
                            break;
                    }
                }

            }
        }
        if (merge){
            addRandomNum();
        }
    }
    public void swipeRight(){

        boolean merge = false;
        for ( int y = 0;y < 4;y++){
            for ( int x = 3;x >= 0;x--){

                for (int x1 = x-1;x1 >= 0;x1--){
                    if (cardsMap[x1][y].getNum() > 0) {

                        if (cardsMap[x][y].getNum() <= 0) {
                            cardsMap[x][y].setNum(cardsMap[x1][y].getNum());
                            cardsMap[x1][y].setNum(0);
                            merge = true;
                            x++;
                        } else if (cardsMap[x][y].equals(cardsMap[x1][y])) {
                            cardsMap[x][y].setNum(cardsMap[x][y].getNum() * 2);
                            cardsMap[x1][y].setNum(0);
                            GameActivity.getInstance().addScore(cardsMap[x][y].getNum());
                            merge = true;
                        }
                            break;
                    }
                }

            }
        }
        if (merge){
            addRandomNum();
        }
    }
    public void swipeUp(){
        boolean merge = false;
        for ( int x = 0;x < 4;x++){
            for ( int y = 0;y < 4;y++){

                for (int y1 = y+1;y1 < 4;y1++){
                    if (cardsMap[x][y1].getNum() > 0) {

                        if (cardsMap[x][y].getNum() <= 0) {
                            cardsMap[x][y].setNum(cardsMap[x][y1].getNum());
                            cardsMap[x][y1].setNum(0);
                            merge = true;
                            y--;

                        } else if (cardsMap[x][y].equals(cardsMap[x][y1])) {
                            cardsMap[x][y].setNum(cardsMap[x][y].getNum() * 2);
                            cardsMap[x][y1].setNum(0);
                            GameActivity.getInstance().addScore(cardsMap[x][y].getNum());
                            merge = true;
                        }
                            break;
                    }
                }

            }
        }
        if (merge){
            addRandomNum();
        }
    }
    public void swipeDown(){
        boolean merge = false;
        for ( int x = 0;x < 4;x++){
            for ( int y = 3;y >= 0;y--){

                for (int y1 = y-1;y1 >= 0;y1--){
                    if (cardsMap[x][y1].getNum() > 0) {

                        if (cardsMap[x][y].getNum() <= 0) {
                            cardsMap[x][y].setNum(cardsMap[x][y1].getNum());
                            cardsMap[x][y1].setNum(0);
                            merge = true;
                            y++;
                        } else if (cardsMap[x][y].equals(cardsMap[x][y1])) {
                            cardsMap[x][y].setNum(cardsMap[x][y].getNum() * 2);
                            cardsMap[x][y1].setNum(0);
                            GameActivity.getInstance().addScore(cardsMap[x][y].getNum());
                            merge = true;
                        }
                            break;
                    }
                }

            }
        }
        if (merge){
            addRandomNum();
        }
    }

    private Card[][] cardsMap = new Card[4][4];
    private List<Point> emptyPoints = new ArrayList<Point>();
}
