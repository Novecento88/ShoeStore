package com.udacity.shoestore.fragments

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoesListBinding
import com.udacity.shoestore.databinding.ShoeItemLayoutBinding
import com.udacity.shoestore.models.Shoe
import com.udacity.shoestore.viewModels.ShoesListViewModel

class ShoesListFragment : Fragment() {

    private lateinit var viewModel: ShoesListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentShoesListBinding>(inflater, R.layout.fragment_shoes_list, container, false)

        viewModel = ViewModelProvider(this).get(ShoesListViewModel::class.java)

        viewModel.shoesList.observe(viewLifecycleOwner, Observer { shoesList ->
           for(shoe in shoesList){
               val shoeBinding = ShoeItemLayoutBinding.inflate(inflater)
               shoeBinding.shoe = shoe
               binding.shoesListLinearLayout.addView(shoeBinding.root)
           }
        })

        setHasOptionsMenu(true)

        binding.floatingActionButton.setOnClickListener{
            findNavController().navigate(ShoesListFragmentDirections.actionShoesListFragmentToShoeDetailFragment())
        }

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.actionLogout -> {
                findNavController().navigate(ShoesListFragmentDirections.actionShoesListFragmentToLoginFragment2())
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

}