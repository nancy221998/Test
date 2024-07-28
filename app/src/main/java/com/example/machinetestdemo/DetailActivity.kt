package com.example.machinetestdemo

import android.media.MediaPlayer
import android.os.Bundle
import android.text.Html
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.machinetestdemo.databinding.ActivityDetailBinding
import com.example.machinetestdemo.db.ItemEntity
import com.example.machinetestdemo.other.NetworkConnectivity
import com.google.gson.Gson

class DetailActivity : AppCompatActivity() {

    private var mediaPlayer: MediaPlayer? = null
    lateinit var binding : ActivityDetailBinding
    lateinit var itemData : ItemEntity
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
          var gson = Gson()
        itemData = gson.fromJson(intent.getStringExtra("detailData").toString(), ItemEntity::class.java)

        Log.e("TAG", "onCreate: $itemData", )
        binding.entityData = itemData
        showDetail(itemData)
       initListner()
    }

    private fun initListner() {
        if(NetworkConnectivity.isInternetAvailable(this)){
            mediaPlayer = MediaPlayer().apply {
                setDataSource(itemData.url)
                prepare()
            }
        }else{
            Toast.makeText(this,"Can't play video, Internet issue",Toast.LENGTH_SHORT).show()
        }

        binding.playButton.setOnClickListener {
            mediaPlayer?.start()
        }

        binding.pauseButton.setOnClickListener {
            mediaPlayer?.pause()
        }
        binding.appBar.btnBack.setOnClickListener(){onBackPressed()}
    }

    private fun showDetail(itemData: ItemEntity?) {
        binding.appBar.btnBack.visibility= View.VISIBLE
        binding.appBar.textContactName.text="Detail"
        Glide.with(this).load(itemData?.image).into(binding.detailImage)
        val htmlDescription = itemData?.content?.let { Html.fromHtml(it, Html.FROM_HTML_MODE_COMPACT) }
        binding.detailDescription.text = htmlDescription
     }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer?.release()
        mediaPlayer = null
    }
}
