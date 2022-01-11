package com.filomenadeveloper.laboro_new_version.ui.product

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager.widget.ViewPager
import com.filomenadeveloper.laboro_new_version.MainActivity
import com.filomenadeveloper.laboro_new_version.R
import com.filomenadeveloper.laboro_new_version.ui.catalogoOnlain.CatalogoOnlainFragment
import com.filomenadeveloper.laboro_new_version.ui.main.PlaceholderFragment
import com.filomenadeveloper.laboro_new_version.ui.main.SectionsPagerAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout

class ProductFragment : Fragment() {

    private lateinit var productViewModel: ProductViewModel

    @SuppressLint("UseRequireInsteadOfGet", "ResourceAsColor")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        productViewModel = ViewModelProvider(this).get(ProductViewModel::class.java)
        val root = inflater.inflate(R.layout.activity_main, container, false)
        val sectionsPagerAdapter = SectionsPagerAdapter(fragmentManager!!,FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT)
        val viewPager: ViewPager = root.findViewById(R.id.view_pager)
        sectionsPagerAdapter.AddFragment(PlaceholderFragment(),"Itens")
        sectionsPagerAdapter.AddFragment(CatalogoOnlainFragment(),"Estoque")
        sectionsPagerAdapter.AddFragment(CatalogoOnlainFragment(),"Categoria")
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = root.findViewById(R.id.tabs)
        tabs.setupWithViewPager(viewPager)
        val tab =tabs.getTabAt(0)
        val badge = tab?.orCreateBadge
        badge!!.number =122


        return root
    }
}