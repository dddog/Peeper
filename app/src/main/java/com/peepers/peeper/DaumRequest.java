package com.peepers.peeper;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

/**
 * Created by dddog on 2017/05/19.
 */

public class DaumRequest extends StringRequest {
    final static private String URL = "https://apis.daum.net/search/web";
    final static private String APIKEY = "69091a7314ada1e9f1025f4f80611b6c";

    public DaumRequest(String value, Response.Listener<String> listener) {
        super(Method.GET, URL+"?q="+value+"&apikey="+APIKEY+"&output=json", listener, null);
    }
}
