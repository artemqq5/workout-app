package com.artemqq5.workoutapp.presentation.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.artemqq5.workoutapp.domain.model.Muscles
import com.artemqq5.workoutapp.domain.usecases.GetMuscleGroupsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MenuViewModel @Inject constructor(
    private val getMuscleGroupsUseCase: GetMuscleGroupsUseCase
) : ViewModel() {

    private val _muscles = MutableStateFlow<Result<List<Muscles>?>>(Result.success(null))
    val muscles: StateFlow<Result<List<Muscles>?>> = _muscles

    init {
        fetchMuscles()
    }

    private fun fetchMuscles() {
        viewModelScope.launch(Dispatchers.IO) {
            getMuscleGroupsUseCase.execute().collect { result ->
                _muscles.value = result
            }
        }
    }
}