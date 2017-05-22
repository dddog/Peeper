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

    private ListView detalListView;
    private DetailListAdapter adapter;
    private List<DetailDto> detailList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();

        detalListView = (ListView) findViewById(R.id.detailListView);
        detailList = new ArrayList<DetailDto>();


        detailList = intent.getParcelableArrayListExtra("detailList");

        adapter = new DetailListAdapter(getApplicationContext(), detailList);
        detalListView.setAdapter(adapter);

        TextView siteNameTextView = (TextView) findViewById(R.id.siteNameTextView);
        siteNameTextView.setText(intent.getStringExtra("siteName"));
    }
}
