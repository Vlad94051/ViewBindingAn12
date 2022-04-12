package com.tms.viewbindingan12

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tms.viewbindingan12.databinding.ItemOmegaBinding

class OmegaViewHolder(binding: ItemOmegaBinding)
    : RecyclerView.ViewHolder(binding.root) {

        companion object {
            fun newInstance(parent: ViewGroup) = OmegaViewHolder(
               ItemOmegaBinding.inflate(
                   LayoutInflater.from(parent.context),
                   parent,
                   false
               )
            )
        }
}