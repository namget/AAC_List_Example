package com.namget.list_aac.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.namget.list_aac.R
import com.namget.list_aac.data.model.Photo
import com.namget.list_aac.databinding.ItemImageGridBinding
import com.namget.list_aac.ui.base.BaseAdapterInf
import com.namget.list_aac.util.setImageWithGlide

class MainAdapter(val list: ArrayList<Photo>) : RecyclerView.Adapter<MainAdapter.mViewHodler>(),
    BaseAdapterInf<ArrayList<Photo>> {

    class mViewHodler(val view: ItemImageGridBinding) : RecyclerView.ViewHolder(view.root) {
        var gridImage: ImageView

        init {
            gridImage = view.girdImage
        }

        fun bind(photo: Photo) {
            gridImage.setImageWithGlide(photo.urls.thumb)
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
        val item: ItemImageGridBinding =
            DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_image_grid, parent, false)
        return mViewHodler(item)
    }

    override fun getItemCount(): Int = list.size
    override fun onBindViewHolder(holder: mViewHodler, position: Int) {
        holder.bind(list[position])
    }
}