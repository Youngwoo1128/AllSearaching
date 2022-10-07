package com.woojoo.allsearching.ui.ViewHolder

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.woojoo.allsearching.R
import com.woojoo.allsearching.databinding.ItemVideoResultBinding
import com.woojoo.allsearching.domain.entites.Documents
import com.woojoo.allsearching.ui.adapter.SearchingResultAdapter

class VideoResultViewHolder(val binding: ItemVideoResultBinding): RecyclerView.ViewHolder(binding.root) {
    fun onBind(item: ArrayList<Documents>, position: Int, callback: SearchingResultAdapter.InsertSearchingData) {
        binding.item = item[position]

        Glide.with(binding.root.context)
            .load(item[position].thumbnail)
            .placeholder(R.drawable.default_image)
            .circleCrop()
            .into(binding.ivThumnail)

        binding.ivFavorite.setOnClickListener {
            callback.onInsertSearchingData(item[position])
        }
    }
}