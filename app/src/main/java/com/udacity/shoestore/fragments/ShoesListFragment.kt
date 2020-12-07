package com.udacity.shoestore.fragments

import android.os.Bundle
import android.view.*
import android.widget.LinearLayout
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoesListBinding
import com.udacity.shoestore.databinding.ShoeItemLayoutBinding
import com.udacity.shoestore.viewModels.ShoesListViewModel

class ShoesListFragment : Fragment() {

    private val viewModel: ShoesListViewModel by activityViewModels()

    private lateinit var binding: FragmentShoesListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate<FragmentShoesListBinding>(inflater, R.layout.fragment_shoes_list, container, false)

        viewModel.shoesList.observe(viewLifecycleOwner, Observer { shoesList ->
           for(shoe in shoesList){
               val shoeBinding = ShoeItemLayoutBinding.inflate(inflater)
               shoeBinding.shoe = shoe
               addItem(shoeBinding.root)
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

    private fun addItem(view: View){
        val layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
        layoutParams.setMargins(16, 8, 16, 8)
        binding.shoesListLinearLayout.addView(view, layoutParams)
    }

}