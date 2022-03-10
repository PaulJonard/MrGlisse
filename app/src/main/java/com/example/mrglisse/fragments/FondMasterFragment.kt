package com.example.mrglisse.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mrglisse.R
import com.example.mrglisse.viewmodel.AlpinViewModel
import com.example.mrglisse.viewmodel.FondViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

class FondStockFragment : Fragment() {

    private lateinit var fondViewModel: FondViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_fond_master, container, false)

        val adapter = ListAdapter()
        val recyclerView: RecyclerView = view.findViewById(R.id.fondRecyclerView)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        fondViewModel = ViewModelProvider(this)[FondViewModel::class.java]
        fondViewModel.readAllFonds.observe(viewLifecycleOwner, Observer { fond ->
            adapter.setData(fond)
        })

        val floatingActionButton = view.findViewById<FloatingActionButton>(R.id.fondAddButton)
        floatingActionButton.setOnClickListener {
            val bundle = Bundle()
            bundle.putSerializable("FOND_VIEW_MODEL", FondViewModel::class.java)
            findNavController().navigate(R.id.action_fondStockFragment_to_addFragment, bundle)
        }

        return view
    }
}