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
import com.example.mrglisse.databinding.FragmentFondMasterBinding
import com.example.mrglisse.viewmodel.AlpinViewModel
import com.example.mrglisse.viewmodel.FondViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class FondMasterFragment : Fragment() {
    private lateinit var binding: FragmentFondMasterBinding
    private lateinit var bottomNavigationView: BottomNavigationView

    private lateinit var fondViewModel: FondViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFondMasterBinding.inflate(layoutInflater, container, false)

        val adapter = ListAdapter()

        binding.fondRecyclerView.adapter = adapter
        binding.fondRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        fondViewModel = ViewModelProvider(this)[FondViewModel::class.java]
        fondViewModel.readAllFonds.observe(viewLifecycleOwner, Observer { fond ->
            adapter.setData(fond)
        })

        binding.fondAddButton.setOnClickListener {
            val bundle = Bundle()
            bundle.putSerializable("FOND_VIEW_MODEL", FondViewModel::class.java)
            findNavController().navigate(R.id.action_fondStockFragment_to_addFragment, bundle)
        }

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        bottomNavigationView = requireActivity().findViewById(R.id.bottomNavigationView)
        bottomNavigationView.visibility = View.VISIBLE
    }
}