package com.filomenadeveloper.laboro_new_version.ui.drawer.drawer_produts.product

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.filomenadeveloper.laboro_new_version.AppLaboro
import com.filomenadeveloper.laboro_new_version.R
import com.filomenadeveloper.laboro_new_version.database.DatabaseKeys
import com.filomenadeveloper.laboro_new_version.database.HawkStorage
import com.filomenadeveloper.laboro_new_version.database.LaboroViewModelFactory
import com.filomenadeveloper.laboro_new_version.database.LaboroViewModels
import com.filomenadeveloper.laboro_new_version.databinding.FragmentProductBinding
import com.filomenadeveloper.laboro_new_version.ui.drawer.drawer_produts.ProductFragmentNavigationDirections
import com.filomenadeveloper.laboro_new_version.ui.drawer.drawer_produts.product.addProducts.ActivityAddProduts
import com.filomenadeveloper.laboro_new_version.ui.drawer.drawer_produts.product.addProducts.ItemListLinearAdapter

class FragmentProdut :Fragment() {

    private val viewModel: LaboroViewModels by activityViewModels {
        LaboroViewModelFactory((activity?.application as AppLaboro).database.productsDao())
    }

    private var _binding: FragmentProductBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProductBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = ItemListLinearAdapter{
            val  action = ProductFragmentNavigationDirections.actionAddItemFragmentToItemListFragment()
            this.findNavController().navigate(action)
        }


        binding.addProductImageview.setOnClickListener {
            startActivity(Intent(requireContext(), NavigationManegerProducts::class.java))
        }

        binding.cardAddProdut.setOnClickListener {
            startActivity(Intent(requireContext(), NavigationManegerProducts::class.java))
        }

        binding.recycleProduct.layoutManager = LinearLayoutManager(this.requireContext())
        binding.recycleProduct.adapter = adapter
        viewModel.allItems.observe(this.viewLifecycleOwner){ items ->
            items.let { adapter.submitList(it) }

        }
    }

}