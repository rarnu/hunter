package com.rarnu.hunter.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.rarnu.devlib.base.adapter.BaseAdapter;
import com.rarnu.hunter.R;
import com.rarnu.hunter.api.TimelineClass;

import java.util.List;

public class TimelineAdapter extends BaseAdapter<TimelineClass> {

    public TimelineAdapter(Context context, List<TimelineClass> list) {
        super(context, list);
    }

    @Override
    public String getValueText(TimelineClass item) {
        return null;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            v = inflater.inflate(R.layout.item_timeline, parent, false);
        }
        TimelineHolder holder = (TimelineHolder) v.getTag();
        if (holder == null) {
            holder = new TimelineHolder();
            holder.tvTitle = (TextView) v.findViewById(R.id.tvTitle);
            holder.tvPublishDate = (TextView) v.findViewById(R.id.tvPublishDate);
            v.setTag(holder);
        }
        TimelineClass item = list.get(position);
        if (item != null) {
            holder.tvTitle.setText(item.comment);
            holder.tvPublishDate.setText(item.publishDate);
        }
        return v;
    }
}
