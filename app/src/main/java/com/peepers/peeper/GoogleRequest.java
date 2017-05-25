package com.peepers.peeper;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

/**
 * Created by m_yg on 2017-05-25.
 */

public class GoogleRequest extends StringRequest{
    final static private String URL = "https://www.google.com/";
    final private String query = "?q";
    final private int startValue = 0;

    public GoogleRequest(String value, Response.Listener<String> listener) {
        super(Method.GET, URL+"?q="+value, listener, null);
    }
}
