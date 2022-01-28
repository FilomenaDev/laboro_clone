package com.filomenadeveloper.laboro_new_version.ui.drawer.drawer_produts.product

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import com.filomenadeveloper.laboro_new_version.R
import com.filomenadeveloper.laboro_new_version.ui.drawer.drawer_produts.product.addProducts.ActivityAddProduts

class FragmentProdut :Fragment() {

    private lateinit var root: View
    private lateinit var cardAddProduct : CardView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        root = inflater.inflate(R.layout.fragment_product, container, false)
        cardAddProduct = root.findViewById(R.id.card_add_produt)

        val fragmentManager = childFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        cardAddProduct.setOnClickListener {
           startActivity(Intent(requireContext(), NavigationManegerProducts::class.java))
        }

        return root
    }

}