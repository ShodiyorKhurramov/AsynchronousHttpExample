package com.example.asynchronoushttpexample.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.asynchronoushttpexample.R
import com.example.asynchronoushttpexample.model.WelcomeElement
import com.squareup.picasso.Picasso


class CatsAdapter(var context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var photoList = ArrayList<WelcomeElement>()

    @SuppressLint("NotifyDataSetChanged")
    fun addPhotos(photoList: Array<WelcomeElement>) {
        this.photoList.addAll(photoList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_feed, parent, false)
        return PhotoViewHolder(view)
    }

    class PhotoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val ivItem: ImageView = view.findViewById(R.id.iv_photo)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        val photoItem = photoList[position]

        val photoUrl = photoItem.url


        if (holder is PhotoViewHolder) {

            Picasso.get().load(photoUrl)
                .into(holder.ivItem)

        }

    }

    override fun getItemCount(): Int {
        return photoList.size
    }
}