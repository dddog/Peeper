package com.peepers.peeper;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.apache.commons.lang3.StringEscapeUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SiteListActivity extends AppCompatActivity {

    private TextView searchTextView;
    private Button searchButton;

    private ListView siteListView;
    private SiteListAdapter siteListAdapter;
    private List<SiteDto> siteList;

    private ArrayList<DetailDto> daumDetailList;
    private ArrayList<DetailDto> naverDetailList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_site_list);

        siteListView = (ListView) findViewById(R.id.siteListView);
        siteList = new ArrayList<SiteDto>();
        siteListAdapter = new SiteListAdapter(getApplicationContext(), siteList);
        siteListView.setAdapter(siteListAdapter);

        searchTextView = (TextView) findViewById(R.id.searchValText);
        searchButton = (Button) findViewById(R.id.searchButton);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RequestQueue queue = Volley.newRequestQueue(SiteListActivity.this);

                if( searchTextView.getText().toString().equals("") ) {
                    Toast.makeText(SiteListActivity.this, "검색어를 입력하세요", Toast.LENGTH_SHORT).show();
                } else {
                    siteList.clear();

                    String searchVal = searchTextView.getText().toString();

                    daumDetailList = new ArrayList<DetailDto>();

                    Response.Listener<String> daumResponsListener = new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject jsonObject = new JSONObject(response).getJSONObject("channel");
                                Log.d("Daum JSON", jsonObject.toString());
                                SiteDto siteDto = new SiteDto(R.drawable.daum_175x175bb, "Daum 웹", jsonObject.getString("totalCount"));

                                siteList.add(siteDto);

                                JSONArray jsonArray = jsonObject.getJSONArray("item");
                                int count = 0;
                                while (count < jsonArray.length()) {
                                    JSONObject object = jsonArray.getJSONObject(count);

                                    daumDetailList.add(new DetailDto(StringEscapeUtils.unescapeHtml4(object.getString("title")).toString(),
                                            StringEscapeUtils.unescapeHtml4(object.getString("description")).toString(),
                                            object.getString("link"),
                                            object.getString("pubDate")));
                                    count++;
                                }

                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            siteListAdapter.notifyDataSetChanged();
                        }
                    };
                    // Daum api
                    DaumRequest daumRequest = new DaumRequest(searchVal, daumResponsListener);
                    queue.add(daumRequest);

                    naverDetailList = new ArrayList<DetailDto>();
                    Response.Listener<String> naverResponsListener = new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                Log.d("Naver JSON", jsonObject.toString());
                                SiteDto siteDto = new SiteDto(R.drawable.naver_blog_175x175bb, "네이버 블로그", jsonObject.getString("total"));

                                siteList.add(siteDto);

                                JSONArray jsonItem = jsonObject.getJSONArray("items");
                                for (int i = 0; i < jsonItem.length(); i++) {
                                    JSONObject object = jsonItem.getJSONObject(i);
                                    naverDetailList.add(new DetailDto(StringEscapeUtils.unescapeHtml4(object.getString("title")).toString(),
                                            StringEscapeUtils.unescapeHtml4(object.getString("description")).toString(),
                                            object.getString("link"),
                                            object.getString("postdate")));
                                }

                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            siteListAdapter.notifyDataSetChanged();
                        }
                    };
                    // Naver api
                    NaverRequest naverRequest = new NaverRequest(searchVal, naverResponsListener);
                    queue.add(naverRequest);

                    // Html parse
                    new BackgroundTask().execute(searchTextView.getText().toString());
                }

            }
        });

        siteListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                SiteDto siteDto = siteList.get(position);

                if( siteDto.getTotalCount().equals("") || siteDto.getTotalCount().equals("0") ) {
                    Toast.makeText(SiteListActivity.this, "검색된 결과가 없습니다.", Toast.LENGTH_LONG).show();
                } else {
                    Intent intent = new Intent(SiteListActivity.this, MainActivity.class);

                    if( R.drawable.daum_175x175bb == siteList.get(position).getLogoID() ) {
                        intent.putParcelableArrayListExtra("detailList", daumDetailList);
                    } else if( R.drawable.naver_blog_175x175bb == siteList.get(position).getLogoID() ) {
                        intent.putParcelableArrayListExtra("detailList", naverDetailList);
                    }
                    intent.putExtra("siteName", siteDto.getSiteName());
                    SiteListActivity.this.startActivity(intent);
                }
            }
        });
    }

    class BackgroundTask extends AsyncTask<String, Void, SiteDto> {

        @Override
        protected SiteDto doInBackground(String... params) {
            int paramsCount = params.length;

            if( paramsCount > 0 ) {
                GoogleTask googleTask = new GoogleTask(params[0]);
                return googleTask.getSiteDto();
            } else {
                return null;
            }
        }

        @Override
        public void onProgressUpdate(Void... values) {
            super.onProgressUpdate();
        }

        @Override
        public void onPostExecute(SiteDto siteDto) {
            if( !siteDto.getTotalCount().equals("") ) {
                siteList.add(siteDto);
            }
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(searchTextView.getWindowToken(), 0);
        }
    }
}
