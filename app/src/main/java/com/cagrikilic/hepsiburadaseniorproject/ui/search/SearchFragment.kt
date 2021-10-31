package com.cagrikilic.hepsiburadaseniorproject.ui.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.children
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.cagrikilic.ItunesAppi
import com.cagrikilic.hepsiburadaseniorproject.SearchViewModel
import com.cagrikilic.hepsiburadaseniorproject.databinding.FragmentSearchBinding
import com.cagrikilic.hepsiburadaseniorproject.repo.SearchRepositoryImpl
import com.google.android.material.chip.Chip
import kotlinx.coroutines.launch

class SearchFragment : Fragment() {
    private lateinit var viewModel : SearchViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentSearchBinding.inflate(inflater)
        binding.lifecycleOwner = viewLifecycleOwner
        val repository = SearchRepositoryImpl(ItunesAppi)
        val viewModelFactory = SearchViewModelFactory(repository)
        viewModel = ViewModelProvider(
            this, viewModelFactory
        ).get(SearchViewModel::class.java)
        binding.viewModel =  viewModel
        val adapter = SearchFragmentAdapter(SearchFragmentAdapter.OnClickListener{
            viewModel.displayMediaDetails(it.trackId,it.kind)
        })
        binding.itemGrid.adapter = adapter

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.getMediaList(filter = "movie").observe(viewLifecycleOwner, {
                adapter.submitData(lifecycle, it)
            })
        }
        binding.searchEditText.doOnTextChanged { text, start, before, count ->
            if (text != null) {
                if(text.length >2){
                   val selectedChip =  binding.chipGroup.children
                       .toList()
                       .filter { (it as Chip).isChecked }[0] as Chip

                    viewLifecycleOwner.lifecycleScope.launch {
                        viewModel.getMediaList(term = text.toString(),filter = selectedChip.imeActionLabel.toString()).observe(viewLifecycleOwner, {
                            adapter.submitData(lifecycle, it)
                        })
                    }
                }
                if(text.isEmpty()){
                    val selectedChip =  binding.chipGroup.children
                        .toList()
                        .filter { (it as Chip).isChecked }[0] as Chip

                    viewLifecycleOwner.lifecycleScope.launch {
                        viewModel.getMediaList(filter = selectedChip.imeActionLabel.toString()).observe(viewLifecycleOwner, {
                            adapter.submitData(lifecycle, it)
                        })
                    }
                }
            }
        }
        binding.chipGroup.setOnCheckedChangeListener{
                chip, _ ->
            val text = binding.searchEditText.text
            val selectedChip = chip.children
            .toList()
            .filter { (it as Chip).isChecked }[0] as Chip
            if( text != null){
                if(text.length >2){
                    viewLifecycleOwner.lifecycleScope.launch {
                    viewModel.getMediaList(term = text.toString(),filter = selectedChip.imeActionLabel.toString()).observe(viewLifecycleOwner, {
                        adapter.submitData(lifecycle, it)
                    })
                }
                }
                else{
                    viewLifecycleOwner.lifecycleScope.launch {
                        viewModel.getMediaList(filter = selectedChip.imeActionLabel.toString()).observe(viewLifecycleOwner, {
                            adapter.submitData(lifecycle, it)
                        })
                    }
                }
            }


        }
        binding.viewModel?.navigateToSelectedMedia?.observe(viewLifecycleOwner, Observer {
            if ( null != it ) {
                if(it.mediaId != null){
                    this.findNavController().navigate(SearchFragmentDirections.actionSearchFragmentToDetailFragment(
                        it.mediaId!!,it.mediaType!!))
                    viewModel.displayMediaDetailsCompleted()
                }

            }
        })

        return binding.root

        // Kept it as a reference for our meeting

//        binding.chipApps.setOnCheckedChangeListener{chip,isChecked ->
//
//
//        }

//        binding.chipBooks.setOnCheckedChangeListener{chip,isChecked ->
//            if (isChecked) {
//
//            }
//
//        }

//        binding.chipMovies.setOnCheckedChangeListener{chip,isChecked ->
//            if (isChecked){
//                viewLifecycleOwner.lifecycleScope.launch {
//                    viewModel.getMediaList(filter = "movie").observe(viewLifecycleOwner, {
//                        adapter.submitData(lifecycle, it)
//                    })
//                }
//            }
//
//        }

//        binding.chipMusic.setOnCheckedChangeListener{chip,isChecked ->
//            if (isChecked){
//                viewLifecycleOwner.lifecycleScope.launch {
//                    viewModel.getMediaList(filter = "song").observe(viewLifecycleOwner, {
//                        adapter.submitData(lifecycle, it)
//                    })
//                }
//            }
//
//        }


    }


}