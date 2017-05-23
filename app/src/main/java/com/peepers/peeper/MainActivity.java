package com.peepers.peeper;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public String name = "";
    public TextView textView;

    private ListView detailListView;
    private DetailListAdapter adapter;
    private List<DetailDto> detailList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();

        detailListView = (ListView) findViewById(R.id.detailListView);
        detailList = new ArrayList<DetailDto>();


        detailList = intent.getParcelableArrayListExtra("detailList");

        adapter = new DetailListAdapter(getApplicationContext(), detailList);
        detailListView.setAdapter(adapter);

        TextView siteNameTextView = (TextView) findViewById(R.id.siteNameTextView);
        siteNameTextView.setText(intent.getStringExtra("siteName"));

        detailListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                DetailDto detailDto = detailList.get(position);
                Intent intent = new Intent(MainActivity.this, WebActivity.class);
                intent.putExtra("url", detailDto.getLink());

                MainActivity.this.startActivity(intent);
            }
        });
    }
}
