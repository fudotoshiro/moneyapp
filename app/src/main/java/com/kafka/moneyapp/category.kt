package com.kafka.moneyapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.kafka.moneyapp.databinding.FragmentCategoryBinding

class category : Fragment() {

    private lateinit var binding: FragmentCategoryBinding
    private lateinit var view: CardView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCategoryBinding.inflate(layoutInflater, container, false)
        super.onCreate(savedInstanceState)
        binding.createCategoryBtn.setOnClickListener(){
            val showDialog = CreateCategory()

            showDialog.show((activity as AppCompatActivity).supportFragmentManager, "showDialog")

        }



        return binding.root

    }
}