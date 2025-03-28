package com.prathameshkumbhar.aniyey.features.anime_list.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.prathameshkumbhar.aniyey.R
import com.prathameshkumbhar.aniyey.connection.models.GetAnimeListResponse
import com.prathameshkumbhar.aniyey.databinding.FragmentAnimeListBinding
import com.prathameshkumbhar.aniyey.features.anime_details.presentation.fragment.AnimeDetailsFragment.Companion.ANIME_ID
import com.prathameshkumbhar.aniyey.features.anime_list.presentation.adapter.AnimeListAdapter
import com.prathameshkumbhar.aniyey.features.anime_list.presentation.viewmodel.AnimeListViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AnimeListFragment : Fragment() {
    private lateinit var binding: FragmentAnimeListBinding
    private lateinit var animeListAdapter: AnimeListAdapter
    private val animeListViewModel: AnimeListViewModel by activityViewModels()
    private val navController: NavController by lazy {
        findNavController()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAnimeListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupUi()
        setupObserver()
        setupAdapter()
    }

    private fun setupUi() {

    }

    private fun setupObserver() {
        with(animeListViewModel) {
            isNetworkAvailable.observe(viewLifecycleOwner) {
                when(it){
                    true -> {
                        lifecycleScope.launch {
                            repeatOnLifecycle(Lifecycle.State.STARTED) {
                                animeListViewModel.animeList.collectLatest { animeList ->
                                    animeListAdapter.submitData(animeList)
                                }
                            }
                        }
                    }
                    false -> {
                        //can handle the error with dialog or bottom sheet for user to enable internet.
                        Toast.makeText(requireContext(), "No Internet Connection", Toast.LENGTH_SHORT).show()
                    }
                    else -> {
                        Toast.makeText(requireContext(), "Something Went Wrong", Toast.LENGTH_SHORT).show()
                    }
                }
            }



        }
    }

    private fun setupAdapter() {
        animeListAdapter = AnimeListAdapter(this::onNavigateToAnimeDetailsScreen)
        binding.animeRecyclerView.adapter = animeListAdapter
    }

    private fun onNavigateToAnimeDetailsScreen(data: GetAnimeListResponse.Data) {
        navController.navigate(
            R.id.animeDetailsFragment, bundleOf(ANIME_ID to data.malId)
        )
    }
}