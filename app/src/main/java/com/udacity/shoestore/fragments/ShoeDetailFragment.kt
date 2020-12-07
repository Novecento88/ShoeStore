package com.udacity.shoestore.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeDeteailBinding
import com.udacity.shoestore.viewModels.ShoesListViewModel

class ShoeDetailFragment : Fragment() {

    private lateinit var binding: FragmentShoeDeteailBinding

    private val viewModel: ShoesListViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_shoe_deteail, container, false)

        //viewModel = ViewModelProvider(this).get(ShoesListViewModel::class.java)

        binding.shoeViewModel = viewModel
        binding.lifecycleOwner = this

        binding.saveButton.setOnClickListener {
            binding.apply {
                if(allFieldsAreNotBlank()){
                    viewModel.addNewPairOfShoes(
                        shoeNameEditText.text.toString(),
                        shoeSizeEditText.text.toString(),
                        companyNameEditText.text.toString(),
                        shoeDescriptionEditText.text.toString())
                    goToShoesList()
                } else {
                    Toast.makeText(context, R.string.empty_fields_toast, Toast.LENGTH_LONG).show()
                }
            }
        }

        binding.cancelButton.setOnClickListener {
            goToShoesList()
        }

        return binding.root
    }

    private fun goToShoesList(){
        findNavController().navigate(ShoeDetailFragmentDirections.actionShoeDetailFragmentToShoesListFragment())
    }

    private fun allFieldsAreNotBlank() : Boolean {
        return binding.shoeNameEditText.text.toString().isNotBlank() &&
                binding.companyNameEditText.text.toString().isNotBlank() &&
                binding.shoeSizeEditText.text.toString().isNotBlank() &&
                binding.shoeDescriptionEditText.text.toString().isNotBlank()
    }

}