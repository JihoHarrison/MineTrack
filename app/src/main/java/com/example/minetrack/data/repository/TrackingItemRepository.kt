package com.example.minetrack.data.repository

import com.example.minetrack.data.entity.TrackingInformation
import com.example.minetrack.data.entity.TrackingItem

interface TrackingItemRepository {

    suspend fun getTrackingItemInformation(): List<Pair<TrackingItem, TrackingInformation>>
}