package com.example.machinetestdemo

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.machinetestdemo.chatadapter.ItemAdapter
import com.example.machinetestdemo.databinding.ActivityMainBinding
import com.example.machinetestdemo.vm.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val viewModel: MainViewModel by viewModels()

    lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
      }

    fun init(){
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = ItemAdapter(listOf())
        binding.recyclerView.adapter = adapter

        lifecycleScope.launch {
            viewModel.items.collect { items ->
                adapter.updateItem(items)
            }
        }

    }
}