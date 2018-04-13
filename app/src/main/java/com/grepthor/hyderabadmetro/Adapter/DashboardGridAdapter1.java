package com.grepthor.hyderabadmetro.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.grepthor.hyderabadmetro.ActivityRoute;
import com.grepthor.hyderabadmetro.Helper.ConnectionDetector;
import com.grepthor.hyderabadmetro.Helper.SharedPreferenceManager;
import com.grepthor.hyderabadmetro.NearBy.MapsActivity;
import com.grepthor.hyderabadmetro.R;
import com.grepthor.hyderabadmetro.Route.RouteActivity;
import com.grepthor.hyderabadmetro.Train.TrainActivity;
import com.grepthor.hyderabadmetro.constant.IErrors;
import com.grepthor.hyderabadmetro.object.GridViewRowItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by user on 3/8/16.
 */
public class DashboardGridAdapter1 extends BaseAdapter {

    private static LayoutInflater inflater = null;
    Context context;
    private Dialog dialog, dialog1;
    public static int i = 0;
    int pos;
    private SharedPreferenceManager sharedPrefMgr;
    private String stringCountryIdNew, stringCountryNameNew;
    private List<GridViewRowItem> gridViewRowItemList;
    private ArrayList<GridViewRowItem> arraylist;

    public DashboardGridAdapter1(Context mainActivity, List<GridViewRowItem> gridViewRowItemList) {
        // TODO Auto-generated constructor stub
        this.gridViewRowItemList = gridViewRowItemList;
        context = mainActivity;
        inflater = (LayoutInflater) context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.arraylist = new ArrayList<GridViewRowItem>();
        this.arraylist.addAll(gridViewRowItemList);

    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return gridViewRowItemList.size();
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
        final Holder holder = new Holder();
        pos = position;
        final View rowView;
        sharedPrefMgr = new SharedPreferenceManager(context);
        sharedPrefMgr.connectDB();

        sharedPrefMgr.closeDB();
        rowView = inflater.inflate(R.layout.dashboard_item_list, null);

        holder.tv = (TextView) rowView.findViewById(R.id.textView1);
        holder.img = (ImageView) rowView.findViewById(R.id.imageView1);



        holder.tv.setText(gridViewRowItemList.get(position).getTitle());
        holder.img.setImageResource(gridViewRowItemList.get(position).getImageId());
       /* if (pos == i || pos == 1 ||pos == 2 || pos == 3 || pos == 4 || pos == 5|| pos == 6|| pos == 7|| pos == 8|| pos == 9|| pos == 10|| pos == 11|| pos == 12|| pos == 13|| pos == 14 || pos == 15) {
            holder.ripple.setVisibility(View.VISIBLE);
        }*/

        rowView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub



                ConnectionDetector connectionDetector = ConnectionDetector.getInstance(context);
                if (connectionDetector.isConnectionAvailable()) {

                    if (position == 0) {

                        Intent intent = new Intent(context,TrainActivity.class);

                        context.startActivity(intent);

                    }
                    if (position==1) {
                        Intent intent = new Intent(context,ActivityRoute.class);

                        context.startActivity(intent);
                    }
                    if (position==2) {
                        Intent intent = new Intent(context,RouteActivity.class);
                    intent.putExtra("KeyWord","Telephonic%20Interview");

                    context.startActivity(intent);
                    }
                    if (position == 3) {Intent intent = new Intent(context,MapsActivity.class);

                        context.startActivity(intent);
                    }



                } else {
                   showToast(IErrors.INTERNET_CONNECT);
                }

            }
        });

        return rowView;
    }


    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        System.out.print("Character receive in Location adapter" + charText);
        gridViewRowItemList.clear();
        if (charText.length() == 0) {
            gridViewRowItemList.addAll(arraylist);
        } else {
            for (GridViewRowItem wp : arraylist) {
                if (wp.getTitle().toLowerCase(Locale.getDefault()).contains(charText)) {
                    gridViewRowItemList.add(wp);
                }
            }
        }
        notifyDataSetChanged();
    }

    public void showToast(String msg) {
        Toast.makeText(context, "" + msg, Toast.LENGTH_LONG).show();
    }

    public class Holder {
        TextView tv;
        ImageView img;

       /* LinearLayout llImage;*/
    }







}
