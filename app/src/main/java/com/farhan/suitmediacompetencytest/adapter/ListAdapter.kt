package com.farhan.suitmediacompetencytest.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.farhan.suitmediacompetencytest.R
import com.farhan.suitmediacompetencytest.databinding.ItemUserBinding
import com.farhan.suitmediacompetencytest.model.ItemModel

class ListAdapter (private val list: ArrayList<ItemModel>): RecyclerView.Adapter<ListAdapter.ViewHolder>() {

    private var onItemClickCallback : OnItemClickCallback? = null

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    inner class ViewHolder (private val binding: ItemUserBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ItemModel) {
            with(binding) {
                tvUsername.text = R.string.tv_username.toString()
                tvEmail.text = item.email
                Glide.with(itemView.context)
                    .load(item.avatar)
                    .apply(RequestOptions())
                    .into(imgUser)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int =list.size

    interface OnItemClickCallback {
        fun onItemClicked(data: ItemModel)
    }
}