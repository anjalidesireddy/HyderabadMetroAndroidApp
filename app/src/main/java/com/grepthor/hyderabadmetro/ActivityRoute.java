package com.grepthor.hyderabadmetro;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.MenuItem;
import android.view.View;

import com.grepthor.hyderabadmetro.Adapter.MyAdapter;
import com.grepthor.hyderabadmetro.RouteMapsActivity.ActivityMap1;
import com.grepthor.hyderabadmetro.RouteMapsActivity.ActivityMap2;
import com.grepthor.hyderabadmetro.RouteMapsActivity.ActivityMap3;
import com.grepthor.hyderabadmetro.RouteMapsActivity.ActivityMap4;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import me.relex.circleindicator.CircleIndicator;

public class ActivityRoute extends AppCompatActivity {
    private static ViewPager mPager;
    private static int currentPage = 0;
    private static final Integer[] XMEN= {R.drawable.map1,R.drawable.untitled,R.drawable.untitled1,R.drawable.untitled3};
    private ArrayList<Integer> XMENArray = new ArrayList<Integer>();
    CardView cr1,cr2,cr3,cr4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Metro Route");
        setContentView(R.layout.activity_route);

        cr1=(CardView)findViewById(R.id.cr1);
        cr2=(CardView)findViewById(R.id.cr2);
        cr3=(CardView)findViewById(R.id.cr3);
        cr4=(CardView)findViewById(R.id.cr4);

        init();
    }
    private void init() {
        for(int i=0;i<XMEN.length;i++)
            XMENArray.add(XMEN[i]);

        mPager = (ViewPager) findViewById(R.id.pager);
        mPager.setAdapter(new MyAdapter(ActivityRoute.this,XMENArray));
        CircleIndicator indicator = (CircleIndicator) findViewById(R.id.indicator);
        indicator.setViewPager(mPager);

        cr1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ActivityRoute.this, ActivityMap1.class));

            }
        });
        cr2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ActivityRoute.this, ActivityMap2.class));

            }
        });
        cr3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ActivityRoute.this, ActivityMap3.class));

            }
        });
        cr4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ActivityRoute.this, ActivityMap4.class));

            }
        });

        // Auto start of viewpager
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == XMEN.length) {
                    currentPage = 0;
                }
                mPager.setCurrentItem(currentPage++, true);
            }
        };
       // Timer swipeTimer = new Timer();
//        swipeTimer.schedule(new TimerTask() {
//            @Override
//            public void run() {
//                handler.post(Update);
//            }
//        }, 2500, 2500);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }}