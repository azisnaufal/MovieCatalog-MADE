package com.oazisn.moviecatalog.core.data.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.oazisn.moviecatalog.core.data.remote.network.MovieApiService
import com.oazisn.moviecatalog.core.domain.model.MoviePagingDomain

class MoviesPagingSource(
    private val service: MovieApiService,
) : PagingSource<Int, MoviePagingDomain>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MoviePagingDomain> {
        val page = params.key ?: 1
        return try {
            val response = service.getPopular(page)
            val movies = ArrayList<MoviePagingDomain>()
            response.results?.forEach { data ->
                movies.add(
                    MoviePagingDomain(
                        "https://image.tmdb.org/t/p/original${data?.posterPath}",
                        data?.title ?: "-",
                        data?.voteAverage ?: 0.0,
                        data?.id ?: 0
                    )
                )
            }
            LoadResult.Page(
                data = movies,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (page == response.totalPages) null else page + 1
            )
        } catch (exception: Exception) {
            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, MoviePagingDomain>): Int? {
        // Try to find the page key of the closest page to anchorPosition, from
        // either the prevKey or the nextKey, but you need to handle nullability
        // here:
        //  * prevKey == null -> anchorPage is the first page.
        //  * nextKey == null -> anchorPage is the last page.
        //  * both prevKey and nextKey null -> anchorPage is the initial page, so
        //    just return null.
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}
