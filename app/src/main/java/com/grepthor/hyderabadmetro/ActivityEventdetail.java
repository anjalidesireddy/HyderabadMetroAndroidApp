package com.grepthor.hyderabadmetro;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;


import com.grepthor.hyderabadmetro.Adapter.CustomAdapter;

import java.util.Timer;
import java.util.TimerTask;

public class ActivityEventdetail extends AppCompatActivity {
    int currentPage = 0;
    Timer timer;
    final long DELAY_MS = 500;//delay in milliseconds before task is to be executed
    final long PERIOD_MS = 3000; // time in milliseconds between successive task executions.
 TextView textViewCenterename, tvProgramname,tvprogramDescr,tvprogramstartDate,tvProgramTimeSlot;
  ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eventdetail);
        setTitle("Event");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        textViewCenterename=(TextView)findViewById(R.id.textViewCenterename);
         tvProgramname=(TextView)findViewById(R.id.tvProgramname);
         tvprogramDescr=(TextView)findViewById(R.id.tvprogramDescr);
         tvprogramstartDate=(TextView)findViewById(R.id.tvprogramstartDate);
         tvProgramTimeSlot=(TextView)findViewById(R.id.tvProgramTimeSlot);;


        Intent intent = getIntent();

        String images[] = {intent.getStringExtra("IMAGE_1"), intent.getStringExtra("IMAGE_2"), intent.getStringExtra("IMAGE_3"), intent.getStringExtra("IMAGE_4"), intent.getStringExtra("IMAGE_5")};



          String name=intent.getStringExtra("EVENT_NAME");
        String START_DATE=intent.getStringExtra("START_DATE");
             String END_DATE=intent.getStringExtra("END_DATE");
        String DESCRIPTION=intent.getStringExtra("DESCRIPTION");


        tvProgramname.setText(name);
        tvprogramDescr.setText(DESCRIPTION);
        tvprogramstartDate.setText(START_DATE);
        tvProgramTimeSlot.setText(END_DATE);;

        textViewCenterename.setText(name);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        PagerAdapter adapter = new CustomAdapter(getApplicationContext(),images);
        viewPager.setAdapter(adapter);

/*After setting the adapter use the timer */
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == 4) {
                    currentPage = 0;
                }
                viewPager.setCurrentItem(currentPage++, true);
            }
        };

        timer = new Timer(); // This will create a new Thread
        timer .schedule(new TimerTask() { // task to be scheduled

            @Override
            public void run() {
                handler.post(Update);
            }
        }, DELAY_MS, PERIOD_MS);

    }



    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
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
}
