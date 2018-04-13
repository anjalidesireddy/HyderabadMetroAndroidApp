package com.grepthor.hyderabadmetro.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.alorma.timeline.RoundTimelineView;
import com.grepthor.hyderabadmetro.R;

import java.util.List;

/**
 * Created by Grepthor_4 on 2/5/2018.
 */

public class SelectedStationAdapter extends BaseAdapter {

    Context context;
    List<String> list;
    public SelectedStationAdapter(Context context,List<String> list) {
        this.context=context;
        this.list=list;

    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.size();
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.my_view,viewGroup,false);


        TextView textView = (TextView) view.findViewById(R.id.textView);
        RoundTimelineView roundTimelineView=(RoundTimelineView)view.findViewById(R.id.timeline);

        for(int j = 0; j<list.size(); j++)
        {
            String str=list.get(i);
            textView.setText(str);
        }

        return view;
    }
}
