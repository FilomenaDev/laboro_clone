package com.filomenadeveloper.laboro_new_version.ui.venda

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.filomenadeveloper.laboro_new_version.R
import kotlin.collections.ArrayList

class AdapterVenda(val venda: ArrayList<venda>): RecyclerView.Adapter<AdapterVenda.ViewHolder>() {


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItems(vend: venda){
          //  val mCardview = itemView.findViewById(R.id.card) as CardView
            val textCard = itemView.findViewById(R.id.textcard) as ImageView
            textCard.setImageResource(vend.descricao)

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_venda_background, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return venda.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(venda[position])
    }
}