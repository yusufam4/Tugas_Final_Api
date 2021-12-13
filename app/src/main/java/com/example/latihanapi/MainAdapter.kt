package com.example.latihanapi

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.adapter_main.view.*

class MainAdapter (val results : ArrayList<MainModel.Result>, val listener: onAdapterListener)
    : RecyclerView.Adapter<MainAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder (
        LayoutInflater.from(parent.context).inflate(R.layout.adapter_main, parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val result = results[position]
        holder.view.textView.text = result.title
        Glide.with(holder.view)
            .load(result.thumb)
            .placeholder(R.drawable.img_placeholder)
            .centerCrop()
            .into(holder.view.imageView)
        holder.view.setOnClickListener{
            listener.onClick(result)
        }
    }

    override fun getItemCount() = results.size

    class ViewHolder (val view : View): RecyclerView.ViewHolder(view)

    fun setData(data : List<MainModel.Result>){
        results.clear()
        results.addAll(data)
        notifyDataSetChanged()
    }

    interface onAdapterListener{
        fun onClick(result : MainModel.Result)
    }

}