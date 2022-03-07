package com.filomenadeveloper.laboro_new_version.ui.drawer.drawer_produts.product.addProducts

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.loadAny
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.filomenadeveloper.laboro_new_version.database.baseModels.Produts
import com.filomenadeveloper.laboro_new_version.database.baseModels.getFormattedPrice
import com.filomenadeveloper.laboro_new_version.databinding.ItemProductLinearBinding

class ItemListLinearAdapter(private val onProductClicked:(Produts)->Unit):
    ListAdapter<Produts,ItemListLinearAdapter.ItemViewHolder>(DiffCallback) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            ItemProductLinearBinding.inflate(
                LayoutInflater.from(
                    parent.context
                )
            )
        )
    }

    override fun onBindViewHolder(holder: ItemListLinearAdapter.ItemViewHolder, position: Int) {
        val current = getItem(position)
        holder.itemView.setOnClickListener {
            onProductClicked(current)
        }
        holder.bind(current)
    }

    class ItemViewHolder(private var binding: ItemProductLinearBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Produts) {
            val bitmap: Bitmap = BitmapFactory.decodeByteArray(item.image_urlProduct, 0,
                item.image_urlProduct!!.size
            );
            binding.itemlinearImageview.load(bitmap)
            binding.itemlinearName.text = item.nameProduct
            binding.itemlinearPrice.text = item.getFormattedPrice()
            binding.itemlinearCategory.text = item.descriptionProduct.toString()
        }
    }


    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<Produts>() {
            override fun areItemsTheSame(oldItem: Produts, newItem: Produts): Boolean {
                return oldItem === newItem
            }

            override fun areContentsTheSame(oldItem: Produts, newItem: Produts): Boolean {
                return oldItem.nameProduct == newItem.nameProduct
            }
        }
    }
}