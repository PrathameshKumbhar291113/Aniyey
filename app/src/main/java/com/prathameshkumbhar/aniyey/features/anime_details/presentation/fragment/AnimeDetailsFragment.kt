package com.prathameshkumbhar.aniyey.features.anime_details.presentation.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Html
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.core.text.HtmlCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import coil.load
import com.prathameshkumbhar.aniyey.R
import com.prathameshkumbhar.aniyey.connection.models.GetAnimeDetailsByIdResponse
import com.prathameshkumbhar.aniyey.databinding.FragmentAnimeDetailsBinding
import com.prathameshkumbhar.aniyey.features.anime_details.presentation.manager.hideProgressBarAndShowAnimeDetailsContainer
import com.prathameshkumbhar.aniyey.features.anime_details.presentation.manager.hideTrailerAndShowPoster
import com.prathameshkumbhar.aniyey.features.anime_details.presentation.manager.showProgressBarAndHideAnimeDetailsContainer
import com.prathameshkumbhar.aniyey.features.anime_details.presentation.manager.showTrailerAndHidePoster
import com.prathameshkumbhar.aniyey.features.anime_details.presentation.viewmodel.AnimeDetailsViewModel
import com.prathameshkumbhar.aniyey.utils.NetworkResponseCodes
import com.prathameshkumbhar.aniyey.utils.NetworkResult
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AnimeDetailsFragment : Fragment() {
    private lateinit var binding: FragmentAnimeDetailsBinding
    private val animeDetailsViewModel: AnimeDetailsViewModel by activityViewModels()
    private val navController: NavController by lazy {
        findNavController()
    }

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
        handleBackPress()
    }

    private fun getArgumentFromAnimeListScreen() {
        animeDetailsViewModel.setAnimeId(arguments?.getInt(ANIME_ID) ?: -1)
    }


    private fun setupUi() {
        binding.showProgressBarAndHideAnimeDetailsContainer()
    }

    private fun setupObserver() {
        with(animeDetailsViewModel) {

            isNetworkAvailable.observe(viewLifecycleOwner) {
                when (it) {
                    true -> {
                        //Do nothing
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

            animeId.observe(viewLifecycleOwner) {
                it?.let { animeId ->
                    getAnimeDetailsById(animeId)
                }
            }

            animeDetails.observe(viewLifecycleOwner) {
                when (it) {
                    is NetworkResult.Loading -> {}
                    is NetworkResult.Success -> {
                        binding.hideProgressBarAndShowAnimeDetailsContainer()
                        when (it.data?.code()) {
                            NetworkResponseCodes.SUCCESS_RESPONSE_CODE_200 -> {
                                it.data.body()?.data?.let { animeDetails ->
                                    bindAnimeDetails(animeDetails)
                                }

                            }

                            NetworkResponseCodes.INTERNAL_SERVER_ERROR_CODE_500 -> {}
                        }
                    }

                    is NetworkResult.Error -> {
                        Log.e(
                            "AnimeListFragment",
                            "AnimeListFragment: ${it.message.toString()}"
                        )
                    }

                    else -> {}
                }
            }
        }

    }

    private fun bindAnimeDetails(anime: GetAnimeDetailsByIdResponse.Data) {

        binding.titleText.text = Html.fromHtml(
            requireContext().getString(
                R.string.anime_title,
                anime.title.toString()
            ),
            HtmlCompat.FROM_HTML_MODE_LEGACY
        )
        binding.synopsisText.text = Html.fromHtml(
            requireContext().getString(
                R.string.anime_synopsis,
                anime.synopsis.toString()
            ),
            HtmlCompat.FROM_HTML_MODE_LEGACY
        )

        binding.genreText.text = Html.fromHtml(
            requireContext().getString(
                R.string.anime_genres,
                anime.genres?.filterNotNull()
                    ?.joinToString(", ") { it.name.toString() } ?: "No Genres Available"
            ),
            HtmlCompat.FROM_HTML_MODE_LEGACY
        )

        binding.episodesText.text = Html.fromHtml(
            requireContext().getString(
                R.string.anime_episodes,
                anime.episodes.toString()
            ),
            HtmlCompat.FROM_HTML_MODE_LEGACY
        )

        binding.ratingText.text = Html.fromHtml(
            requireContext().getString(
                R.string.anime_rating,
                anime.rating.toString()
            ),
            HtmlCompat.FROM_HTML_MODE_LEGACY
        )

        if (anime.trailer?.embedUrl?.isNotEmpty() == true) {
            showTrailer(anime.trailer?.embedUrl)
        } else {
            anime.images?.jpg?.largeImageUrl?.let {
                binding.posterImage.load(it)
            }
            binding.hideTrailerAndShowPoster()
        }
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun showTrailer(url: String?) {
        binding.webViewTrailer.apply {
            settings.javaScriptEnabled = true
            settings.mediaPlaybackRequiresUserGesture = false
            webViewClient = WebViewClient()
            url?.let { embeddedUrl ->
                loadUrl(embeddedUrl)
            }
        }
        binding.showTrailerAndHidePoster()
    }

    private fun handleBackPress() {
        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    animeDetailsViewModel.clearAnimeDetails()
                    binding.hideProgressBarAndShowAnimeDetailsContainer()
                    navController.popBackStack()
                }
            })
    }

}