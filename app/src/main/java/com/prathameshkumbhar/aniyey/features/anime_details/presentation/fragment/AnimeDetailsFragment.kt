package com.prathameshkumbhar.aniyey.features.anime_details.presentation.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.media3.exoplayer.ExoPlayer
import coil.load
import com.prathameshkumbhar.aniyey.connection.models.GetAnimeDetailsByIdResponse
import com.prathameshkumbhar.aniyey.databinding.FragmentAnimeDetailsBinding
import com.prathameshkumbhar.aniyey.features.anime_details.presentation.viewmodel.AnimeDetailsViewModel
import com.prathameshkumbhar.aniyey.utils.NetworkResponseCodes
import com.prathameshkumbhar.aniyey.utils.NetworkResult
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AnimeDetailsFragment : Fragment() {
    private lateinit var binding: FragmentAnimeDetailsBinding
    private val animeDetailsViewModel: AnimeDetailsViewModel by activityViewModels()
    private var player: ExoPlayer? = null

    companion object {
        const val ANIME_ID: String = "animeId"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAnimeDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupUi()
        setupObserver()
        getArgumentFromAnimeListScreen()
    }

    private fun getArgumentFromAnimeListScreen() {
        animeDetailsViewModel.setAnimeId(arguments?.getInt(ANIME_ID) ?: -1)
    }


    private fun setupUi() {

    }

    private fun setupObserver() {
        with(animeDetailsViewModel) {
            isNetworkAvailable.observe(viewLifecycleOwner) {
                when (it) {
                    true -> {
                        getAnimeDetailsById()
                    }

                    false -> {
                        //can handle the error with dialog or bottom sheet for user to enable internet.
                        Toast.makeText(
                            requireContext(),
                            "No Internet Connection",
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                    else -> {
                        Toast.makeText(requireContext(), "Something Went Wrong", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            }

            animeDetails.observe(viewLifecycleOwner) {
                when (it) {
                    is NetworkResult.Loading -> {}
                    is NetworkResult.Success -> {
                        when (it.data?.code()) {
                            NetworkResponseCodes.SUCCESS_RESPONSE_CODE_200 -> {
                                Log.e(
                                    "ANIME_DETAILS",
                                    "setupObserver: ${it.data.body()?.data.toString()}",
                                )
                                it.data.body()?.data?.let { animeDetails ->
                                    bindAnimeDetails(animeDetails)
                                }

                            }

                            NetworkResponseCodes.INTERNAL_SERVER_ERROR_CODE_500 -> {}
                        }
                    }

                    is NetworkResult.Error -> {}
                }
            }
        }

    }

    private fun bindAnimeDetails(anime: GetAnimeDetailsByIdResponse.Data) {
        binding.titleText.text = anime.title
        binding.synopsisText.text = anime.synopsis
        binding.genreText.text = anime.genres?.joinToString(", ")
        binding.castText.text = anime.broadcast.toString()
        binding.episodesText.text = "Episodes: ${anime.episodes}"
        binding.ratingText.text = "Rating: ${anime.rating}"

        if (anime.trailer?.embedUrl != null) {
            showTrailer(anime.trailer?.embedUrl)
        } else {
            anime.images?.jpg?.largeImageUrl?.let {
                binding.posterImage.load(it)
            }

            binding.webViewTrailer.isVisible = false
            binding.posterImage.isVisible = true
        }
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun showTrailer(url: String?) {
        binding.webViewTrailer.apply {
            settings.javaScriptEnabled = true
            webViewClient = WebViewClient()
            url?.let { embeddedUrl ->
                loadUrl(embeddedUrl)
            }
        }
        binding.webViewTrailer.isVisible = true
        binding.posterImage.isVisible = false
    }
}