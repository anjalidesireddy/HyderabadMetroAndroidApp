package com.grepthor.hyderabadmetro.Route;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.grepthor.hyderabadmetro.Helper.ConnectionDetector;
import com.grepthor.hyderabadmetro.R;
import com.grepthor.hyderabadmetro.constant.IErrors;
import com.grepthor.hyderabadmetro.objectclass.ProductCodeObj;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RouteActivity extends AppCompatActivity {
    TextView sources,dest;
   public List<String> sourcse;
    public List<String> desti;
     String  text1,text2;
   String temp3=null;
    ImageView swip;
    Button btsearch;
    TextView tvendpoint,tvstartpoint,tvarivaltime,tvdistance,tvfare;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route2);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        sources=(TextView)findViewById(R.id.sources);
        dest=(TextView)findViewById(R.id.dest);
        swip=(ImageView)findViewById(R.id.swip);
        sourcse= new ArrayList<String>();
        desti= new ArrayList<String>();


        btsearch=(Button)findViewById(R.id.btsearch);

        tvendpoint=(TextView)findViewById(R.id.tvendpoint);
        tvstartpoint=(TextView)findViewById(R.id.tvstartpoint);
        tvarivaltime=(TextView)findViewById(R.id.tvarivaltime);
        tvdistance=(TextView)findViewById(R.id.tvdistance);
        tvfare=(TextView)findViewById(R.id.tvfare);
        SourcseList();
        destinationList();
        sources.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ShowAlertDialogWithListview();
            }
        });

        dest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                ShowAlertDialogWithListview1();
            }
        });



        swip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                text1=sources.getText().toString();
                text2=dest.getText().toString();
                temp3=text1;
                text1=text2;
                text2=temp3;
                sources.setText(text1);
                dest.setText(text2);

            }
        });
        btsearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!sources.getText().toString().equalsIgnoreCase(dest.getText().toString()))
                {
                    FareChart();
                }
                else
                {
                    Toast.makeText(RouteActivity.this, "source and destination must diffrent ", Toast.LENGTH_SHORT).show();

                }
            }
        });

    }

    public void ShowAlertDialogWithListview()
    {


        final CharSequence[] Animals = sourcse.toArray(new String[sourcse.size()]);
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        dialogBuilder.setTitle("Source");
        dialogBuilder.setItems(Animals, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int item) {
                String selectedText = Animals[item].toString();  //Selected item in listview
                sources.setText(selectedText);

            }
        });
        //Create alert dialog object via builder
        AlertDialog alertDialogObject = dialogBuilder.create();
        //Show the dialog
        alertDialogObject.show();
    }


    private void SourcseList(){

        StringRequest stringRequest = new StringRequest(Request.Method.GET, "http://grepthorsoftware.in/tst/metro/webservices/metrosource.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        System.out.println("responce="+response.toString());


                        try {




                       String statustring;

                            JSONArray jsonArray1,jsonArray2;
                              JSONObject jsonObject1;


                            jsonArray1 = new JSONArray(response);

                            jsonObject1=jsonArray1.getJSONObject(0);


                            statustring = jsonObject1.getString("status");

                            jsonArray2=jsonObject1.getJSONArray("result");



                            for (int index = 0; index < jsonArray2.length(); index++) {
                                try {
                                    sourcse.add(jsonArray2.getJSONObject(index).getString("STARTING_POINT"));

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }



                        }catch (Exception e)
                        {

                        }





                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(RouteActivity.this,error.toString(),Toast.LENGTH_LONG).show();
                    }
                }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();

                return params;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }






    public void ShowAlertDialogWithListview1()
    {
        final CharSequence[] Animals = desti.toArray(new String[desti.size()]);
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        dialogBuilder.setTitle("Destination");
        dialogBuilder.setItems(Animals, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int item) {
                String selectedText = Animals[item].toString();  //Selected item in listview
                dest.setText(selectedText);

            }
        });
        //Create alert dialog object via builder
        AlertDialog alertDialogObject = dialogBuilder.create();
        //Show the dialog
        alertDialogObject.show();
    }



    private void destinationList(){

        StringRequest stringRequest = new StringRequest(Request.Method.GET, "http://grepthorsoftware.in/tst/metro/webservices/metrodestination.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        System.out.println("responce="+response.toString());


                        try {




                            String statustring;

                            JSONArray jsonArray1,jsonArray2;
                            JSONObject jsonObject1;


                            jsonArray1 = new JSONArray(response);

                            jsonObject1=jsonArray1.getJSONObject(0);


                            statustring = jsonObject1.getString("status");

                            jsonArray2=jsonObject1.getJSONArray("result");



                            for (int index = 0; index < jsonArray2.length(); index++) {
                                try {
                                    desti.add(jsonArray2.getJSONObject(index).getString("ENDING_POINT"));

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }



                        }catch (Exception e)
                        {

                        }





                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(RouteActivity.this,error.toString(),Toast.LENGTH_LONG).show();
                    }
                }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();

                return params;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }








    private void FareChart(){
        //final String custmobile = etMobile.getText().toString().trim();

        ConnectionDetector connectionDetector = ConnectionDetector.getInstance(RouteActivity.this);
        if (connectionDetector.isConnectionAvailable()) {

            StringRequest stringRequest = new StringRequest(Request.Method.POST,"http://grepthorsoftware.in/tst/metro/webservices/metrodetails.php",
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            System.out.println("login responce=" + response.toString());


                            try {

                                JSONArray jsonArray1;
                                JSONObject jsonObject1;
                                JSONObject jsonObject2;
                                String statustring, message;

                                jsonArray1 = new JSONArray(response);
                                jsonObject1 = jsonArray1.getJSONObject(0);

                                statustring = jsonObject1.getString("status");
                                message = jsonObject1.getString("message");
                                //

                                    jsonObject2 = jsonObject1.getJSONObject("result");

                                    Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                                    System.out.println("output Login=" + response);
                                    Toast.makeText(RouteActivity.this, message.toString(), Toast.LENGTH_LONG).show();


                                    String FARE = jsonObject2.getString("FARE");
                                    String DISTANCE = jsonObject2.getString("DISTANCE");
                                    String ARRIVE_TIME = jsonObject2.getString("ARRIVE_TIME");
                                    String STARTING_POINT = jsonObject2.getString("STARTING_POINT");
                                    String ENDING_POINT = jsonObject2.getString("ENDING_POINT");


                                tvendpoint.setText(ENDING_POINT);
                                tvstartpoint.setText(STARTING_POINT);
                                tvarivaltime.setText(ARRIVE_TIME);
                                tvdistance.setText(DISTANCE);
                                tvfare.setText(FARE);




                            } catch (Exception e) {

                            }


                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(RouteActivity.this, error.toString(), Toast.LENGTH_LONG).show();

                        }
                    }) {
                @Override
                protected Map<String, String> getParams() {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("source",sources.getText().toString().trim());

                    params.put("destination", dest.getText().toString().trim());
                 System.out.print("KEY_custmobile="+params);

                    return params;
                }

            };

            RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(stringRequest);
        }
        else {
            Toast.makeText(RouteActivity.this, IErrors.INTERNET_CONNECT, Toast.LENGTH_LONG).show();

        }
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
