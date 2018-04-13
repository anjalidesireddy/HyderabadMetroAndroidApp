package com.grepthor.hyderabadmetro.Showcase;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;


import com.grepthor.hyderabadmetro.Adapter.MyPagerAdapter;
import com.grepthor.hyderabadmetro.DashBordActivity;
import com.grepthor.hyderabadmetro.Helper.SharedPreferenceManager;
import com.grepthor.hyderabadmetro.R;
import com.grepthor.hyderabadmetro.constant.IConstant;

import java.util.Timer;
import java.util.TimerTask;

public class ShowcaseActivity extends AppCompatActivity {


    private RelativeLayout rlSignup,rlGetStarted;
    private MyPagerAdapter mpAdapter;
    private ViewPager landingPager;
    private ImageView imgFirst, imgSecond, imgThird, imgForth, imgFifth, imgSixth;
    private int page = 0;
    private Timer timer;
    private SharedPreferenceManager sharedPrefMgr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showcase);
        getSupportActionBar().hide();
        sharedPrefMgr = new SharedPreferenceManager(ShowcaseActivity.this);

        sharedPrefMgr.connectDB();
        String test = sharedPrefMgr.getString(IConstant.login);


        if (test.equalsIgnoreCase("true")) {

        } else {



            ActivityCompat.requestPermissions(ShowcaseActivity.this,
                    new String[]{android.Manifest.permission.CALL_PHONE},
                    1);

        }
        sharedPrefMgr.connectDB();
        sharedPrefMgr.setString(IConstant.login, "true");


        sharedPrefMgr.closeDB();


        sharedPrefMgr.closeDB();

        rlSignup = (RelativeLayout) findViewById(R.id.rlSignup);
            rlGetStarted = (RelativeLayout) findViewById(R.id.rlGetStarted);
            landingPager = (ViewPager) findViewById(R.id.landingPager);
            mpAdapter = new MyPagerAdapter(this);
            landingPager.setAdapter(mpAdapter);
            pageSwitcher(3);

            sharedPrefMgr = new SharedPreferenceManager(this);
            sharedPrefMgr.connectDB();
            boolean islogin = sharedPrefMgr.getBoolean("IsLogin");
            sharedPrefMgr.closeDB();

            if (islogin) {
                rlSignup.setVisibility(View.INVISIBLE);
            }

            imgFirst = (ImageView) findViewById(R.id.icnFirst);
            imgSecond = (ImageView) findViewById(R.id.icnSecond);
            imgThird = (ImageView) findViewById(R.id.icnThird);
            imgForth = (ImageView) findViewById(R.id.icnForth);
            imgFifth = (ImageView) findViewById(R.id.icnFifth);
            //imgSixth = (ImageView) findViewById(R.id.icnSixth);

            landingPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageSelected(int position) {
                    switch (position) {
                        case 0:
                            imgFirst.setImageResource(R.drawable.ic_action_red);
                            imgSecond.setImageResource(R.drawable.ic_action_gray);
                            imgThird.setImageResource(R.drawable.ic_action_gray);
                            imgForth.setImageResource(R.drawable.ic_action_gray);
                            imgFifth.setImageResource(R.drawable.ic_action_gray);
                            // imgSixth.setImageResource(R.drawable.gray);
                            break;
                        case 1:
                            imgFirst.setImageResource(R.drawable.ic_action_gray);
                            imgSecond.setImageResource(R.drawable.ic_action_red);
                            imgThird.setImageResource(R.drawable.ic_action_gray);
                            imgForth.setImageResource(R.drawable.ic_action_gray);
                            imgFifth.setImageResource(R.drawable.ic_action_gray);
                            // imgSixth.setImageResource(R.drawable.gray);
                            break;
                        case 2:
                            imgFirst.setImageResource(R.drawable.ic_action_gray);
                            imgSecond.setImageResource(R.drawable.ic_action_gray);
                            imgThird.setImageResource(R.drawable.ic_action_red);
                            imgForth.setImageResource(R.drawable.ic_action_gray);
                            imgFifth.setImageResource(R.drawable.ic_action_gray);
                            //imgSixth.setImageResource(R.drawable.gray);
                            break;
                        case 3:
                            imgFirst.setImageResource(R.drawable.ic_action_gray);
                            imgSecond.setImageResource(R.drawable.ic_action_gray);
                            imgThird.setImageResource(R.drawable.ic_action_gray);
                            imgForth.setImageResource(R.drawable.ic_action_red);
                            imgFifth.setImageResource(R.drawable.ic_action_gray);
                            // imgSixth.setImageResource(R.drawable.gray);
                            break;
                        case 4:
                            imgFirst.setImageResource(R.drawable.ic_action_gray);
                            imgSecond.setImageResource(R.drawable.ic_action_gray);
                            imgThird.setImageResource(R.drawable.ic_action_gray);
                            imgForth.setImageResource(R.drawable.ic_action_gray);
                            imgFifth.setImageResource(R.drawable.ic_action_red);
                            // imgSixth.setImageResource(R.drawable.gray);
                            break;
                    /*case 5:
						imgFirst.setImageResource(R.drawable.gray);
						imgSecond.setImageResource(R.drawable.gray);
						imgThird.setImageResource(R.drawable.gray);
						imgForth.setImageResource(R.drawable.gray);
		                imgFifth.setImageResource(R.drawable.gray);
		               // imgSixth.setImageResource(R.drawable.red);
						break;*/
                        default:
                            break;
                    }
                }

                @Override
                public void onPageScrolled(int arg0, float arg1, int arg2) {
                }

                @Override
                public void onPageScrollStateChanged(int arg0) {
                }
            });


//        rlSignup.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                Intent i = new Intent(ShowcaseActivity.this,RegestrationActivity.class);
//                startActivityForResult(i, 1);
//                ShowcaseActivity.this.overridePendingTransition(R.anim.slide_in_right, R.anim.fade_out);
//                finish();
//            }
//        });
            rlGetStarted.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent i = new Intent(ShowcaseActivity.this, DashBordActivity.class);
                    startActivityForResult(i, 1);
                    ShowcaseActivity.this.overridePendingTransition(R.anim.slide_in_right, R.anim.fade_out);
                    finish();
                }
            });

    }
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1: {

                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.

                    sharedPrefMgr.connectDB();
                    sharedPrefMgr.setString(IConstant.permission, "grant");


                    sharedPrefMgr.closeDB();
                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    Toast.makeText(ShowcaseActivity.this, "Permission denied to read your External storage", Toast.LENGTH_SHORT).show();
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }
    private void pageSwitcher(int seconds) {
        timer = new Timer(); // At this line a new Thread will be created
        timer.scheduleAtFixedRate(new RemindTask(), 0, seconds * 1000); // delay in milliseconds
    }

    class RemindTask extends TimerTask {
        @Override
        public void run() {

            runOnUiThread(new Runnable() {
                public void run() {
                    landingPager.setCurrentItem(page, true);
                    page += 1;

                    if (page > 4) { // In my case the number of pages are 5
                        page = 0;
                    }
                }
            });
        }
    }



}
