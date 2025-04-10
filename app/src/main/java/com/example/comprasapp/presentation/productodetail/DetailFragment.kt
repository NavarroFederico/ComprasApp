package com.example.comprasapp.presentation.productodetail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import coil.load
import com.example.comprasapp.R
import com.example.comprasapp.databinding.FragmentDetailBinding
import com.example.comprasapp.presentation.country.CountryViewModel
import com.example.comprasapp.util.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

/**
 * A simple [Fragment] subclass.
 * Use the [DetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class DetailFragment : BaseFragment<FragmentDetailBinding>() {

    private var productId: Int = 0

    private val detailViewModel: DetailViewModel by viewModels()
    private val countryViewModel: CountryViewModel by activityViewModels()

    override fun inflateBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentDetailBinding.inflate(inflater, container, false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            productId = DetailFragmentArgs.fromBundle(it).productId
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        countryViewModel.selectedCountry.observe(viewLifecycleOwner) { country ->
            country?.let {
                // Llama al ViewModel pasando el país
                detailViewModel.fetchProductDetail(productId, it)
            }
        }

        detailViewModel.productDetail.observe(viewLifecycleOwner) { response ->
            if (response.isSuccessful) {
                val product = response.body()
                if (product != null) {
                    binding.productTitle.text = product.title
                    binding.productDescription.text = product.description
                    binding.productPrice.text = "$${product.price}"
                    binding.productCategory.text = product.category

                    binding.productImage.load(product.image) {
                        placeholder(R.drawable.ic_placeholder_banner)
                        error(R.drawable.ic_error_image)
                        crossfade(true)
                    }
                } else {
                    Log.d("DetailFragment", "El cuerpo de la respuesta es null")
                }
            } else {
                Log.d(
                    "DetailFragment",
                    "Error al obtener detalle: ${response.code()} - ${
                        response.errorBody()?.string()
                    }"
                )
            }
        }

        binding.buyButton.setOnClickListener {
            val action = DetailFragmentDirections.actionDetailFragmentToPaymentFragment(productId)
            findNavController().navigate(action)
        }
    }
}
