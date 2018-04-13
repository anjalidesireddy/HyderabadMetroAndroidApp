package com.grepthor.hyderabadmetro.Adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


import com.grepthor.hyderabadmetro.R;
import com.squareup.picasso.Picasso;

/**
 * Created by om on 11/24/2017.
 */

public class CustomAdapter extends PagerAdapter {

    Context context;

    private static LayoutInflater inflater = null;
    private String[] images;

    public CustomAdapter(Context contex, String[] images) {


        this.images = images;
        this.context = contex;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        inflater  = (LayoutInflater) context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View viewItem = inflater.inflate(R.layout.image_item, container, false);
        ImageView imageView = (ImageView) viewItem.findViewById(R.id.imageView);





        Picasso.with(context).load(images[position])
                .placeholder(R.drawable.welcomeimg)
                .into(imageView);



        ((ViewPager)container).addView(viewItem);

        return viewItem;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return 5;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        // TODO Auto-generated method stub
        return view == ((View)object);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        // TODO Auto-generated method stub
        ((ViewPager) container).removeView((View) object);
    }
}
