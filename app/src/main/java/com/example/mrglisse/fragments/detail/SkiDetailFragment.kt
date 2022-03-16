package com.example.mrglisse.fragments.detail

import android.app.AlertDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.mrglisse.R
import com.example.mrglisse.databinding.FragmentSkiDetailBinding
import com.example.mrglisse.model.Alpin
import com.example.mrglisse.model.Fond
import com.example.mrglisse.model.Ski
import com.example.mrglisse.viewmodel.AlpinViewModel
import com.example.mrglisse.viewmodel.FondViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView

class SkiDetailFragment : Fragment() {
    private val args by navArgs<SkiDetailFragmentArgs>()
    private lateinit var binding: FragmentSkiDetailBinding

    private lateinit var alpinViewModel: AlpinViewModel
    private lateinit var fondViewModel: FondViewModel

    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSkiDetailBinding.inflate(layoutInflater, container, false)

        alpinViewModel = ViewModelProvider(this)[AlpinViewModel::class.java]
        fondViewModel = ViewModelProvider(this)[FondViewModel::class.java]

        //Impossible de rendre Parcelable une classe Abstraite
        //donc je passe deux objets filles en safeArgs
        //que je reconstruis après en objet mère
        val currentSki:Ski = if(args.currentAlpin != null)
            args.currentAlpin!!
        else
            args.currentFond!!

        binding.detailBrand.setText(currentSki.brand)
        binding.detailModel.setText(currentSki.model)
        binding.detailSize.setText(currentSki.size.toString())
        binding.detailPrice.setText(currentSki.price.toString())
        binding.detailOverView.text = currentSki.showOverView()


        binding.detailUpdate.setOnClickListener {
            updateItem(currentSki)
        }

        binding.detailDetele.setOnClickListener {
            deleteItem(currentSki)
        }

        bottomNavigationView = requireActivity().findViewById(R.id.bottomNavigationView)
        bottomNavigationView.visibility = View.INVISIBLE

        return binding.root
    }

    private fun updateItem(ski: Ski){
        val brand = binding.detailBrand.text.toString()
        val model = binding.detailModel.text.toString()
        val price = binding.detailPrice.text
        val size = binding.detailSize.text

        if (inputCheck(brand, model, price, size)){
            when(ski) {
                is Alpin -> {
                    val updatedAlpin = Alpin(args.currentAlpin!!.id, brand, model, price.toString().toDouble(), size.toString().toInt())
                    alpinViewModel.updateAlpin(updatedAlpin)

                    Toast.makeText(requireContext(), R.string.infoUpdateSuccess, Toast.LENGTH_SHORT).show()

                    findNavController().navigate(R.id.action_skiDetailFragment_to_alpinStockFragment)
                }
                is Fond -> {
                    val updatedFond = Fond(args.currentFond!!.id, brand, model, price.toString().toDouble(), size.toString().toInt())
                    fondViewModel.updateFond(updatedFond)

                    Toast.makeText(requireContext(), R.string.infoUpdateSuccess, Toast.LENGTH_SHORT).show()

                    findNavController().navigate(R.id.action_skiDetailFragment_to_fondStockFragment)
                }
                else -> Toast.makeText(requireContext(), R.string.infoError, Toast.LENGTH_LONG).show()
            }
        }else{
            Toast.makeText(requireContext(), R.string.infoVoid, Toast.LENGTH_LONG).show()
        }
    }

    private fun deleteItem(ski: Ski){
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes") { _, _ ->
            when(ski) {
                is Alpin -> {
                    alpinViewModel.deleteAlpin(ski)

                    Toast.makeText(requireContext(), R.string.infoDeleteSuccess, Toast.LENGTH_SHORT).show()
                    findNavController().navigate(R.id.action_skiDetailFragment_to_alpinStockFragment)
                }
                is Fond -> {
                    fondViewModel.deleteFond(ski)

                    Toast.makeText(requireContext(), R.string.infoDeleteSuccess, Toast.LENGTH_SHORT).show()
                    findNavController().navigate(R.id.action_skiDetailFragment_to_fondStockFragment)
                }
                else -> Toast.makeText(requireContext(), R.string.infoError, Toast.LENGTH_LONG).show()
            }

        }
        builder.setNegativeButton("No") { _, _ ->

        }
        builder.setTitle("Delete ${ski.model}?")
        builder.setMessage(R.string.infoDelete)
        builder.create().show()
    }

    private fun inputCheck(brand: String, model: String, price: Editable, size: Editable): Boolean{
        return !(TextUtils.isEmpty(brand) || TextUtils.isEmpty(model) || price.isEmpty() || size.isEmpty())
    }
}