package com.example.gridviewapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
public class MainActivity extends Activity{
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        GridView gridView = (GridView) findViewById(R.id.grid_view);
        // Instance of ImageAdapter Class
        gridView.setAdapter(new ImageAdapter(this));
        /**
         * On Click event for Single Gridview Item
         * */
        gridView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                // Sending image id to FullScreenActivity
                Intent i = new Intent(getApplicationContext(),
                        FullImage.class);
                // passing array index
                i.putExtra("id", position);
                startActivity(i);
            }
        });
    }
}

