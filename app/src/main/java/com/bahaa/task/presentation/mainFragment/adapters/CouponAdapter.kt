package com.bahaa.task.presentation.mainFragment.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.bahaa.task.R
import com.bahaa.task.databinding.CouponItemBinding
import com.bahaa.task.domain.models.remoteModels.Coupon

class CouponAdapter() :
    ListAdapter<Coupon, CouponAdapter.ItemViewHolder>(CouponDiffCallback) {

    private var onItemClick: (Coupon) -> Unit = {}


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ItemViewHolder(CouponItemBinding.inflate(inflater, parent, false))
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

    class ItemViewHolder(private val itemBinding: CouponItemBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(
            coupon: Coupon,
            onItemClick: (Coupon) -> Unit
        ) {
            itemBinding.brandLogo.load(
                uri = coupon
                    .url
            ) {

                placeholder(R.drawable.top_store_img3)
            }
            itemBinding.couponPercentage.text = itemBinding.root.context.getString(R.string.up_to_50_off,coupon.couponPercentage)
            itemBinding.root.setOnClickListener { onItemClick(coupon) }
        }
    }

    object CouponDiffCallback : DiffUtil.ItemCallback<Coupon>() {
        override fun areItemsTheSame(oldItem: Coupon, newItem: Coupon): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Coupon, newItem: Coupon): Boolean {
            return oldItem == newItem
        }
    }
}