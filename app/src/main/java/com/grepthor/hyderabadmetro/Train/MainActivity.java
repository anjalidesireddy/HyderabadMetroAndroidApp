package com.grepthor.hyderabadmetro.Train;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.grepthor.hyderabadmetro.R;

public class MainActivity extends AppCompatActivity {


  @Override
  protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_main);
      ListView list = (ListView) findViewById(R.id.list);
  }




}
