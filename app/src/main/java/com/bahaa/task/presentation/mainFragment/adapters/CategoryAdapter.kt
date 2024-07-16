package com.bahaa.task.presentation.mainFragment.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.bahaa.task.R
import com.bahaa.task.databinding.CategoryItemBinding
import com.bahaa.task.domain.models.remoteModels.Category

class CategoryAdapter() :
    ListAdapter<Category, CategoryAdapter.ItemViewHolder>(CategoryDiffCallback) {

    private var onItemClick: (Category) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ItemViewHolder(CategoryItemBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(currentList[position],position == currentList.size-1,onItemClick)
    }

    override fun getItemCount(): Int = currentList.size

    fun setOnclickListener(clickItemLambda: (Category) -> Unit){
        onItemClick = clickItemLambda
    }

    class ItemViewHolder(private val itemBinding: CategoryItemBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(category: Category, isLast: Boolean,onItemClick:(Category)->Unit) {
            itemBinding.logo.load(category.image){
                placeholder(R.drawable.category_sample)
            }
            itemBinding.category.text = category.name
            itemBinding.root.setOnClickListener {
                onItemClick(category)
            }
        }
    }

    object CategoryDiffCallback : DiffUtil.ItemCallback<Category>() {
        override fun areItemsTheSame(oldItem: Category, newItem: Category): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Category, newItem: Category): Boolean {
            return oldItem == newItem
        }
    }
}