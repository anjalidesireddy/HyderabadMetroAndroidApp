package com.grepthor.hyderabadmetro;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

import com.grepthor.hyderabadmetro.Helper.AppController;
import com.grepthor.hyderabadmetro.Helper.SharedPreferenceManager;
import com.grepthor.hyderabadmetro.Showcase.ShowcaseActivity;
import com.grepthor.hyderabadmetro.constant.IConstant;


public class SplashActivity extends AppCompatActivity {

    private ImageView ivBackground;
ImageView ivtrain;
    private SharedPreferenceManager sharedPrefMgr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getSupportActionBar().hide();

        sharedPrefMgr=new SharedPreferenceManager(SplashActivity.this);

        ivBackground = (ImageView) findViewById(R.id.ivBackground);

        ivtrain = (ImageView) findViewById(R.id.ivtrain);
        if (AppController.isInternet(this)) {
            Thread timer = new Thread() {
                public void run() {
                    try {

                     //   Animation animation2 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade);
                      //  ivBackground.startAnimation(animation2);
                    /*    rlName.startAnimation(animation2);*/
                        Animation animation3 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_in_left);
                        ivtrain.startAnimation(animation3);

                        animation3.setAnimationListener(new Animation.AnimationListener() {
                            @Override
                            public void onAnimationStart(Animation animation) {

                            }

                            @Override
                            public void onAnimationEnd(Animation animation) {

                                sharedPrefMgr.connectDB();
                               String test= sharedPrefMgr.getString(IConstant.login);





                                if(test.equalsIgnoreCase("true"))
                                {
                                    Intent i = new Intent(SplashActivity.this, DashBordActivity.class);
                                    startActivityForResult(i, 1);
                                    SplashActivity.this.overridePendingTransition(R.anim.slide_in_right, R.anim.fade_out);
                                    finish();
                                }
                                else {



                                    addNotification();
                                    ActivityCompat.requestPermissions(SplashActivity.this,
                                            new String[]{android.Manifest.permission.CALL_PHONE},
                                            1);
                                    Intent i = new Intent(SplashActivity.this, ShowcaseActivity.class);
                                    startActivityForResult(i, 1);
                                    SplashActivity.this.overridePendingTransition(R.anim.slide_in_right, R.anim.fade_out);
                                    finish();
                                }

                                sharedPrefMgr.closeDB();

                            }

                            @Override
                            public void onAnimationRepeat(Animation animation) {

                            }
                        });

                    } finally {
                    }
                }
            };
            timer.start();
        }
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
                    Toast.makeText(SplashActivity.this, "Permission denied to read your External storage", Toast.LENGTH_SHORT).show();
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }
    private void addNotification() {
        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.ic_action_notification)
                        .setContentTitle("Metro Notifications")
                        .setContentText("Thank You For Downloading App");

        Intent notificationIntent = new Intent(this, DashBordActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notificationIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(contentIntent);

        // Add as notification
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(0, builder.build());
    }

}