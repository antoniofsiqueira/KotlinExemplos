package com.example.recyclerviewapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<Series> seriesList = new ArrayList<>();
    private RecyclerView recyclerView;
    private SeriesAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        mAdapter = new SeriesAdapter(seriesList);

        recyclerView.setHasFixedSize(true);

        // RecyclerView vertical
        // series_list_row.xml com o match_parent
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());

        // horizontal RecyclerView horizontal
        // largura series_list_row.xml `wrap_content`
        // RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);

        recyclerView.setLayoutManager(mLayoutManager);

        // adicionando divider line
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

        // adicionando o custom divider line com o padding 16dp
        // recyclerView.addItemDecoration(new MyDividerItemDecoration(this, LinearLayoutManager.VERTICAL, 16));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        recyclerView.setAdapter(mAdapter);

        // row click listener
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Series movie = seriesList.get(position);
                Toast.makeText(getApplicationContext(), movie.getTitulo() + " is selected!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

        prepareSeriesData();
    }

    /**
     *  prepara  o sample data para "popular" o data set  adapter
     */
    private void prepareSeriesData() {
        Series series = new Series("Breaking Bad", "Drama", "2016");
        seriesList.add(series);

        series = new Series("The Sopranos", "Drama", "1999");
        seriesList.add(series);

        series = new Series("Game of Thrones", "Ação", "2011");
        seriesList.add(series);

        series = new Series("Homeland", "Ação", "2011");
        seriesList.add(series);

        series = new Series("Silicon Valley", "Comedia", "2014");
        seriesList.add(series);

        series = new Series("WestWorld", "Drama", "2016");
        seriesList.add(series);

        series = new Series("Stranger Things", "Suspense", "2016");
        seriesList.add(series);

        series = new Series("Better Call Saul", "Drama", "2015");
        seriesList.add(series);

        series = new Series("Narcos", "Drama", "2015");
        seriesList.add(series);

        series = new Series("Billions", "Drama", "2016");
        seriesList.add(series);

        // notifica o adapter caso ocorra mudanças de data set
        // assim ele renderiza a lista com o novo data set
        mAdapter.notifyDataSetChanged();
    }

}