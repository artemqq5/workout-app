package com.artemqq5.workoutapp.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Muscles(
    val icon: Int,
    val nameMusclesGroup: String,
    val exercises: ArrayList<Exercise>,
    val kcal: Int
) : Parcelable