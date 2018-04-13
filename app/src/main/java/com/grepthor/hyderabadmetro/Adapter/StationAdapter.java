package com.grepthor.hyderabadmetro.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.grepthor.hyderabadmetro.object.StationListObj;


import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by user on 3/8/16.
 */
public class StationAdapter extends BaseAdapter {

    private static LayoutInflater inflater = null;
    Context context;
    private Dialog dialog, dialog1;
    public static int i = 0;
    int pos;
    private SharedPreferenceManager sharedPrefMgr;
    private String stringCountryIdNew, stringCountryNameNew;
    private List<StationListObj> StationListObjList;
    private ArrayList<StationListObj> arraylist;

    public StationAdapter(Context mainActivity, List<StationListObj> StationListObjList) {
        // TODO Auto-generated constructor stub
        this.StationListObjList = StationListObjList;
        context = mainActivity;
        inflater = (LayoutInflater) context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.arraylist = new ArrayList<StationListObj>();
        this.arraylist.addAll(StationListObjList);

    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return StationListObjList.size();
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
        rowView = inflater.inflate(R.layout.item_main, null);

        holder.tv = (TextView) rowView.findViewById(R.id.textView);






       /* if (pos == i || pos == 1 ||pos == 2 || pos == 3 || pos == 4 || pos == 5|| pos == 6|| pos == 7|| pos == 8|| pos == 9|| pos == 10|| pos == 11|| pos == 12|| pos == 13|| pos == 14 || pos == 15) {
            holder.ripple.setVisibility(View.VISIBLE);
        }*/

        rowView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub



                ConnectionDetector connectionDetector = ConnectionDetector.getInstance(context);
                if (connectionDetector.isConnectionAvailable()) {




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
        StationListObjList.clear();
        if (charText.length() == 0) {
            StationListObjList.addAll(arraylist);
        } else {
            for (StationListObj wp : arraylist) {
                if (wp.getTitle().toLowerCase(Locale.getDefault()).contains(charText)) {
                    StationListObjList.add(wp);
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


       /* LinearLayout llImage;*/
    }







}
