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
import com.google.android.material.floatingactionbutton.FloatingActionButton

class AlpinStockFragment : Fragment() {

    private lateinit var alpinViewModel: AlpinViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_alpin_master, container, false)

        val adapter = ListAdapter()
        val recyclerView:RecyclerView = view.findViewById(R.id.alpinRecyclerView)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        alpinViewModel = ViewModelProvider(this)[AlpinViewModel::class.java]
        alpinViewModel.readAllAlpins.observe(viewLifecycleOwner, Observer { alpin ->
            adapter.setData(alpin)
        })

        val floatingActionButton = view.findViewById<FloatingActionButton>(R.id.alpinAddButton)
        floatingActionButton.setOnClickListener {
            val bundle = Bundle()
            bundle.putSerializable("ALPIN_VIEW_MODEL", AlpinViewModel::class.java)
            findNavController().navigate(R.id.action_alpinStockFragment_to_addFragment, bundle)
        }

        return view
    }
}