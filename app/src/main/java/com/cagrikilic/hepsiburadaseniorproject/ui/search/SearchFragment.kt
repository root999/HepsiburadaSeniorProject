package com.cagrikilic.hepsiburadaseniorproject.ui.search

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.cagrikilic.ItunesAppi
import com.cagrikilic.hepsiburadaseniorproject.SearchViewModel
import com.cagrikilic.hepsiburadaseniorproject.databinding.FragmentSearchBinding
import com.cagrikilic.hepsiburadaseniorproject.repo.SearchRepositoryImpl
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
            viewModel.displayMediaDetails(it)
        })

        binding.chipApps.setOnCheckedChangeListener{chip,isChecked ->


        }
        binding.chipBooks.setOnCheckedChangeListener{chip,isChecked ->
            Log.d("chipBooks",chip.id.toString() +" " +isChecked)

        }
        binding.chipMovies.setOnCheckedChangeListener{chip,isChecked ->
            if (isChecked){
                viewLifecycleOwner.lifecycleScope.launch {
                    viewModel.getMediaList(filter = "movie").observe(viewLifecycleOwner, {
                        adapter.submitData(lifecycle, it)
                    })
                }
            }

        }
        binding.chipMusic.setOnCheckedChangeListener{chip,isChecked ->
            if (isChecked){
                viewLifecycleOwner.lifecycleScope.launch {
                    viewModel.getMediaList(filter = "music").observe(viewLifecycleOwner, {
                        adapter.submitData(lifecycle, it)
                    })
                }
            }

        }
        binding.itemGrid.adapter = adapter
        binding.viewModel?.navigateToSelectedMedia?.observe(viewLifecycleOwner, Observer {
            if ( null != it ) {
                this.findNavController().navigate(SearchFragmentDirections.actionSearchFragmentToDetailFragment(it))
                viewModel.displayMediaDetailsCompleted()
            }
        })

        return binding.root
    }


}