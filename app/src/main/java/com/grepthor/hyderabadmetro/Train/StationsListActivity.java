package com.grepthor.hyderabadmetro.Train;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.grepthor.hyderabadmetro.Adapter.SelectedStationAdapter;
import com.grepthor.hyderabadmetro.R;

import java.util.ArrayList;

public class StationsListActivity extends AppCompatActivity {
    ListView stationsList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stations_list);

        stationsList=(ListView)findViewById(R.id.stations_list);

        ArrayList<String> arr = (ArrayList<String>) getIntent().getSerializableExtra("mylist");

        SelectedStationAdapter adapter = new SelectedStationAdapter(StationsListActivity.this, arr);
          stationsList.setAdapter(adapter);



    }
}
