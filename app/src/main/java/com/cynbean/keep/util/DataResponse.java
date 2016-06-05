package com.cynbean.keep.util;

import java.util.List;
import java.util.Map;

/**
 * Created by tcf24 on 2016/5/30.
 */
public interface DataResponse<T> {
    List<Map<String, Object>> onData(T data);
}
