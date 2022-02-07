package com.mehyo.androidassignment.ui.pages.inner

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mehyo.androidassignment.model.RocketItem
import com.mehyo.androidassignment.repository.RocketRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class InnerPageViewModel(private val rocketRepo: RocketRepository): ViewModel() {

    private var rocketItemMutableLiveData= MutableLiveData<Response<RocketItem>>()
    val rocketItemLiveData: LiveData<Response<RocketItem>> get() =rocketItemMutableLiveData

    //getting Rocket details by ID from the repository and posting the response into the MutableLiveData
    fun getRocketByID(id:String) = viewModelScope.launch(Dispatchers.IO) {
        rocketItemMutableLiveData.postValue(rocketRepo.getRocketByID(id))
    }
}