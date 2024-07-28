package com.example.machinetestdemo.chatadapter
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.machinetestdemo.DetailActivity
import com.example.machinetestdemo.databinding.ItemLayoutBinding
import com.example.machinetestdemo.db.ItemEntity
import com.google.gson.Gson

class ItemAdapter(var items: List<ItemEntity>) :
    RecyclerView.Adapter<ItemAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemLayoutBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.binding.entityData = item
        Glide.with(holder.binding.root.context).load(item.image).into(holder.binding.songImage)
        holder.binding.root.setOnClickListener(){
            sendToDetailScreen(item,holder.binding.root.context)
        }
     }

    private fun sendToDetailScreen(item: ItemEntity, context: Context) {
        var gson = Gson()
        var intent = Intent(context, DetailActivity::class.java).apply {
            putExtra("detailData", gson.toJson(item) )
        }
        context.startActivity(intent)
    }


    fun updateItem( item :List<ItemEntity>){
        items = item
        notifyDataSetChanged()
        Log.e("TAG", "updateItem: $items", )
    }

    override fun getItemCount() = items.size
}
