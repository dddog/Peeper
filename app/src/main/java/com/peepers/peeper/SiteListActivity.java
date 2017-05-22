package com.peepers.peeper;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
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

    private ArrayList<DaumDto> daumList;

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
                if( searchTextView.equals("") ) {
                    Toast toast = Toast.makeText(SiteListActivity.this, "검색어를 입력하세요", Toast.LENGTH_SHORT);
                    toast.show();
                } else {
                    siteList.clear();

                    String searchVal = searchTextView.getText().toString();

                    daumList = new ArrayList<DaumDto>();

                    Response.Listener<String> responsListener = new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject jsonObject = new JSONObject(response).getJSONObject("channel");

                                SiteDto siteDto = new SiteDto(R.drawable.daum_logo, Integer.parseInt(jsonObject.getString("totalCount")));

                                siteList.add(siteDto);

                                JSONArray jsonArray = jsonObject.getJSONArray("item");
                                int count = 0;
                                while (count < jsonArray.length()) {
                                    JSONObject object = jsonArray.getJSONObject(count);

                                    daumList.add(new DaumDto(StringEscapeUtils.unescapeHtml4(object.getString("title")).toString(),
                                            StringEscapeUtils.unescapeHtml4(object.getString("description")).toString(),
                                            object.getString("link"),
                                            object.getString("pubDate")));
                                    count++;
                                }

                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    };
                    DaumRequest daumRequest = new DaumRequest(searchVal, responsListener);
                    RequestQueue queue = Volley.newRequestQueue(SiteListActivity.this);
                    queue.add(daumRequest);

                }
            }
        });

        siteListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(SiteListActivity.this, MainActivity.class);
                intent.putParcelableArrayListExtra("daumList", daumList);
                SiteListActivity.this.startActivity(intent);
            }
        });
    }
}
