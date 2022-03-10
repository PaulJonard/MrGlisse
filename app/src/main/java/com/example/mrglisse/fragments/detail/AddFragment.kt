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
import com.example.mrglisse.model.Alpin
import com.example.mrglisse.model.Fond
import com.example.mrglisse.viewmodel.AlpinViewModel
import com.example.mrglisse.viewmodel.FondViewModel

class AddFragment : Fragment() {
    private lateinit var alpinViewModel: AlpinViewModel
    private lateinit var fondViewModel: FondViewModel

    private lateinit var brandEditText: EditText
    private lateinit var modelEditText: EditText
    private lateinit var priceEditText: EditText
    private lateinit var sizeEditText: EditText


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add, container, false)

        brandEditText = view.findViewById(R.id.addDetailBrand)
        modelEditText = view.findViewById(R.id.addDetailModel)
        priceEditText = view.findViewById(R.id.addDetailPrice)
        sizeEditText = view.findViewById(R.id.addDetailSize)

        val alpinBundle = requireArguments().get("ALPIN_VIEW_MODEL")
        val fondBundle = requireArguments().get("FOND_VIEW_MODEL")

        val addButton = view.findViewById<Button>(R.id.addDetailButton)
        addButton.setOnClickListener {
            if (alpinBundle != null){
                alpinViewModel = ViewModelProvider(this)[AlpinViewModel::class.java]
                insertToDataBase(Alpin::class.java)
            }
            if (fondBundle != null){
                fondViewModel = ViewModelProvider(this)[FondViewModel::class.java]
                insertToDataBase(Fond::class.java)
            }
        }

        return view
    }

    private fun <T: Any> insertToDataBase(skiType : Class<T>){
        val brand = brandEditText.text.toString()
        val model = modelEditText.text.toString()
        val price = priceEditText.text
        val size = sizeEditText.text

        if (inputCheck(brand, model, price, size)){
            if(skiType.isAssignableFrom(Alpin::class.java)){ //Réfléction de type juste pour pas avoir à faire deux méthodes d'insert suivant le type d'objet
                //Create object
                val alpin = Alpin(0, brand, model, price.toString().toDouble(), size.toString().toInt())
                //Add data to database
                alpinViewModel.addAlpin(alpin)
                Toast.makeText(requireContext(), R.string.infoSuccess, Toast.LENGTH_LONG).show()
                //Navigate Back
                findNavController().navigate(R.id.action_addFragment_to_alpinStockFragment)

            } else if (skiType.isAssignableFrom(Fond::class.java)){
                //Create object
                val fond = Fond(0, brand, model, price.toString().toDouble(), size.toString().toInt())
                //Add data to database
                fondViewModel.addFond(fond)
                Toast.makeText(requireContext(), R.string.infoSuccess, Toast.LENGTH_LONG).show()
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