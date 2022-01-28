package com.filomenadeveloper.laboro_new_version.ui.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.selection.SelectionTracker
import androidx.recyclerview.widget.RecyclerView
import com.filomenadeveloper.laboro_new_version.R
import com.filomenadeveloper.laboro_new_version.data.models.CategoresModel


class AdapterCategory() : RecyclerView.Adapter<AdapterCategory.CategoriesHolder>() {

    private var categories: MutableList<CategoresModel> = mutableListOf()
    inner class CategoriesHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        @SuppressLint("ResourceAsColor")
        fun bind(categories: CategoresModel) {
            val cardCategory = itemView.findViewById<CardView>(R.id.cardCategory)!!
            val textCategory: AppCompatTextView = itemView.findViewById(R.id.textCategory)

            textCategory.text = categories.nameCategory

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesHolder {
        return CategoriesHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_categories, parent, false,
            )
        )
    }

    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder: CategoriesHolder, position: Int) {
        holder.bind(categories[position])

    }

    override fun getItemCount(): Int = categories.count()

    fun addAll(input: MutableList<CategoresModel>) {
        categories = input
        notifyDataSetChanged()
    }
}