package com.example.recyclerviewapp;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class SeriesAdapter extends RecyclerView.Adapter<SeriesAdapter.MyViewHolder> {

    private List<Series> seriesList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView titulo, ano, genero;

        public MyViewHolder(View view) {
            super(view);
            titulo = (TextView) view.findViewById(R.id.titulo);
            genero = (TextView) view.findViewById(R.id.genero);
            ano = (TextView) view.findViewById(R.id.ano);
        }
    }


    public SeriesAdapter(List<Series> seriesList) {
        this.seriesList = seriesList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.series_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Series series = seriesList.get(position);
        holder.titulo.setText(series.getTitulo());
        holder.genero.setText(series.getGenero());
        holder.ano.setText(series.getAno());
    }

    @Override
    public int getItemCount() {
        return seriesList.size();
    }
}