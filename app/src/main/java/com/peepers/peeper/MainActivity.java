package com.peepers.peeper;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public String name = "";
    public TextView textView;

    private ListView daumListView;
    private DaumListAdapter adapter;
    private List<DaumDto> daumList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();

        daumListView = (ListView) findViewById(R.id.daumListView);
        daumList = new ArrayList<DaumDto>();


        daumList = intent.getParcelableArrayListExtra("daumList");

        adapter = new DaumListAdapter(getApplicationContext(), daumList);
        daumListView.setAdapter(adapter);



//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                daumList.clear();
//                String searchVal = textView.getText().toString();
//
//                Response.Listener<String> responsListener = new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        try {
//                            JSONObject jsonObject = new JSONObject(response);
//                            JSONArray jsonArray = jsonObject.getJSONObject("channel").getJSONArray("item");
//                            int count = 0;
//                            while (count < jsonArray.length()) {
//                                JSONObject object = jsonArray.getJSONObject(count);
//
//                                daumList.add(new DaumDto(Html.fromHtml(StringEscapeUtils.unescapeHtml4(object.getString("title"))).toString(),
//                                        StringEscapeUtils.unescapeHtml4(object.getString("description")).toString(),
//                                        object.getString("link"),
//                                        object.getString("pubDate")));
//                                count++;
//                            }
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }
//                    }
//                };
//                DaumRequest daumRequest = new DaumRequest(searchVal, responsListener);
//                RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
//                queue.add(daumRequest);
//            }
//        });

//        RequestQueue requestQueue;
//        RequestQueue queue = Volley.newRequestQueue(this);
//        String URL = "https://apis.daum.net/search/web?q=신기원&apikey=69091a7314ada1e9f1025f4f80611b6c&output=json";
//
//        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET, URL, null, new Response.Listener<JSONObject>() {
//            @Override
//            public void onResponse(JSONObject response) {
//                Log.i("Response", response.toString());
//                textView.setText(response.toString());
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Log.d("error", error.toString());
//            }
//        });
//
//        queue.add(jsonObjReq);
//        Log.i("getJsonData end", "end");
//
//
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Log.i("msg", "button click");
//
//            }
//        });
    }
}
