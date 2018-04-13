package com.grepthor.hyderabadmetro;

import android.*;
import android.app.Activity;
import android.app.Dialog;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.NotificationCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.view.MenuInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.firebase.crash.FirebaseCrash;
import com.grepthor.hyderabadmetro.AboutUs.AboutusActivity;
import com.grepthor.hyderabadmetro.Adapter.DashboardGridAdapter1;
import com.grepthor.hyderabadmetro.Adapter.SlidingImage_Adapter;
import com.grepthor.hyderabadmetro.Emergency.AmbulanceNumbers;
import com.grepthor.hyderabadmetro.Emergency.EmergencyNumbers;
import com.grepthor.hyderabadmetro.Emergency.FireNumbers;
import com.grepthor.hyderabadmetro.Emergency.PoliceNumbers;
import com.grepthor.hyderabadmetro.Helper.BadgeDrawable;
import com.grepthor.hyderabadmetro.Helper.ExpandableHeightGridView;
import com.grepthor.hyderabadmetro.Helper.SharedPreferenceManager;
import com.grepthor.hyderabadmetro.Showcase.ShowcaseActivity;
import com.grepthor.hyderabadmetro.constant.IConstant;
import com.grepthor.hyderabadmetro.metrohistory.ActivityMetrohistory;
import com.grepthor.hyderabadmetro.metroinfo.ActivityMetroInfo;
import com.grepthor.hyderabadmetro.object.GridViewRowItem;
import com.grepthor.hyderabadmetro.termsandcondition.ActivityTermsandcond;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import me.relex.circleindicator.CircleIndicator;

