package com.bahaa.task.presentation.mainFragment.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.bahaa.task.R
import com.bahaa.task.databinding.TopStoreItemBinding
import com.bahaa.task.domain.models.remoteModels.TopStore

class TopStoresAdapter() :
    ListAdapter<TopStore, TopStoresAdapter.ItemViewHolder>(TopStoreDiffCallback) {

    private var onItemClick: (TopStore) -> Unit = {}


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ItemViewHolder(TopStoreItemBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(
            currentList[position],
            onItemClick
        )
    }

    override fun getItemCount(): Int = currentList.size

//    override fun submitList(list: MutableList<TopStore>?) {
//        list?.add(0, TopStore())
//        super.submitList(list)
//    }

    fun setOnclickListener(clickItemLambda: (TopStore) -> Unit) {
        onItemClick = clickItemLambda
    }

    class ItemViewHolder(private val itemBinding: TopStoreItemBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(
            store: TopStore,
            onItemClick: (TopStore) -> Unit
        ) {
            itemBinding.storeImage.load(
                uri = store
                    .url
            ) {
                placeholder(R.drawable.top_store_img3)
            }
            itemBinding.root.setOnClickListener { onItemClick(store) }
        }
    }

    object TopStoreDiffCallback : DiffUtil.ItemCallback<TopStore>() {
        override fun areItemsTheSame(oldItem: TopStore, newItem: TopStore): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: TopStore, newItem: TopStore): Boolean {
            return oldItem == newItem
        }
    }
}