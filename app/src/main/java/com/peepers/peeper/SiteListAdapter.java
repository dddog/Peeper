package com.peepers.peeper;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by dddog on 2017/05/22.
 */

public class SiteListAdapter extends BaseAdapter {

    private Context context;
    private List<SiteDto> siteList;

    public SiteListAdapter(Context context, List<SiteDto> siteList) {
        this.context = context;
        this.siteList = siteList;
    }

    @Override
    public int getCount() {
        return siteList.size();
    }

    @Override
    public Object getItem(int position) {
        return siteList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = View.inflate(context, R.layout.site_list, null);
        ImageView imageView = (ImageView)v.findViewById(R.id.siteImageView);
        TextView textView = (TextView) v.findViewById(R.id.siteTotalTextView);

        imageView.setImageResource(siteList.get(position).getLogoID());
        textView.setText(""+siteList.get(position).getTotalCount());

        return v;
    }
}
