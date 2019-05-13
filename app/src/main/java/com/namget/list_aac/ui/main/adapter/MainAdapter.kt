package com.namget.list_aac.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.namget.list_aac.R
import com.namget.list_aac.data.model.Photo
import com.namget.list_aac.databinding.ItemImageLinearBinding
import com.namget.list_aac.ui.base.BaseAdapterInf

class MainAdapter(val list: ArrayList<Photo>) : RecyclerView.Adapter<MainAdapter.mViewHodler>(),
    BaseAdapterInf<ArrayList<Photo>> {

    class mViewHodler(view: ItemImageLinearBinding) : RecyclerView.ViewHolder(view.root) {
        fun bind() {

        }
    }

    override fun addItems(items: ArrayList<Photo>) {
        val before = list.size
        list.addAll(items)
        notifyItemChanged(before, items.size)
    }

    override fun setItems(items: ArrayList<Photo>) {
        clear()
        this.list.addAll(items)
        notifyDataSetChanged()
    }

    override fun clear() {
        list.clear()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): mViewHodler {
        val item: ItemImageLinearBinding =
            DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_image_linear, parent, false)
        return mViewHodler(item)
    }

    override fun getItemCount(): Int = list.size
    override fun onBindViewHolder(holder: mViewHodler, position: Int) {
        holder.bind()
    }
}