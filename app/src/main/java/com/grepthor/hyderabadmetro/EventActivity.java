package com.grepthor.hyderabadmetro;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.grepthor.hyderabadmetro.Adapter.BusinessAdapter1;
import com.grepthor.hyderabadmetro.objectclass.Event;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class EventActivity extends AppCompatActivity {
    private ListView lv;
    private ArrayList<Event> arrayList;
    private BusinessAdapter1 businessAdapter1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        getSupportActionBar().setTitle("Event");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        lv = (ListView) findViewById(R.id.lsv_allphotos);

        arrayList = new ArrayList<Event>();

        LoginUser();





    }

    private void LoginUser(){

        StringRequest stringRequest = new StringRequest(Request.Method.GET, "http://grepthorsoftware.in/tst/healthy/webservices/eventbusiness.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {



                        try {
                            String statustring,message;
                            JSONArray jsonArray1;
                            JSONArray jsonArray2 = null;
                            JSONObject jsonObject1;


                            jsonArray1 = new JSONArray(response);

                            jsonObject1=jsonArray1.getJSONObject(0);


                            statustring = jsonObject1.getString("status");


                            if(statustring.equals("1")) {
                                jsonArray2=jsonObject1.getJSONArray("result");

                                for (int index = 0; index < jsonArray2.length(); index++) {
                                    try {
                                        arrayList.add(parseObject(jsonArray2.getJSONObject(index)));

                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }



                            businessAdapter1 = new BusinessAdapter1(EventActivity.this, arrayList);

                            lv.setAdapter(businessAdapter1);






                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //Toast.makeText(TempleLiveActivity.this,error.toString(),Toast.LENGTH_LONG).show();
                    }
                }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                // params.put(DomeActivity.KEY_custmobile,custmobile);

                return params;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
    }




    public Event parseObject(JSONObject jsonObject) {
        Event loginn = new Event();
        try {




            loginn.EVENT_ID = jsonObject.getString("EVENT_ID");
            loginn.EVENT_TYPE = jsonObject.getString("EVENT_TYPE");
            loginn.EVENT_NAME = jsonObject.getString("EVENT_NAME");
            loginn.IMAGE_1 = jsonObject.getString("IMAGE_1");
            loginn.IMAGE_2= jsonObject.getString("IMAGE_2");
            loginn.IMAGE_3 = jsonObject.getString("IMAGE_3");
            loginn.IMAGE_4 = jsonObject.getString("IMAGE_4");
            loginn.IMAGE_5 = jsonObject.getString("IMAGE_5");
            loginn.START_DATE = jsonObject.getString("START_DATE");
            loginn.END_DATE = jsonObject.getString("END_DATE");
            loginn.DESCRIPTION = jsonObject.getString("DESCRIPTION");


        } catch (JSONException e) {
            e.printStackTrace();
        }
        return loginn;
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
