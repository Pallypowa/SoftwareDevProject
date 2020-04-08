package com.example.studentcalendar.Adapters


import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.studentcalendar.ItemClass
import com.example.studentcalendar.R
import kotlinx.android.synthetic.main.view_item.view.*

class RecyclerAdapter(private val itemList: List<ItemClass>, private val context: Context?) :
    RecyclerView.Adapter<RecyclerAdapter.ItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup , viewType: Int): ItemViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.view_item ,
            parent , false
        )

        return ItemViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ItemViewHolder , position: Int) {
        val currentItem = itemList[position]
        //val absentNum = currentItem.absents

        holder.imageView.setImageResource(currentItem.imageResource)
        holder.className.text = currentItem.text1
        holder.classRoom.text = currentItem.text2
        holder.absents.text = currentItem.absents.toString()
        animate(holder)
    }

    override fun getItemCount(): Int = itemList.size

    //One row of our RecyclerView
    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.image_view
        val className: TextView = itemView.class_name
        val classRoom: TextView = itemView.class_room
        val absents: TextView = itemView.absents
        //val cardView: CardView = itemView.card_view


    }

     private fun animate(viewHolder: RecyclerView.ViewHolder){
        val animAnticipateOvershoot: Animation = AnimationUtils.loadAnimation(context, R.anim.bounce_interpolator)
        viewHolder.itemView.setAnimation(animAnticipateOvershoot)
    }
}