public class DashBordActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private AdView mAdView;
    private List<GridViewRowItem> gridViewRowItemList;
    private DashboardGridAdapter1 dashboardGridAdapter1;
    private ExpandableHeightGridView gv;
    private Dialog dialog, dialog1;
    private ViewFlipper simpleViewFlipper;
    TextView txtViewCount;
    String pr;
    int count;
    private static ViewPager mPager;
    private static int currentPage = 0;
    private static int NUM_PAGES = 0;
    private static final Integer[] IMAGES= {R.drawable.welcomeimg,R.drawable.safety,R.drawable.welcomeimg,R.drawable.design};
    private ArrayList<Integer> ImagesArray = new ArrayList<Integer>();
    SharedPreferenceManager sharedPrefMgr;
    ImageView fb,tw,insta,share;
    LinearLayout cardambulance,cardpolice,cardfirebirgade,cardEergency;
    int[] images = {R.drawable.welcomeimg, R.drawable.safety, R.drawable.hmrlogo, R.drawable.logodb, R.drawable.design};     // array of images

    public static String[] prgmNameList = {"Stations", "Route", "Fares", "NearBy"};

    public static int[] prgmImages = {R.drawable.traina, R.drawable.route, R.drawable.cash, R.drawable.placeholder,};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_bord);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Hyderabad Metro");
        sharedPrefMgr = new SharedPreferenceManager(DashBordActivity.this);

        FirebaseCrash.log("here comes the Exception");
        FirebaseCrash.report(new Exception("oops!"));


        init();

        cardambulance=(LinearLayout)findViewById(R.id.cardambulance);
           cardpolice=(LinearLayout)findViewById(R.id.cardpolice);
           cardfirebirgade=(LinearLayout)findViewById(R.id.cardfirebirgade);
           cardEergency=(LinearLayout)findViewById(R.id.cardEergency);;


        cardambulance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DashBordActivity.this,AmbulanceNumbers.class));
            }
        });
        cardpolice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DashBordActivity.this, PoliceNumbers.class));

            }
        });

        cardfirebirgade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DashBordActivity.this, FireNumbers.class));

            }
        });
        cardEergency.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DashBordActivity.this, EmergencyNumbers.class));

            }
        });
        gridViewRowItemList = new ArrayList<GridViewRowItem>();
        for (int i = 0; i < prgmNameList.length; i++) {
            GridViewRowItem item = new GridViewRowItem(prgmImages[i], prgmNameList[i]);
            gridViewRowItemList.add(item);
        }
        gv = (ExpandableHeightGridView)findViewById(R.id.gridView1);
        dashboardGridAdapter1 = new DashboardGridAdapter1(DashBordActivity.this,gridViewRowItemList);
        gv.setAdapter(dashboardGridAdapter1);
        gv.setExpanded(true);

           mAdView = (AdView) findViewById(R.id.adView);
        if (TextUtils.isEmpty(getString(R.string.appunitid))) {
            Toast.makeText(getApplicationContext(), "Please mention your Banner Ad ID in strings.xml", Toast.LENGTH_LONG).show();
            return;
        }


        // get The references of ViewFlipper
        simpleViewFlipper = (ViewFlipper) findViewById(R.id.simpleViewFlipper); // get the reference of ViewFlipper

        // loop for creating ImageView's
        for (int i = 0; i < images.length; i++) {
            // create the object of ImageView
            ImageView imageView = new ImageView(this);
            imageView.setImageResource(images[i]); // set image in ImageView
            simpleViewFlipper.addView(imageView); // add the created ImageView in ViewFlipper
        }
        // Declare in and out animations and load them using AnimationUtils class
        Animation in = AnimationUtils.loadAnimation(this, android.R.anim.slide_in_left);
        Animation out = AnimationUtils.loadAnimation(this, android.R.anim.slide_out_right);
        // set the animation type's to ViewFlipper
        simpleViewFlipper.setInAnimation(in);
        simpleViewFlipper.setOutAnimation(out);
        // set interval time for flipping between views
        simpleViewFlipper.setFlipInterval(3000);
        // set auto start for flipping between views
        simpleViewFlipper.setAutoStart(true);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();



        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);

       //  fb=  navigationView.getHeaderView(0).findViewById(R.id.fb);
        share=  navigationView.getHeaderView(0).findViewById(R.id.share);
        navigationView.setNavigationItemSelectedListener(this);


        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               /* Intent intent = new Intent(Intent.ACTION_SEND);
                intent.putExtra(Intent.EXTRA_TEXT, "dsdsds" + "\n" + "sds" + "ddsds" + " \n " + "https://play.google.com/store/apps/details?id=com.engeniuspark.omnamouser");
                intent.setType("image/jpeg");
                // intent.putExtra(Intent.EXTRA_STREAM, uri);
                //  getApplicationContext().startActivity(Intent.createChooser(intent, "Share Product"));

                Intent i = new Intent(Intent.createChooser(intent, "Share Product"));
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);*/
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT,
                        "Hey check out my app at: https://play.google.com/store/apps/details?id=com.google.android.apps.plus");
                sendIntent.setType("text/plain");
                startActivity(sendIntent);






            }
        });


        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                // Check the LogCat to get your test device ID
                .addTestDevice("C04B1BFFB0774708339BC273F8A43708")
                .build();

        mAdView.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
            }
            @Override
            public void onAdClosed() {
                Toast.makeText(getApplicationContext(), "Ad is closed!", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onAdFailedToLoad(int errorCode) {
                Toast.makeText(getApplicationContext(), "Ad failed to load! error code: " + errorCode, Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onAdLeftApplication() {
                Toast.makeText(getApplicationContext(), "Ad left application!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdOpened() {
                super.onAdOpened();
            }
        });

        mAdView.loadAd(adRequest);




    }


    private void rateusDailog(int animationSource, String type) {

        int width = (int) (getResources().getDisplayMetrics().widthPixels);
        int height = (int) (getResources().getDisplayMetrics().heightPixels * 0.40);
        dialog1 = new Dialog(this);
        dialog1.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog1.getWindow().setLayout(width, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog1.setContentView(R.layout.dailog_rateus);
        dialog1.setCancelable(true);
        dialog1.getWindow().getAttributes().windowAnimations = animationSource;
        dialog1.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog1.show();

        final TextView tvLater, tvRateNow;

        tvLater = (TextView) dialog1.findViewById(R.id.tvLater);
        tvRateNow = (TextView) dialog1.findViewById(R.id.tvRateNow);


        tvLater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog1.dismiss();
            }
        });

        tvRateNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                startActivity(new Intent(Intent.ACTION_VIEW,
//                        Uri.parse("http://play.google.com/store/apps/details?id=com.omnamopandit&hl=en"
//                                /*+ getPackageName()*/)));


                startActivity(new Intent(Intent.ACTION_VIEW,
                        Uri.parse("http://play.google.com/store/apps/details?id="
                                + getApplicationContext().getPackageName())));
                dialog1.dismiss();

            }
        });


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }



    public void updateHotCount(final int new_hot_number) {
        count = new_hot_number;
        if (count < 0) return;
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (count == 0)
                    txtViewCount.setVisibility(View.GONE);
                else {
                    txtViewCount.setVisibility(View.VISIBLE);
                    txtViewCount.setText(Integer.toString(count));
                    // supportInvalidateOptionsMenu();
                }
            }
        });
    }



        @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {


            case R.id.action_cart:

                startActivity(new Intent(DashBordActivity.this, NotificationActivity.class));

            default:
                return super.onOptionsItemSelected(item);
        }
    }


    @Override
    public void onPause() {
        if (mAdView != null) {
            mAdView.pause();
        }
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mAdView != null) {
            mAdView.resume();
        }
    }

    @Override
    public void onDestroy() {
        if (mAdView != null) {
            mAdView.destroy();
        }
        super.onDestroy();
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {

        } else if (id == R.id.nav_founder) {
            rateusDailog(R.style.DialogAnimation, "Left - Right Animation!");
        }
        else if (id == R.id.nav_about) {
           startActivity(new Intent(DashBordActivity.this, ActivityMetrohistory.class));
        }

        else if (id == R.id.nav_mission) {
            startActivity(new Intent(DashBordActivity.this, ActivityMetroInfo.class));
        }

        else if (id == R.id.nav_event) {
            startActivity(new Intent(DashBordActivity.this, ActivityTermsandcond.class));
        }
        else if (id == R.id.nav_aboutus) {
            startActivity(new Intent(DashBordActivity.this, AboutusActivity.class));
        }
        else if (id == R.id.nav_contact) {
            startActivity(new Intent(DashBordActivity.this, EventActivity.class));
        }  /*else if (id == R.id.nav_gallary) {
            startActivity(new Intent(DashBordActivity.this, NotificationActivity.class));
        }*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    private void init() {
        for(int i=0;i<IMAGES.length;i++)
            ImagesArray.add(IMAGES[i]);

        mPager = (ViewPager) findViewById(R.id.pager);


        mPager.setAdapter(new SlidingImage_Adapter(DashBordActivity.this,ImagesArray));

        CircleIndicator indicator = (CircleIndicator)findViewById(R.id.indicator);
//        CirclePageIndicator indicator = (CirclePageIndicator)
//                findViewById(R.id.indicator);
//
        indicator.setViewPager(mPager);

        final float density = getResources().getDisplayMetrics().density;

//Set circle indicator radius
        //  indicator.setRadius(5 * density);

        NUM_PAGES =IMAGES.length;

        // Auto start of viewpager
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == NUM_PAGES) {
                    currentPage = 0;
                }
                mPager.setCurrentItem(currentPage++, true);
            }
        };
        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 10000, 10000);

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
                    Toast.makeText(DashBordActivity.this, "Permission denied to read your External storage", Toast.LENGTH_SHORT).show();
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }
    public static void setBadgeCount(Context context, LayerDrawable icon, String count) {

        BadgeDrawable badge;

        // Reuse drawable if possible
        Drawable reuse = icon.findDrawableByLayerId(R.id.ic_badge);
        if (reuse != null && reuse instanceof BadgeDrawable) {
            badge = (BadgeDrawable) reuse;
        } else {
            badge = new BadgeDrawable(context);
        }

        badge.setCount(count);
        icon.mutate();
        icon.setDrawableByLayerId(R.id.ic_badge, badge);
    }
}
