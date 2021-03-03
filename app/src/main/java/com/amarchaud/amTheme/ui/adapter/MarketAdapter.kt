package com.amarchaud.amTheme.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.amarchaud.amTheme.databinding.ItemMarketBinding

class MarketAdapter : RecyclerView.Adapter<MarketAdapter.MarketViewHolder>() {

    var market: List<Triple<Int, String, String>> = ArrayList()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MarketAdapter.MarketViewHolder {
        val binding = ItemMarketBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MarketViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MarketAdapter.MarketViewHolder, position: Int) {
        val element = market[position]

        with(holder.binding) {
            this.image.setImageResource(element.first)
            this.title.text = element.second
            this.description.text = element.third
        }
    }

    override fun getItemCount(): Int = market.size

    inner class MarketViewHolder(var binding: ItemMarketBinding) :
        RecyclerView.ViewHolder(binding.root)
}