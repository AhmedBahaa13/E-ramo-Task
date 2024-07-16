package com.bahaa.task.presentation.mainFragment.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.bahaa.task.R
import com.bahaa.task.databinding.BestDealItemBinding
import com.bahaa.task.domain.models.remoteModels.Coupon

class BestDealsAdapter() :
    ListAdapter<Coupon, BestDealsAdapter.ItemViewHolder>(CouponDealDiffCallback) {

    private var onItemClick: (Coupon) -> Unit = {}


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ItemViewHolder(BestDealItemBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(
            currentList[position],
            onItemClick
        )
    }

    override fun getItemCount(): Int = currentList.size

    fun setOnclickListener(clickItemLambda: (Coupon) -> Unit) {
        onItemClick = clickItemLambda
    }

    class ItemViewHolder(private val itemBinding: BestDealItemBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(
            coupon: Coupon,
            onItemClick: (Coupon) -> Unit
        ) {
            itemBinding.logo.load(
                uri = coupon
                    .url
            ) {
                placeholder(R.drawable.top_store_img3)
            }
            itemBinding.dealPercentage.text = itemBinding.root.context.getString(R.string.best_deal_percentage,coupon.couponPercentage,"10")
            itemBinding.root.setOnClickListener { onItemClick(coupon) }
        }
    }

    object CouponDealDiffCallback : DiffUtil.ItemCallback<Coupon>() {
        override fun areItemsTheSame(oldItem: Coupon, newItem: Coupon): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Coupon, newItem: Coupon): Boolean {
            return oldItem == newItem
        }
    }
}