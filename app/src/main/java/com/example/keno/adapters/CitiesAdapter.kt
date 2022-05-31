package com.example.keno.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.api.client.City
import com.example.keno.adapters.vh.CitiesViewHolder
import com.example.keno.adapters.vh.CityDisplay
import com.example.keno.databinding.InflateCityBinding
import com.kartik.grevocab.adapters.BaseBindingViewHolder
import com.kartik.grevocab.adapters.OnBindClickListener
import java.util.ArrayList

class CitiesAdapter(private val clickListener: OnBindClickListener) : RecyclerView.Adapter<BaseBindingViewHolder>() {
    private var items: ArrayList<CityDisplay> = ArrayList()

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: BaseBindingViewHolder, position: Int) {
        holder as CitiesViewHolder
        holder.binding.item = items[position]
        holder.binding.executePendingBindings()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseBindingViewHolder {
        return CitiesViewHolder(
            InflateCityBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            clickListener
        )
    }

    fun replaceData(groupItems: ArrayList<CityDisplay>) {
        this.items = groupItems
        notifyDataSetChanged()
    }
}
