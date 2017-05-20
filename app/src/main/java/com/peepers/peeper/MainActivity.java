package com.peepers.peeper;

import android.app.Application;
import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.HttpStack;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.lang.reflect.Method;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private static String TAG = MainActivity.class.getSimpleName();

    final Context context = this;
    ListAdapter listAdapter;
    ListView listView;

    private Button searchButton;
    private TextView searchText;

    private String clientId = "8VL6tbiiIsc0cjp8OBzn";//애플리케이션 클라이언트 아이디값";
    private String clientSecret = "X4PwnKJroA";//애플리케이션 클라이언트 시크릿값";

    static String keyword = "";

    RequestQueue requestQueue;
    ArrayList<ListModel> listModels;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.searchListView);
        listModels = new ArrayList<ListModel>();
        //listAdapter = new ListAdapter(MainActivity.this, listModels);
        listView.setAdapter(listAdapter);


        requestQueue = Volley.newRequestQueue(this);
        searchText = (TextView) findViewById(R.id.searchTv);
        searchButton = (Button) findViewById(R.id.searchBtn);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                keyword = searchText.getText().toString();
                customHttpHeaders();
            }
        });
    }

    public void customHttpHeaders() {
        RequestQueue queue = Volley.newRequestQueue(this);
        String NAVERURL = "https://openapi.naver.com/v1/search/blog?query="+ keyword;
        JsonObjectRequest postRequest = new JsonObjectRequest(Request.Method.GET, NAVERURL, null,
                new Response.Listener<JSONObject>()
                {
                    @Override
                    public void onResponse(JSONObject response) {
                        // response
                        Log.d("Response =>", ""+ response);
                        parseJSON(response);

                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO Auto-generated method stub
                        Log.d("ERROR_RESPONSE =>", error.toString());
                    }
                }
        ) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String>  params = new HashMap();
                params.put("X-Naver-Client-Id", clientId);
                params.put("X-Naver-Client-Secret", clientSecret);
                Log.d("GETHEADER =>", ""+ params);
                return params;
            }
        };
        queue.add(postRequest);
        Log.d("QUEUE->", ""+queue);
        Log.d("POSTREQUEST->", ""+postRequest);
    }


    public void parseJSON(JSONObject json){
        try{
            Log.d("PARSEJJSON값확인 =>", ""+json);
            JSONArray jsonItem = json.getJSONArray("items");
                for (int i = 0; i < jsonItem.length(); i++) {
                    JSONObject item = jsonItem.getJSONObject(i);
                    Log.d("item확인=>", ""+item);

                    ListModel listModel = new ListModel();
                    listModel.setTitle(item.getString("title"));
                    listModel.setLink(item.getString("link"));
                    listModel.setDescription(item.getString("description"));
                    listModel.setBloggername(item.getString("bloggername"));
                    listModels.add(listModel);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

/*
                        try{
                            JSONObject channel = response.getJSONObject("channel");
                            JSONArray items = channel.getJSONArray("items");

                            for(int i=0; i<items.length(); i++){
                                JSONObject item = items.getJSONObject(i);
                                ListModel listModel = new ListModel();
                                listModel.setTitle(item.optString("title"));
                                listModel.setLink(item.optString("link"));
                                listModel.setDescription(item.optString("description"));
                                listModel.setBloggername(item.optString("bloggername"));
                                listModels.add(listModel);
                            }
                        }catch (Exception e){
                            e.printStackTrace();
                        }
 */