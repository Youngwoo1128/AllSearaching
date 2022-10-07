package com.woojoo.allsearching.data.repository

import com.woojoo.allsearching.data.local.ResearchingDao
import com.woojoo.allsearching.data.mapping.toData
import com.woojoo.allsearching.data.mapping.toDomain
import com.woojoo.allsearching.domain.entites.Researching
import com.woojoo.allsearching.domain.repository.ResearchingRepository
import javax.inject.Inject

class ResearchingRepositoryImpl @Inject constructor(
    private val researchingDao: ResearchingDao
) : ResearchingRepository {

    override suspend fun getResearchingItem(): List<Researching> {
        return researchingDao.getAll().map {
            it.toDomain()
        }
    }

    override suspend fun insertResearching(item: Researching) {
        researchingDao.insertResearching(item.toData())
    }

    override suspend fun deleteResearching(item: Researching) {
        return researchingDao.delete(item.toData())
    }
}