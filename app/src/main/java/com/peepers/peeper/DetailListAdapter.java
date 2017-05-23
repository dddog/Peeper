package com.peepers.peeper;

import android.content.Context;
import android.text.Html;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by dddog on 2017/05/19.
 */

public class DetailListAdapter extends BaseAdapter {

    private Context context;
    private List<DetailDto> detailList;

    public DetailListAdapter(Context context, List<DetailDto> detailList) {
        this.context = context;
        this.detailList = detailList;
    }

    @Override
    public int getCount() {
        return detailList.size();
    }

    @Override
    public Object getItem(int i) {
        return detailList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = View.inflate(context, R.layout.detail, null);
        TextView titleText = (TextView) v.findViewById(R.id.titleText);
        TextView descriptionText = (TextView) v.findViewById(R.id.descriptionText);
        TextView linkText = (TextView) v.findViewById(R.id.linkText);
        TextView pubDateText = (TextView) v.findViewById(R.id.pubDateText);

        titleText.setText(Html.fromHtml(detailList.get(i).getTitle()));
        descriptionText.setText(Html.fromHtml(detailList.get(i).getDescription()));
        linkText.setText(detailList.get(i).getLink());
        pubDateText.setText(detailList.get(i).getPubDate());

        v.setTag(detailList.get(i).getTitle());
        return v;
    }
}
