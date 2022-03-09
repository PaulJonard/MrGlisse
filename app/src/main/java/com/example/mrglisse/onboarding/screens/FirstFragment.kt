package com.example.mrglisse.onboarding.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.viewpager2.widget.ViewPager2
import com.example.mrglisse.R

class FirstFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_first, container, false)

        val viewPager = activity?.findViewById<ViewPager2>(R.id.viewPager)

        val next = view.findViewById<TextView>(R.id.next1)
        next.setOnClickListener{
            viewPager?.currentItem = 1
        }

        return view
    }
}