package com.bahaa.task.presentation.mainFragment.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.bahaa.task.R
import com.bahaa.task.databinding.FeaturedDealItemBinding
import com.bahaa.task.domain.models.remoteModels.FeaturedDeal

class FeaturedDealAdapter() :
    ListAdapter<FeaturedDeal, FeaturedDealAdapter.ItemViewHolder>(FeaturedDealDiffCallback) {

    private var onItemClick: (FeaturedDeal) -> Unit = {}


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ItemViewHolder(FeaturedDealItemBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(
            currentList[position],
            onItemClick
        )
    }

    override fun getItemCount(): Int = currentList.size

    fun setOnclickListener(clickItemLambda: (FeaturedDeal) -> Unit) {
        onItemClick = clickItemLambda
    }

    class ItemViewHolder(private val itemBinding: FeaturedDealItemBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(
            featuredDeal: FeaturedDeal,
            onItemClick: (FeaturedDeal) -> Unit
        ) {
            itemBinding.logo.load(
                uri = featuredDeal
                    .url
            ) {
                placeholder(R.drawable.top_store_img3)
            }
            itemBinding.dealPercentage.text = itemBinding.root.context.getString(R.string.deal_percentage,featuredDeal.dealPercentage,featuredDeal.dealBrandName)
            itemBinding.root.setOnClickListener { onItemClick(featuredDeal) }
        }
    }

    object FeaturedDealDiffCallback : DiffUtil.ItemCallback<FeaturedDeal>() {
        override fun areItemsTheSame(oldItem: FeaturedDeal, newItem: FeaturedDeal): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: FeaturedDeal, newItem: FeaturedDeal): Boolean {
            return oldItem == newItem
        }
    }
}