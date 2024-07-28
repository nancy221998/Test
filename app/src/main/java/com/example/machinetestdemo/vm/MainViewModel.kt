package com.example.machinetestdemo.vm

import android.content.Context
import android.util.Log
import com.example.machinetestdemo.api.ApiService
import com.example.machinetestdemo.db.ItemDao
import com.example.machinetestdemo.db.ItemEntity

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.machinetestdemo.other.NetworkConnectivity
import com.example.machinetestdemo.repo.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: MainRepository,
    @ApplicationContext private val context: Context
    ) : ViewModel() {

    val items: Flow<List<ItemEntity>> = repository.items

    init {
        viewModelScope.launch { withContext(Dispatchers.IO) {
            if(NetworkConnectivity.isInternetAvailable(context)){
                repository.saveItem()
               }else{
                Log.e("TAG", "error: ", )
                }
         }
        }
    }

}
