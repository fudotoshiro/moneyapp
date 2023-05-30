package com.kafka.moneyapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

class historyAdapter(private var history: List<historyModel>):
    RecyclerView.Adapter<historyAdapter.historyHolder>(){
    class historyHolder(view: View): RecyclerView.ViewHolder(view){
        val label: TextView = view.findViewById(R.id.history_label)
        val amount: TextView = view.findViewById(R.id.history_amount)
        val date: TextView = view.findViewById(R.id.history_date)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): historyHolder {
        val view:View = LayoutInflater.from(parent.context).inflate(R.layout.history_layout, parent, false)
        return historyHolder(view)
    }

    override fun onBindViewHolder(holder: historyHolder, position: Int) {
        val history:historyModel = history[position]
        val context:Context = holder.amount.context

        if(history.amount >= 0){
            holder.amount.text = "+ %.2f".format(history.amount)
            holder.amount.setTextColor(ContextCompat.getColor(context, R.color.green))
        }else{
            holder.amount.text = "- %.2f".format(Math.abs(history.amount))
            holder.amount.setTextColor(ContextCompat.getColor(context, R.color.red))
        }
        holder.label.text = history.label
        holder.date.text = history.date
    }

    override fun getItemCount(): Int {
        return history.size
    }
    fun setData(history: List<historyModel>){
        this.history = history
        notifyDataSetChanged()
    }
}