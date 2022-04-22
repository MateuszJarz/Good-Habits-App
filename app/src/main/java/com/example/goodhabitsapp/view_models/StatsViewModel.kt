package com.example.goodhabitsapp.view_models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.goodhabitsapp.data.repository.StatRepository
import com.example.goodhabitsapp.domain.model.Statistics
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StatsViewModel @Inject constructor(
    private val statRepository: StatRepository,
) : ViewModel() {

    private var _allStats = MutableStateFlow(Statistics())
    val allStats: StateFlow<Statistics> = _allStats

    fun getStats() {

        viewModelScope.launch {
            _allStats.value = statRepository.getStats()
        }

    }
}