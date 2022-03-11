package com.example.mrglisse.fragments.detail

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.mrglisse.R
import com.example.mrglisse.databinding.FragmentAddBinding
import com.example.mrglisse.model.Alpin
import com.example.mrglisse.model.Fond
import com.example.mrglisse.viewmodel.AlpinViewModel
import com.example.mrglisse.viewmodel.FondViewModel

class AddFragment : Fragment() {
    private lateinit var binding: FragmentAddBinding

    private lateinit var alpinViewModel: AlpinViewModel
    private lateinit var fondViewModel: FondViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAddBinding.inflate(layoutInflater, container, false)

        val alpinBundle = requireArguments().get("ALPIN_VIEW_MODEL")
        val fondBundle = requireArguments().get("FOND_VIEW_MODEL")

        binding.addDetailButton.setOnClickListener {
            if (alpinBundle != null){
                alpinViewModel = ViewModelProvider(this)[AlpinViewModel::class.java]
                insertToDataBase(Alpin::class.java)
            }
            if (fondBundle != null){
                fondViewModel = ViewModelProvider(this)[FondViewModel::class.java]
                insertToDataBase(Fond::class.java)
            }
        }

        return binding.root
    }

    private fun <T: Any> insertToDataBase(skiType : Class<T>){
        val brand = binding.addDetailBrand.text.toString()
        val model = binding.addDetailModel.text.toString()
        val price = binding.addDetailPrice.text
        val size = binding.addDetailSize.text

        if (inputCheck(brand, model, price, size)){
            if(skiType.isAssignableFrom(Alpin::class.java)){ //Réfléction de type, juste pour pas avoir à faire deux méthodes d'insert suivant le type d'objet
                //Create object
                val alpin = Alpin(0, brand, model, price.toString().toDouble(), size.toString().toInt())
                //Add data to database
                alpinViewModel.addAlpin(alpin)
                Toast.makeText(requireContext(), R.string.infoSuccess, Toast.LENGTH_SHORT).show()
                //Navigate Back
                findNavController().navigate(R.id.action_addFragment_to_alpinStockFragment)

            } else if (skiType.isAssignableFrom(Fond::class.java)){
                //Create object
                val fond = Fond(0, brand, model, price.toString().toDouble(), size.toString().toInt())
                //Add data to database
                fondViewModel.addFond(fond)
                Toast.makeText(requireContext(), R.string.infoSuccess, Toast.LENGTH_SHORT).show()
                //Navigate Back
                findNavController().navigate(R.id.action_addFragment_to_fondStockFragment)
            }
        }else{
            Toast.makeText(requireContext(), R.string.infoVoid, Toast.LENGTH_LONG).show()
        }
    }

    private fun inputCheck(brand: String, model: String, price: Editable, size: Editable): Boolean{
        return !(TextUtils.isEmpty(brand) || TextUtils.isEmpty(model) || price.isEmpty() || size.isEmpty())
    }
}