package com.prathameshkumbhar.aniyey.features.anime_list.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.prathameshkumbhar.aniyey.connection.models.GetAnimeListResponse
import com.prathameshkumbhar.aniyey.databinding.ItemAnimeCardBinding

class AnimeListAdapter(
    private val onNavigateToAnimeDetailsScreen: (data: GetAnimeListResponse.Data) -> Unit
) :
    PagingDataAdapter<GetAnimeListResponse.Data, AnimeListAdapter.AnimeListViewHolder>(
        diffUtilComparator
    ) {

    companion object {
        private val diffUtilComparator =
            object : DiffUtil.ItemCallback<GetAnimeListResponse.Data>() {
                override fun areItemsTheSame(
                    oldItem: GetAnimeListResponse.Data,
                    newItem: GetAnimeListResponse.Data
                ): Boolean {
                    return oldItem.malId == newItem.malId
                }

                override fun areContentsTheSame(
                    oldItem: GetAnimeListResponse.Data,
                    newItem: GetAnimeListResponse.Data
                ): Boolean {
                    return oldItem == newItem
                }

            }
    }


    inner class AnimeListViewHolder(private val binding: ItemAnimeCardBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindAnimeCard(data: GetAnimeListResponse.Data) {
            binding.titleText.text = data.title.toString()
            binding.ratingText.text = data.rating?.split(" -")?.first()?.trim().toString()
            binding.episodeCountText.text = data.episodes.toString()
            data.images?.jpg?.imageUrl?.let {
                binding.animePosterImage.load(it)
            }

            binding.animeCard.setOnClickListener {
                onNavigateToAnimeDetailsScreen(data)
            }

        }
    }

    override fun onBindViewHolder(holder: AnimeListViewHolder, position: Int) {
        getItem(position)?.let { holder.bindAnimeCard(it) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimeListViewHolder {
        return AnimeListViewHolder(
            ItemAnimeCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }
}