package com.namget.list_aac.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.namget.list_aac.R
import com.namget.list_aac.data.model.Photo
import com.namget.list_aac.databinding.ItemImageHolderBinding
import com.namget.list_aac.ui.base.BaseAdapterInf
import com.namget.list_aac.ui.base.RecyclerItemListener
import com.namget.list_aac.util.setImageWithGlide

class MainAdapter(val list: ArrayList<Photo>) :
    RecyclerView.Adapter<MainAdapter.mViewHolder>(),
    BaseAdapterInf<ArrayList<Photo>> {

    private lateinit var recyclerItemListener: RecyclerItemListener

    inner class mViewHolder(val view: ItemImageHolderBinding) : RecyclerView.ViewHolder(view.root) {
        var ImageHolder: ImageView

        init {
            ImageHolder = view.ImageHolder
            view.rippleHolder.setOnClickListener {
                recyclerItemListener.itemClick(ImageHolder, list[adapterPosition].urls.full)
            }
        }

        fun bind(photo: Photo) {
            view.photo = photo
            ImageHolder.setImageWithGlide(photo.urls.thumb)
        }
    }

    fun setItemClickListener(recyclerItemListener: RecyclerItemListener) {
        this.recyclerItemListener = recyclerItemListener
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): mViewHolder {
        val item: ItemImageHolderBinding =
            DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_image_holder, parent, false)
        return mViewHolder(item)
    }

    override fun getItemCount(): Int = list.size
    override fun onBindViewHolder(holder: mViewHolder, position: Int) {
        holder.bind(list[position])
    }
}