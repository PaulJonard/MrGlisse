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
import com.example.mrglisse.databinding.FragmentAddBinding
import com.example.mrglisse.databinding.FragmentAlpinMasterBinding
import com.example.mrglisse.viewmodel.AlpinViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class AlpinMasterFragment : Fragment() {
    private lateinit var binding: FragmentAlpinMasterBinding
    private lateinit var bottomNavigationView: BottomNavigationView

    private lateinit var alpinViewModel: AlpinViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAlpinMasterBinding.inflate(layoutInflater, container, false)

        val adapter = ListAdapter()
        binding.alpinRecyclerView.adapter = adapter
        binding.alpinRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        alpinViewModel = ViewModelProvider(this)[AlpinViewModel::class.java]
        alpinViewModel.readAllAlpins.observe(viewLifecycleOwner, Observer { alpin ->
            adapter.setData(alpin)
        })

        binding.alpinAddButton.setOnClickListener {
            val bundle = Bundle()
            bundle.putSerializable("ALPIN_VIEW_MODEL", AlpinViewModel::class.java)
            findNavController().navigate(R.id.action_alpinStockFragment_to_addFragment, bundle)
        }

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        bottomNavigationView = requireActivity().findViewById(R.id.bottomNavigationView)
        bottomNavigationView.visibility = View.VISIBLE
    }
}