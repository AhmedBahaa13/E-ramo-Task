package com.bahaa.task.presentation.mainFragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bahaa.task.domain.MainRepository
import com.bahaa.task.domain.models.remoteModels.TopStore
import com.bahaa.task.utils.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val mainRepository: MainRepository): ViewModel() {

   private val _topStores :MutableStateFlow<Response<List<TopStore>>> = MutableStateFlow(Response.Loading(false))
    val topStores:StateFlow<Response<List<TopStore>>> get() = _topStores
    fun getTopStores(){
        viewModelScope.launch(Dispatchers.IO){
            mainRepository.getTopStores().collect{
                _topStores.value = it
            }
        }
    }


}
