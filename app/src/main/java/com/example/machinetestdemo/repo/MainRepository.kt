package com.example.machinetestdemo.repo

import android.content.Context
import android.os.Looper
import android.util.Log
import android.widget.Toast
import com.example.machinetestdemo.api.ApiService
import com.example.machinetestdemo.db.ItemDao
import com.example.machinetestdemo.db.ItemEntity
import com.example.machinetestdemo.other.NetworkConnectivity
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import java.util.logging.Handler
import javax.inject.Inject

class MainRepository  @Inject constructor(
    private val apiService: ApiService,
    private val itemDao: ItemDao,

){
    val items: Flow<List<ItemEntity>> = itemDao.getAllItems()

   suspend fun saveItem(){
           var response= apiService.getXmlData()
           if (response.isSuccessful) {
               response.body()?.entries?.subList(0,20)?.let { items ->
                   val itemEntities = items.map {
                       ItemEntity(it.title ?:"", it.images.get(2).url.toString() ?: it.images.get(0).url.toString(),
                           it.artist?.artist?:"",it.price?.price ?:"" , it.content?.content?:"",
                           it.link?.get(1)?.href ?: "") }.toMutableList()
                   itemDao.insertAll(itemEntities)
               }
           }else{
               Log.e("TAG", "getItem: error occur", )
           }
       }

   }


