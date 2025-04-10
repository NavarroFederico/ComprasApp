package com.example.comprasapp.presentation.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.comprasapp.databinding.FragmentHomeBinding
import com.example.comprasapp.presentation.country.CountryViewModel
import com.example.comprasapp.presentation.home.adapter.ProductAdapter
import com.example.comprasapp.util.BaseFragment
import dagger.hilt.android.AndroidEntryPoint


/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    private val viewModel: HomeViewModel by viewModels()
    private val countryViewModel: CountryViewModel by activityViewModels()

    override fun inflateBinding(
        inflater: LayoutInflater, container: ViewGroup?
    ) = FragmentHomeBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        countryViewModel.selectedCountry.observe(viewLifecycleOwner) { country ->
            country?.let {
                viewModel.selectedCountry = it
                viewModel.fetchProducts(it)
            }
        }

        viewModel.products.observe(viewLifecycleOwner) { response ->
            if (response.isSuccessful) {
                response.body()?.let { products ->
                    Log.d("HomeFragment", "Productos obtenidos: $products")

                    binding.productsRecyclerView.layoutManager =
                        LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

                    val adapter = ProductAdapter(products) { selectedProduct ->
                        val action = HomeFragmentDirections
                            .actionHomeFragmentToDetailFragment(productId = selectedProduct.id)
                        findNavController().navigate(action)
                    }
                    binding.productsRecyclerView.adapter = adapter
                }
            } else {
                Log.d(
                    "HomeFragment",
                    "Error al obtener productos: ${response.code()} - ${
                        response.errorBody()?.string()
                    }"
                )
            }
        }

        binding.qrButton.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToQrScanFragment())
        }
    }
}