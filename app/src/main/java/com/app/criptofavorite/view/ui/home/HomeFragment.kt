package com.app.criptofavorite.view.ui.home

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.criptofavorite.R
import com.app.criptofavorite.databinding.FragmentHomeBinding
import com.app.criptofavorite.view.adapter.CurrencyAdapter

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var adapter: CurrencyAdapter

    // Mova a declaração de editTextValueInReais para ser uma propriedade da classe
    private lateinit var editTextValueInReais: EditText

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        setupRecyclerView()

        // Observe o LiveData no ViewModel
        homeViewModel.currencies.observe(viewLifecycleOwner, Observer { currencies ->
            adapter.setCurrencies(currencies)
            updateCurrencyValues(editTextValueInReais.text.toString().toDoubleOrNull() ?: 0.0)
        })
        editTextValueInReais = binding.editTextValueInReais

        editTextValueInReais.addTextChangedListener(object : TextWatcher {

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                val valueInReais = s.toString().toDoubleOrNull() ?: 0.0
                updateCurrencyValues(valueInReais)
            }

        })
        return root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Observador do LiveData
        homeViewModel.currencies.observe(viewLifecycleOwner, Observer { currencies ->
            adapter.setCurrencies(currencies)
            // Agora editTextValueInReais está acessível
            val multiplier = editTextValueInReais.text.toString().toDoubleOrNull() ?: 1.0
            updateCurrencyValues(multiplier)
        })
    }

    private fun updateCurrencyValues(valueInReais: Double?) {
        val updatedCurrencies = if (valueInReais == null || valueInReais == 0.0) {
            // Se o valor for nulo ou zero, use os valores originais de 'buy'
            homeViewModel.currencies.value
        } else {
            // Caso contrário, faça a conversão
            homeViewModel.currencies.value?.map { currency ->
                currency.copy(buy = valueInReais / currency.buy)
            }
        }

        updatedCurrencies?.let {
            adapter.setCurrencies(it)
        }
    }

    private fun setupRecyclerView() {
        adapter = CurrencyAdapter()
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}