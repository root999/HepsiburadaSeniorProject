package com.cagrikilic.hepsiburadaseniorproject.ui.detail

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.text.HtmlCompat

import com.cagrikilic.hepsiburadaseniorproject.databinding.DetailFragmentBinding


class DetailFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DetailFragmentBinding.inflate(inflater)
        binding.lifecycleOwner = this
        val mediaId = DetailFragmentArgs.fromBundle(requireArguments()).mediaId
        val mediaType = DetailFragmentArgs.fromBundle(requireArguments()).mediaType
        val viewModelFactory = DetailViewModelFactory(mediaId,mediaType)
        val viewModel = ViewModelProvider(
            this, viewModelFactory
        ).get(DetailViewModel::class.java)
        binding.viewModel = viewModel
        when(mediaType){
            "feature-movie"->{
                viewModel.selectedMovie.observe(viewLifecycleOwner,{
                    binding.apply {
                        releaseDateTextView.text = viewModel.parseDate(it.releaseDate)
                        mediaTextView.text = it.trackName
                        genreTextView.text = it.primaryGenreName
                        detailTextView.text = it.longDescription
                        priceTextView.text = it.trackPrice.toString() + "$"
                    }
                })
            }
            "song" ->{
                viewModel.selectedMusic.observe(viewLifecycleOwner,{
                    binding.apply {
                        releaseDateTextView.text = viewModel.parseDate(it.releaseDate)
                        mediaTextView.text = it.trackName
                        genreTextView.text = it.primaryGenreName
                        detailLabelTextView.text = "Artist"
                        detailTextView.text = it.artistName
                        priceTextView.text = it.trackPrice.toString() + "$"
                    }
                })

            }
            "ebook" ->{
                viewModel.selectedEbook.observe(viewLifecycleOwner,{
                    binding.apply {
                        releaseDateTextView.text = viewModel.parseDate(it.releaseDate)
                        mediaTextView.text = it.trackName
                        genreTextView.text = it.genres.toString()
                        detailTextView.text = HtmlCompat.fromHtml(it.description.toString(), HtmlCompat.FROM_HTML_MODE_COMPACT)
                        priceTextView.text = it.formattedPrice.toString() + "$"
                    }
                })

            }
            "software"->{
                viewModel.selectedSoftware.observe(viewLifecycleOwner,{
                    binding.apply {
                        releaseDateLabelTextView.text = "Current Version Release Date"
                        releaseDateTextView.text = viewModel.parseDate(it.currentVersionReleaseDate)
                        mediaTextView.text = it.trackName
                        genreTextView.text = it.genres.toString()
                        detailTextView.text = HtmlCompat.fromHtml(it.description.toString(), HtmlCompat.FROM_HTML_MODE_COMPACT)
                        priceTextView.text = if(it.formattedPrice.toString() != "Free") (it.formattedPrice.toString() + "$") else (it.formattedPrice.toString())
                    }
                })
            }
        }




        return binding.root
    }



}