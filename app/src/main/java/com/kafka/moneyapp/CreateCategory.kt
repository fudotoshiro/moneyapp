package com.kafka.moneyapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.DialogFragment
import com.kafka.moneyapp.databinding.FragmentCreateCategoryBinding

class CreateCategory : DialogFragment() {
    private lateinit var binding: FragmentCreateCategoryBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCreateCategoryBinding.inflate(layoutInflater, container, false)
        super.onCreate(savedInstanceState)
        binding.categoryText.addTextChangedListener {
            if (binding.categoryText.text.toString().isNotEmpty())
                binding.categoryInput.error = null
        }

        binding.categoryAdd.setOnClickListener(){
            val categoryName: String = binding.categoryText.text.toString()

            if(categoryName.isEmpty())
                binding.categoryInput.error = "Please add a valid category name"


        }
        return binding.root
    }


}