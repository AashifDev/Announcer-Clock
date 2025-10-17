package com.dzo.announcerclock.presentation.fragments.repeat_option.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dzo.announcerclock.data.local_source.AppPreferences
import com.dzo.announcerclock.domain.repeat_option_usecase.GetRepeatOptionUseCase
import com.dzo.announcerclock.presentation.fragments.repeat_option.model.RepeatOption
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RepeatOptionViewModel @Inject constructor(
    private val getRepeatOptionUseCase: GetRepeatOptionUseCase
) : ViewModel() {
    private val _repeatOptions = MutableStateFlow<List<RepeatOption>>(emptyList())
    val repeatOptions = _repeatOptions.asStateFlow()

    init {
        loadRepeatOptions()
    }

    private fun loadRepeatOptions() {
        val list = getRepeatOptionUseCase()
        _repeatOptions.value = list
    }

    fun selectCustomOption(selectedItem: RepeatOption, customPair: Pair<Long, Long>? = null){
        viewModelScope.launch {

            val updatedList = _repeatOptions.value.map {
                if (it.id == selectedItem.id) {
                    it.copy(
                        isSelected = true,
                        custom = customPair ?: selectedItem.custom // save custom values if provided
                    )
                } else {
                    it.copy(isSelected = false)
                }
            }
            _repeatOptions.value = updatedList
            // Save selected option in preference
            val selected = updatedList.find { it.isSelected }
            selected?.let {
                AppPreferences.saveRepeatOption(it)
            }
        }
    }

    fun selectOption(selectedItem: RepeatOption) {
        viewModelScope.launch {
            val updatedList = _repeatOptions.value.map {
                if (it.id == selectedItem.id) {
                    it.copy(
                        isSelected = true,
                    )
                } else {
                    it.copy(isSelected = false)
                }
            }
            _repeatOptions.value = updatedList
            // Save selected option in preference
            val selected = updatedList.find { it.isSelected }
            selected?.let {
                //val itemToSave = selectedItem.copy(custom = customPair ?: selectedItem.custom, isSelected = true)
                AppPreferences.saveRepeatOption(it)
            }
        }
    }




}