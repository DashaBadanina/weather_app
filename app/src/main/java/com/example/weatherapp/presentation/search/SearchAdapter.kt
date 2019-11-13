package com.example.weatherapp.presentation.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.R
import com.example.weatherapp.data.entity.SearchCity

class SearchAdapter : RecyclerView.Adapter<SearchViewHolder>() {

    var data: List<SearchCity> = arrayListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val viewItem = LayoutInflater.from(parent.context)
            .inflate(R.layout.search_view_holder, parent, false)
        return SearchViewHolder(viewItem)

    }

    override fun getItemCount(): Int = data.size


    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.update(data[position])
    }
}