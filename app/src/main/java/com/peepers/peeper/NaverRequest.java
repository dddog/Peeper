package com.peepers.peeper;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by dddog on 2017/05/22.
 */

public class NaverRequest extends StringRequest {
    final static private String URL = "https://openapi.naver.com/v1/search/blog";
    final private String clientId = "8VL6tbiiIsc0cjp8OBzn";//애플리케이션 클라이언트 아이디값";
    final private String clientSecret = "X4PwnKJroA";//애플리케이션 클라이언트 시크릿값";

    public NaverRequest(String value, Response.Listener<String> listener) {
        super(Method.GET, URL+"?query="+value, listener, null);
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        Map<String, String>  params = new HashMap();
        params.put("X-Naver-Client-Id", clientId);
        params.put("X-Naver-Client-Secret", clientSecret);
        Log.d("GETHEADER =>", ""+ params);
        return params;
    }
}
