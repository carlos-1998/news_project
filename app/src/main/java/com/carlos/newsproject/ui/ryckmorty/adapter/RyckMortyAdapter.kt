package com.carlos.newsproject.ui.ryckmorty.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.carlos.newsproject.R
import com.carlos.newsproject.data.model.ResultsItem
import com.carlos.newsproject.databinding.ItemCharacterBinding

class RyckMortyAdapter(val arrayList: ArrayList<ResultsItem> = arrayListOf()):RecyclerView.Adapter<RyckMortyAdapter.Holder>() {


    class Holder(private val itemBindin:ItemCharacterBinding):RecyclerView.ViewHolder(itemBindin.root){

        fun bind(item:ResultsItem){
            itemBindin.nameCharacter.text = "Name: ${item.name}"
            itemBindin.ubiCharacter.text = "Location: ${item.location?.name}"

            Glide.with(itemView.context)
                .load(item.image)
                .placeholder(R.drawable.placeholder)
                .into(itemBindin.ivCharacter)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val rootView = ItemCharacterBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return Holder(rootView)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(arrayList[position])
    }

    override fun getItemCount(): Int  = arrayList.size

    override fun onViewAttachedToWindow(holder: Holder) {
        holder.setIsRecyclable(false)
        super.onViewAttachedToWindow(holder)
    }

    override fun onViewDetachedFromWindow(holder: Holder) {
        holder.setIsRecyclable(false)
        super.onViewDetachedFromWindow(holder)
    }


    fun addItems(list: List<ResultsItem?>?){
        list?.forEachIndexed{index, character ->
            if (character != null) {
                this.arrayList.add(character)
            }
        }
        notifyDataSetChanged()
    }
}