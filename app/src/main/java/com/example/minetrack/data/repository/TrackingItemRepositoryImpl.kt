package com.example.minetrack.data.repository

import com.example.minetrack.data.api.SweetTrackerApi
import com.example.minetrack.data.db.TrackingItemDao
import com.example.minetrack.data.entity.TrackingInformation
import com.example.minetrack.data.entity.TrackingItem
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class TrackingItemRepositoryImpl(
    private val sweetTrackerApi: SweetTrackerApi,
    private val trackingItemDao: TrackingItemDao,
    private val dispatcher: CoroutineDispatcher
) : TrackingItemRepository {
    override suspend fun getTrackingItemInformation(): List<Pair<TrackingItem, TrackingInformation>> =
        withContext(dispatcher) {
            trackingItemDao.getAll()
                .mapNotNull { trackingItem ->
                    val relatedTrackingInfo = sweetTrackerApi.getTrackingInformation(
                        trackingItem.company.code,
                        trackingItem.invoice
                    ).body()

                    if (!relatedTrackingInfo!!.errorMessage.isNullOrBlank()) {
                        null
                    } else {
                        trackingItem to relatedTrackingInfo
                    }
                }
                .sortedWith(
                    compareBy(
                        { it.second.level },
                        { -(it.second.lastDetail?.time ?: Long.MAX_VALUE) }
                    )
                )
        }
}