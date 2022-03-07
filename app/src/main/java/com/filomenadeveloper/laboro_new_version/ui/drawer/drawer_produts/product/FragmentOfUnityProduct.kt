package com.filomenadeveloper.laboro_new_version.ui.drawer.drawer_produts.product

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.navigation.fragment.findNavController
import androidx.viewpager.widget.ViewPager
import com.filomenadeveloper.laboro_new_version.databinding.ActivityNavigationManegerProductsBinding
import com.filomenadeveloper.laboro_new_version.ui.drawer.drawer_produts.SectionsPagerAdapter
import com.filomenadeveloper.laboro_new_version.ui.drawer.drawer_produts.product.addProducts.ActivityAddProduts
import com.filomenadeveloper.laboro_new_version.ui.drawer.drawer_produts.product.stockofproduts.StockProdutFragment
import androidx.appcompat.app.AppCompatActivity





class FragmentOfUnityProduct:Fragment() {

    private var _binding: ActivityNavigationManegerProductsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = ActivityNavigationManegerProductsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity?)!!.supportActionBar!!.title = "Adicionar Produtos"


        val sectionsPagerAdapter = SectionsPagerAdapter(childFragmentManager,
            FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT)
        val viewPager: ViewPager = binding.viewPagerManegeProduct

        sectionsPagerAdapter.AddFragment(ActivityAddProduts(),"Produto")
        sectionsPagerAdapter.AddFragment(StockProdutFragment(),"Stocks")
        viewPager.adapter = sectionsPagerAdapter

        binding.tabsManageProduct.setupWithViewPager(viewPager)

    }
}