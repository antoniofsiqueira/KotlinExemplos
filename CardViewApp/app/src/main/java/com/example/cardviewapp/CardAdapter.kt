package com.example.cardviewapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.card_view.view.*
//ciando a classe com o construtor pedindo o objeto, e o contexto,
//extendendo o adpter que por sua vez leva a classe ViewHolder contida
//nessa classe
class CardAdpter(private val list:List<CardClass>, private val context
: Context) : RecyclerView.Adapter<CardAdpter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            ViewHolder {
        //infla o layout card_view para esse adpter
        val view =
            LayoutInflater.from(context).inflate(R.layout.card_view, parent,
                false)
        return ViewHolder(view)
    }
    override fun getItemCount(): Int {
        //retorna quantos objetos tem no recyclerview para assim
        //mostrar na tela
        return if (list.isEmpty())
            0
        else
            list.size
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val card = list[position]
        holder.texto.text = card.texto
        //glide onde carregara a imagem online e colocara dentro do
       // imageview do "card"
        Glide.with(context).load(card.linkImagem).into(holder.imagem)
    }
    //classe viewHolder
    class ViewHolder(item : View) : RecyclerView.ViewHolder(item){
        val texto = item.tvText
        val imagem = itemView.ivImage
    }
}