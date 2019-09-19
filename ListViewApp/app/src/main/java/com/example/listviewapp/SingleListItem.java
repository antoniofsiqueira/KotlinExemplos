package com.example.listviewapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
public class SingleListItem extends Activity{
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.single_list);
        TextView txtElemento = (TextView)
                findViewById(R.id.elemento_label);
        Intent i = getIntent();
        String elemento = i.getStringExtra("elemento");
        txtElemento.setText(elemento);
    }

}
