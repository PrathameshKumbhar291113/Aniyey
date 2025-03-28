package com.prathameshkumbhar.aniyey.features.anime_details.presentation.manager

import androidx.core.view.isVisible
import com.prathameshkumbhar.aniyey.databinding.FragmentAnimeDetailsBinding

fun FragmentAnimeDetailsBinding.showTrailerAndHidePoster() {
    webViewTrailer.isVisible = true
    posterImage.isVisible = false
}

fun FragmentAnimeDetailsBinding.hideTrailerAndShowPoster() {
    webViewTrailer.isVisible = false
    posterImage.isVisible = true
}


fun FragmentAnimeDetailsBinding.showProgressBarAndHideAnimeDetailsContainer() {
    animeDetailsContainer.isVisible = false
    progressBar.isVisible = true
}

fun FragmentAnimeDetailsBinding.hideProgressBarAndShowAnimeDetailsContainer() {
    animeDetailsContainer.isVisible = true
    progressBar.isVisible = false
}