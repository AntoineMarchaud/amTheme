package com.amarchaud.amTheme.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.amarchaud.amTheme.databinding.ItemTeamBinding
import com.amarchaud.amTheme.ui.interfaces.ITeamClickListener

class TeamAdapter(val listener: ITeamClickListener) :
    RecyclerView.Adapter<TeamAdapter.TeamViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamAdapter.TeamViewHolder {
        val binding = ItemTeamBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TeamViewHolder(binding)
    }

    var team: List<Triple<Int, String, String>> = ArrayList()

    override fun onBindViewHolder(holder: TeamAdapter.TeamViewHolder, position: Int) {

        val element = team[position]

        with(holder.binding) {

            this.image.setImageResource(element.first)
            this.title.text = element.second
            this.description.text = element.third

            holder.itemView.setOnClickListener {
                listener.onClick(element)
            }

        }
    }

    override fun getItemCount(): Int = team.size

    inner class TeamViewHolder(var binding: ItemTeamBinding) :
        RecyclerView.ViewHolder(binding.root)


}