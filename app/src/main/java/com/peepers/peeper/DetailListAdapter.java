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
    private List<DetailDto> daumList;

    public DetailListAdapter(Context context, List<DetailDto> daumList) {
        this.context = context;
        this.daumList = daumList;
    }

    @Override
    public int getCount() {
        return daumList.size();
    }

    @Override
    public Object getItem(int i) {
        return daumList.get(i);
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

        titleText.setText(Html.fromHtml(daumList.get(i).getTitle()));
        descriptionText.setText(Html.fromHtml(daumList.get(i).getDescription()));
        linkText.setText(daumList.get(i).getLink());
        pubDateText.setText(daumList.get(i).getPubDate());

        v.setTag(daumList.get(i).getTitle());
        return v;
    }
}
