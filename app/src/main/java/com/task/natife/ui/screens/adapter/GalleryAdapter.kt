package com.task.natife.ui.screens.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.task.natife.R
import com.task.natife.model.GifModel

class GalleryAdapter(
    private var onClickListener: (Int) -> Unit = {},
    private var onLongClickListener: (Int) -> Boolean = { true }
) : RecyclerView.Adapter<GalleryAdapter.ViewHolder>() {

    private var list = mutableListOf<GifModel>()
    private var mContext: Context? = null
    private var listItemView: Int? = null

    fun setOnClickListener(position: (Int) -> Unit) {
        onClickListener = position
    }

    fun setOnLongClickListener(position: (Int) -> Boolean = { true }) {
        onLongClickListener = position
    }

    @SuppressLint("NotifyDataSetChanged")
    fun initAdapter(userModel: List<GifModel>, itemLayout: Int, context: Context) {
        list.clear()
        list.addAll(userModel)

        mContext = context
        listItemView = itemLayout
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GalleryAdapter.ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(listItemView!!, parent, false)
        )
    }

    override fun onBindViewHolder(holder: GalleryAdapter.ViewHolder, position: Int) {
        holder.bind(list[position])

        holder.itemView.setOnLongClickListener {
            onLongClickListener(position)
        }

        holder.itemView.setOnClickListener {
            onClickListener(position)
        }
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val imageView: ImageView = view.findViewById(R.id.gif_view)

        fun bind(gifModel: GifModel) {
            Glide
                .with(mContext!!)
                .load(gifModel.gifUrl)
                .into(imageView)
        }
    }

    override fun getItemCount(): Int = list.size

}