package com.grepthor.hyderabadmetro.Emergency;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.grepthor.hyderabadmetro.R;

import java.util.ArrayList;
import java.util.List;


public class PoliceNumbers extends AppCompatActivity {
    RecyclerView list;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    String[] Names;
    String[] number;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_police_numbers );
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Police Station Numbers");

        Names = getResources().getStringArray( R.array.police_staation_names );
        number = getResources().getStringArray( R.array.police_staation_numbers );
        list = (RecyclerView) findViewById( R.id.customListView );
        list.setHasFixedSize( true );
        layoutManager = new LinearLayoutManager( this );
        list.setLayoutManager( layoutManager );
        List<String> input = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            input.add( "Test" + i );
        }
        // define an adapter
        mAdapter = new MyAdapter( input );
        list.setAdapter( mAdapter );
        list.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        } );
    }

    class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> implements View.OnClickListener {
        private static final int REQUEST_CALL = 1;
        private List<String> values;

        public class ViewHolder extends RecyclerView.ViewHolder {
            public TextView txtHeader;
            public TextView txtFooter;
            public CardView policeimage;
            private final Context context;

            public ViewHolder(View v) {
                super( v );
                context = itemView.getContext();
                txtHeader = (TextView) v.findViewById( R.id.textView );
                txtFooter = (TextView) v.findViewById( R.id.textView1 );
                policeimage = (CardView) v.findViewById( R.id.imagecard );
            }
        }
        @Override
        public void onClick(View v) {

        }

        public MyAdapter(List<String> myDataset) {
            values = myDataset;
        }

        @Override
        public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            // create a new view
            LayoutInflater inflater = LayoutInflater.from(
                    parent.getContext() );
            View v =
                    inflater.inflate( R.layout.item_next, parent, false );
            // set the view's size, margins, paddings and layout parameters
            ViewHolder vh = new ViewHolder( v );
            return vh;
        }

        @Override
        public void onBindViewHolder(MyAdapter.ViewHolder holder, final int position) {
            // - get element from your dataset at this position
            // - replace the contents of the view with that element
            holder.txtHeader.setText( Names[position] );
            holder.txtHeader.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                }
            } );

            holder.txtFooter.setText( number[position] );
            holder.txtFooter.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String url = "tel:" +number[position];
                    Intent intent = new Intent( Intent.ACTION_CALL );
                    intent.setData( Uri.parse( url ) );
                    if (ActivityCompat.checkSelfPermission( getBaseContext(), Manifest.permission.CALL_PHONE ) != PackageManager.PERMISSION_GRANTED) {
                        // TODO: Consider calling
                        //    ActivityCompat#requestPermissions
                        // here to request the missing permissions, and then overriding
                        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                        //                                          int[] grantResults)
                        // to handle the case where the user grants the permission. See the documentation
                        // for ActivityCompat#requestPermissions for more details.
                        return;
                    }
                    startActivity( intent );
                }
            } );
            holder.policeimage.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String url = "tel:" +number[position];
                    Intent intent = new Intent( Intent.ACTION_CALL );
                    intent.setData( Uri.parse( url ) );
                    if (ActivityCompat.checkSelfPermission( getBaseContext(), Manifest.permission.CALL_PHONE ) != PackageManager.PERMISSION_GRANTED) {
                        // TODO: Consider calling
                        //    ActivityCompat#requestPermissions
                        // here to request the missing permissions, and then overriding
                        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                        //                                          int[] grantResults)
                        // to handle the case where the user grants the permission. See the documentation
                        // for ActivityCompat#requestPermissions for more details.
                        return;
                    }
                    startActivity( intent );
                }
            } );
        }

        @Override
        public int getItemCount() {
            return Names.length;
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
                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    Toast.makeText(PoliceNumbers.this, "Permission denied to read your External storage", Toast.LENGTH_SHORT).show();
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
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
    }
}
