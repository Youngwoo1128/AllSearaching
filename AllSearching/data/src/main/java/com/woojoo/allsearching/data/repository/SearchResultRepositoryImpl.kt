package com.woojoo.allsearching.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.woojoo.allsearching.data.datasources.SearchingDataSource
import com.woojoo.allsearching.data.network.NetworkAPI
import com.woojoo.allsearching.data.paging.SearchingPagingDataSource
import com.woojoo.allsearching.domain.ResponseResult
import com.woojoo.allsearching.domain.entites.Documents
import com.woojoo.allsearching.domain.entites.ResError
import com.woojoo.allsearching.domain.repository.SearchResultRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchResultRepositoryImpl @Inject constructor(
    private val searchingDataSource: SearchingDataSource,
    private val networkAPI: NetworkAPI
) : SearchResultRepository {

    override suspend fun getTotalList(query: String): Flow<PagingData<Documents>> {
        return Pager(
                config = PagingConfig(pageSize = REQUEST_PARAM_SIZE, enablePlaceholders = false),
                pagingSourceFactory = { SearchingPagingDataSource(query, networkAPI) }
            ).flow
        }
    override suspend fun getTotalListLiveData(query: String): PagingData<Documents> {
         val value = Pager(
            config = PagingConfig(pageSize = REQUEST_PARAM_SIZE, enablePlaceholders = false),
            pagingSourceFactory = { SearchingPagingDataSource(query, networkAPI) }
        ).liveData.value
        //추후 null 처리 작업하기
        return value!!
    }

    companion object {
        private const val REQUEST_PARAM_SIZE = 30
    }
}

/*
* PagingData를 구성하기 위해서는 이를 다른 앱 레이어에 전달하기 위한 API를 결정해야 한다.
* 여기서는 Flow를 사용해보겠다. 물론 RxJava 로도 가능하다.
* */