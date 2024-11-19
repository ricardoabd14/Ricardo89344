
package com.avengers.energiaverde

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

data class Tip(val title: String, val description: String)

class TipAdapter(
    private var tips: List<Tip>,
    private val onClick: (Tip) -> Unit
) : RecyclerView.Adapter<TipAdapter.TipViewHolder>() {

    inner class TipViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTextView: TextView = itemView.findViewById(R.id.titleTextView)
        val descriptionTextView: TextView = itemView.findViewById(R.id.descriptionTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TipViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_tip, parent, false)
        return TipViewHolder(view)
    }

    override fun onBindViewHolder(holder: TipViewHolder, position: Int) {
        val tip = tips[position]
        holder.titleTextView.text = tip.title
        holder.descriptionTextView.text = tip.description
        holder.itemView.setOnClickListener { onClick(tip) }
    }

    override fun getItemCount(): Int = tips.size

    fun updateList(newTips: List<Tip>) {
        tips = newTips
        notifyDataSetChanged()
    }
}
