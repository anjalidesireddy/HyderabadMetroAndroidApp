package com.grepthor.hyderabadmetro.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.grepthor.hyderabadmetro.ActivityEventdetail;
import com.grepthor.hyderabadmetro.R;
import com.grepthor.hyderabadmetro.objectclass.Event;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class BusinessAdapter1 extends BaseAdapter {
    private static LayoutInflater inflater = null;
    String[] result;
    Context context;
    int[] imageId;
    private ArrayList<Event> arrayList;
    private int lastPosition = -1;

    public BusinessAdapter1(Context context, ArrayList<Event> prgmNameList) {
        // TODO Auto-generated constructor stub
        this.arrayList = prgmNameList;
        this.context = context;
        //imageId=prgmImages;
        inflater = (LayoutInflater) context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        Holder holder = new Holder();
        View rowView;
        rowView = inflater.inflate(R.layout.event_item_list, null);

        Animation animation = AnimationUtils.loadAnimation(context, (position > lastPosition) ? R.anim.up_from_bottom : R.anim.down_from_top);
        rowView.startAnimation(animation);
        lastPosition = position;

        holder.tv = (TextView) rowView.findViewById(R.id.textView1);
        holder.img = (ImageView) rowView.findViewById(R.id.imageView1);
        holder.textview= (TextView) rowView.findViewById(R.id.textview);
        holder.tvenddate= (TextView) rowView.findViewById(R.id.tvenddate);


        holder.tv.setText(arrayList.get(position).EVENT_NAME);


        holder.tvenddate.setText(arrayList.get(position).END_DATE);
//        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
//        int width = metrics.widthPixels;
//        holder.img.getLayoutParams().width = width/2;
//        holder.img.getLayoutParams().height = width / 2;

//        String splitted[] = arrayList.get(position).video_url.split("=",2);
//        System.out.println(splitted[0]);
//        System.out.println(splitted[1]);
        holder.textview.setText(arrayList.get(position).START_DATE);

        Picasso.with(context).load( arrayList.get(position).IMAGE_1)
                .placeholder(R.drawable.welcomeimg)
                .into(holder.img);

        holder.img.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent i=new Intent(context,ActivityEventdetail.class);



                i.putExtra("EVENT_NAME", arrayList.get(position).EVENT_NAME);
                i.putExtra("IMAGE_1", arrayList.get(position).IMAGE_1);
                i.putExtra("IMAGE_2", arrayList.get(position).IMAGE_2);
                i.putExtra("IMAGE_3", arrayList.get(position).IMAGE_3);
                i.putExtra("IMAGE_4", arrayList.get(position).IMAGE_4);

                i.putExtra("IMAGE_5", arrayList.get(position).IMAGE_5);
                i.putExtra("START_DATE", arrayList.get(position).START_DATE);
                i.putExtra("END_DATE", arrayList.get(position).END_DATE);
                i.putExtra("DESCRIPTION", arrayList.get(position).DESCRIPTION);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                v.getContext().startActivity(i);
            }
        });



        return rowView;
    }

    public class Holder {
        TextView tv;
        ImageView img;
        TextView textview,tvenddate;
    }

}
