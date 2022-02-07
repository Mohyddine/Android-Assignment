package com.mehyo.androidassignment.ui.pages.main

import android.icu.util.Calendar
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mehyo.androidassignment.model.LaunchItem
import com.mehyo.androidassignment.repository.LaunchesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@RequiresApi(Build.VERSION_CODES.O)
class MainPageViewModel(private val launchesRepo: LaunchesRepository): ViewModel() {

    init {
        getLaunches()
    }
    private var launchesMutableLiveData= MutableLiveData<List<LaunchItem>>()
    val launchesLiveData: LiveData<List<LaunchItem>> get() =launchesMutableLiveData

    //getting the successful Launches from the repository and posting the response into the MutableLiveData
    private fun getLaunches() = viewModelScope.launch(Dispatchers.IO) {
        val list= arrayListOf<LaunchItem>()
        launchesRepo.getLaunches().body()?.forEach { launchItem ->
            when (launchItem.success) {
                null -> {
                    list.add(launchItem)
                }
                true -> {
                    val currentYear = Calendar.getInstance().get(Calendar.YEAR)
                    when (launchItem.staticFireDateUtc.substring(0..3).toInt()) {
                        currentYear -> {
                            list.add(launchItem)
                        }
                        currentYear-1 -> {
                            list.add(launchItem)
                        }
                        currentYear-2 -> {
                            list.add(launchItem)
                        }
                        else -> {}
                    }
                }
                else -> {}
            }
        }
        launchesMutableLiveData.postValue(list)
    }
}