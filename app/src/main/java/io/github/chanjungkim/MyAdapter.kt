package io.github.chanjungkim

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView

class MyAdapter(colors: List<String>): RecyclerView.Adapter<MyAdapter.MyViewHolder>() {
    var colors = colors
    var focusIdx = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_vp, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = colors[position]
        val context = holder.itemView.context
        if(focusIdx == position){
            holder.imageView.animate()
                .setDuration(500)
                .alpha(1f)
                .withEndAction {
                    notifyItemChanged(position)
                }
                .start()
        }else{
            holder.imageView.animate()
                .setDuration(1000)
                .alpha(0.1f)
                .withEndAction {
                    notifyItemChanged(position)
                }
                .start()
        }
        holder.imageView.setBackgroundColor(Color.parseColor(item))
    }

    override fun getItemCount(): Int {
        return colors.size
    }

    fun focus(position: Int){
        focusIdx = position
    }

    fun addMore(items: List<String>){
        colors = colors.toMutableList().apply {
            addAll(items)
        }.toList()
    }

    inner class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var imageView: ImageView = itemView.findViewById(R.id.image_view)
    }
}