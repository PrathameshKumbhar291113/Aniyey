package com.prathameshkumbhar.aniyey.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.prathameshkumbhar.aniyey.connection.ApiCommunicator
import com.prathameshkumbhar.aniyey.connection.models.GetAnimeListResponse
import retrofit2.HttpException
import java.io.IOException

class AniyeyListPagingSource(
    private val apiCommunicator: ApiCommunicator,
    private val sortDescending: Boolean
) : PagingSource<Int, GetAnimeListResponse.Data>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, GetAnimeListResponse.Data> {
        val page = params.key ?: 1
        return try {
            val response = apiCommunicator.getAnimeList(page)
            val animeList = response.body()?.data?.filterNotNull() ?: emptyList()

            val sortedList = if (sortDescending) {
                animeList.sortedByDescending { it.score }
            } else {
                animeList.sortedBy { it.score }
            }

            val nextPage = if (response.body()?.pagination?.hasNextPage == true) page + 1 else null

            LoadResult.Page(
                data = sortedList,
                prevKey = if (page == 1) null else page - 1,
                nextKey = nextPage
            )
        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, GetAnimeListResponse.Data>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}
