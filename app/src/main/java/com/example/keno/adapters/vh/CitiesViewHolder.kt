package com.example.keno.adapters.vh

import android.view.View
import com.example.keno.databinding.InflateCityBinding
import com.kartik.grevocab.adapters.BaseBindingViewHolder
import com.kartik.grevocab.adapters.OnBindClickListener

class CitiesViewHolder(val binding: InflateCityBinding, private val clickListener: OnBindClickListener?=null) : BaseBindingViewHolder(binding), View.OnClickListener {

    init {
        itemView.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        clickListener?.onItemClick(v, this.adapterPosition, binding.item as Any)
    }
}
