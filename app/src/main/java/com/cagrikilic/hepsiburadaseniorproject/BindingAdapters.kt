package com.cagrikilic.hepsiburadaseniorproject

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import org.w3c.dom.Text
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

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
    @BindingAdapter("formatDate")
    fun bindDate(textView:TextView,
                releaseDate : String?){
      releaseDate.let {
          val outputFormat: DateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.US)
          val inputFormat: DateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.US)
          val date: Date = inputFormat.parse(it)
          val outputText: String = outputFormat.format(date)
          textView.text = outputText
      }
    }
