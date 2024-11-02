package com.artemqq5.workoutapp.domain.model

import android.os.Parcel
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Exercise(
    val img: Int,
    val name: String,
    val time: Int
) : Parcelable