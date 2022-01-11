package com.filomenadeveloper.laboro_new_version.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.filomenadeveloper.laboro_new_version.MainActivity
import com.filomenadeveloper.laboro_new_version.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

/**
 * A placeholder fragment containing a simple view.
 */
class PlaceholderFragment : Fragment() {

    private lateinit var pageViewModel: PageViewModel
    private lateinit var root: View


    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
         root = inflater.inflate(R.layout.fragment_main, container, false)

        val fab: FloatingActionButton = root.findViewById(R.id.fab)

        fab.setOnClickListener { view ->
            val intent = Intent(context, MainActivity::class.java)
            startActivity(intent)
        }
        return root
    }

}