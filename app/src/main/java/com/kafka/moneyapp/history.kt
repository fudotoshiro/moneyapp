package com.kafka.moneyapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.kafka.moneyapp.databinding.ActivityMainBinding
import com.kafka.moneyapp.databinding.FragmentHistoryBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class history : Fragment() {
    private lateinit var binding: FragmentHistoryBinding
    private lateinit var history : List<historyModel>
    private lateinit var historyAdapter: historyAdapter
    private lateinit var linearlayoutManager: LinearLayoutManager
    lateinit var db: AppDatabase

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View {
        // Inflate the layout for this fragment
        binding = FragmentHistoryBinding.inflate(layoutInflater, container, false)
        super.onCreate(savedInstanceState)
        history = arrayListOf(
            //historyModel(id = 1, label = "Кафе", amount = -10000.00, date = "20.02.2004", category = "")
        )

        historyAdapter = historyAdapter(history)
        linearlayoutManager = LinearLayoutManager(context)



        binding.recyclerview.apply {
            adapter = historyAdapter
            layoutManager = linearlayoutManager
        }
        //return inflater.inflate(R.layout.fragment_history, container, false)
        //fetchAll()
        return binding.root

    }

    private fun fetchAll(){
        GlobalScope.launch {
            db.transactionsDao().insertAll(historyModel(0, "Icecream", 320.00, "20.02.2004", "groceries"))
            history = db.transactionsDao().getAll()
            activity?.runOnUiThread(){
                updateDashboard()
                historyAdapter.setData(history)
            }
        }
    }

    fun updateDashboard(){
        val totalAmount: Double = history.sumOf { it.amount }
        val budgetAmount: Double = history.filter { it.amount > 0 }.sumOf { it.amount }
        val expenseAmount: Double = totalAmount - budgetAmount
    }
}