package com.carlos.newsproject.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.carlos.newsproject.R
import com.carlos.newsproject.data.model.ArticlesItem
import com.carlos.newsproject.databinding.ItemNoticeBinding

class HomeAdapter(val arrayList: ArrayList<ArticlesItem> = arrayListOf()): RecyclerView.Adapter<HomeAdapter.Holder>() {


    class Holder(private val itemBinding:ItemNoticeBinding):RecyclerView.ViewHolder(itemBinding.root){

        fun bind(item:ArticlesItem){
            itemBinding.titleNotice.text = item.title
            itemBinding.dateNotice.text = item.publishedAt
            itemBinding.textNotice.text = item.content

            Glide.with(itemView.context)
                .load(item.urlToImage)
                .placeholder(R.drawable.placeholder)
                .into(itemBinding.ivNotice)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
       val rootView = ItemNoticeBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return Holder(rootView)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(arrayList[position])
    }

    override fun getItemCount(): Int  = arrayList.size

    fun addItems(list: List<ArticlesItem?>?){
        list?.forEachIndexed{index, info ->
            if (info != null) {
                this.arrayList.add(info)
            }
        }
        notifyDataSetChanged()
    }

    override fun onViewAttachedToWindow(holder: Holder) {
        holder.setIsRecyclable(false)
        super.onViewAttachedToWindow(holder)
    }

    override fun onViewDetachedFromWindow(holder: Holder) {
        holder.setIsRecyclable(false)
        super.onViewDetachedFromWindow(holder)
    }
}