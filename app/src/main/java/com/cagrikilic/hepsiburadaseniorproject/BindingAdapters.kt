package com.cagrikilic.hepsiburadaseniorproject

import android.view.View
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

@BindingAdapter("imageUrl")
    fun bindImage(imgView: ImageView, imgUrl: String?) {
        imgUrl?.let {
            val imgUri =
                imgUrl.toUri().buildUpon().scheme("https").build()
            Glide.with(imgView.context)
                .load(imgUri)
                .apply(
                    RequestOptions()
                        .placeholder(R.drawable.loading_animation)
                        .error(R.drawable.ic_broken_image))
                .into(imgView)
        }
    }
    @BindingAdapter("AppApiStatus")
    fun bindStatus(statusImageView: ImageView,
                   status: DataStatus?) {
        when(status){
            DataStatus.LOADING ->{
                statusImageView.visibility = View.VISIBLE
                statusImageView.setImageResource(R.drawable.loading_animation)
            }
            DataStatus.ERROR -> {
                statusImageView.visibility = View.VISIBLE
                statusImageView.setImageResource(R.drawable.ic_connection_error)
            }
            DataStatus.DONE -> {
                statusImageView.visibility = View.GONE
            }
        }
    }
//    @BindingAdapter("listData")
//    fun bindRecyclerView(recyclerView: RecyclerView,
//                         data: List<Movie>?) {
//        val adapter = recyclerView.adapter as SearchFragmentAdapter
//        adapter.submitList(data)
//    }
