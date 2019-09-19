package com.example.lab_14_imagens

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView

class ActionsAdapter(private val context: Context,
                     private val resource: Int,
                     private val dados: List<HMAux>): BaseAdapter() {

    private val mInflater: LayoutInflater
    private var selectedId: Long = -1L

    fun setSelectedId(id: Long) {
        if (selectedId == id){
            selectedId = -1L
        } else {
            selectedId = id
        }
//
        notifyDataSetChanged()
    }
    init {
        this.mInflater = LayoutInflater.from(context)
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup):
            View {
        var convertView1 = convertView
        if (convertView1 == null) {
            convertView1 = mInflater.inflate(resource, parent, false)
        }
        val item = dados[position]
        val ll_fundo = convertView1!!.findViewById<LinearLayout>(R.id.celula_ll_fundo)
        val iv_icon = convertView1.findViewById<ImageView>(R.id.celula_iv_icon)
        val tv_nome = convertView1.findViewById<TextView>(R.id.celula_tv_nome)
        iv_icon.setImageResource(Integer.parseInt(item[HMAux.TEXTO_03]!!))
        tv_nome.text = item[HMAux.TEXTO_01]
        if (item[HMAux.ID]?.toLong() == selectedId ){
            ll_fundo.background = context.getDrawable(R.drawable.fundo_selected)
        } else {
            ll_fundo.background = context.getDrawable(R.drawable.fundo_unselected)
        }
        return convertView1
    }


    override fun getItem(p0: Int): Any {
    return dados[p0]
    }

    override fun getItemId(p0: Int): Long {
    val item = dados[p0]
        return java.lang.Long.parseLong(item[HMAux.ID].toString())
    }

    override fun getCount(): Int {
    return dados.size
    }

}