package com.filomenadeveloper.laboro_new_version.ui.drawer.drawer_produts.product

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.filomenadeveloper.laboro_new_version.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

class FragmentProdut :Fragment() {

    private lateinit var root: View


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        root = inflater.inflate(R.layout.fragment_main, container, false)

        val fab: FloatingActionButton = root.findViewById(R.id.fab)

        fab.setOnClickListener { view ->
            val intent = Intent(context, ActivityAddProduts::class.java)
            startActivity(intent)
        }
        return root
    }

}