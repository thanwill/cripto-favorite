package com.app.criptofavorite.view.ui.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.app.criptofavorite.databinding.FragmentHomeBinding
import com.app.criptofavorite.model.Finance
import com.app.criptofavorite.repository.remote.FinanceClient
import com.app.criptofavorite.view.adapter.CurrencyAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel


    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root


        var adapter = CurrencyAdapter()
        binding.recyclerView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(context)
        binding.recyclerView.adapter = adapter


        val service = FinanceClient.createService()
        val call = service.getExchangeRates()
        call.enqueue(object : Callback<Finance> {
            override fun onResponse(
                call: Call<Finance>,
                response: Response<Finance>
            ) {
                if (response.isSuccessful) {
                    val body = response.body()
                    println("Moedas: ${body?.results?.currencies}")

                    if (body != null) {
                        adapter.addCurrency(body.results.currencies.USD)
                        adapter.addCurrency(body.results.currencies.EUR)
                        adapter.addCurrency(body.results.currencies.GBP)
                        adapter.addCurrency(body.results.currencies.ARS)
                        adapter.addCurrency(body.results.currencies.CAD)
                        adapter.addCurrency(body.results.currencies.AUD)
                        adapter.addCurrency(body.results.currencies.JPY)
                        adapter.addCurrency(body.results.currencies.CNY)
                        adapter.addCurrency(body.results.currencies.BTC)
                    }

                }
            }

            override fun onFailure(call: Call<Finance>, t: Throwable) {
                println("Falha na requisição: ${t.message}")
            }
        })

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}