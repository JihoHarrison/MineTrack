package com.example.minetrack.data.entity

import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Entity
import kotlinx.parcelize.Parcelize

@Parcelize
// API 응답 형식에는 없지만 조회한 송장 번호의 저장의 편의성을 위해 작성
// 다른 회사인데 같은 송장 번호를 가지고 있을 경우의 예외를 고려하여 invoice와 code 두 개의 Field로 주 키를 구성함
@Entity(primaryKeys = ["invoice", "code"])
data class TrackingItem(
    val invoice: String,
    // @Embedded -> ShippingCompany의 Field들을 하나의 테이블로 저장하기 위해 사용
    @Embedded val company: ShippingCompany
) : Parcelable