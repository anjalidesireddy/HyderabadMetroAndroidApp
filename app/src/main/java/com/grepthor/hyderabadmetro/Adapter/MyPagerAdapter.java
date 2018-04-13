package com.grepthor.hyderabadmetro.Adapter;


import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;

import com.grepthor.hyderabadmetro.R;


public class MyPagerAdapter extends PagerAdapter
{
	int NumberOfPages = 5;
	  
	int[] res = {R.drawable.welcomeimg, R.drawable.hmrlogo, R.drawable.welcomeimg, R.drawable.design, R.drawable.logodb/*, R.drawable.a_6*/};
	
	private Context context;
	  
	public MyPagerAdapter(Context context)
	{
		this.context = context;
	}
	  	  
	@Override
	public int getCount() {
		return NumberOfPages;
	}

	@Override
	public boolean isViewFromObject(View view, Object object) {
		return view == ((ImageView) object);
	}

	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		 ImageView imageView = new ImageView(context);
		 imageView.setImageResource(res[position]);
	     LayoutParams layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
	     imageView.setLayoutParams(layoutParams);
	     ((ViewPager) container).addView(imageView, 0);
	      
		return imageView;
	}
	
	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		((ViewPager) container).removeView((ImageView) object);
	}
}