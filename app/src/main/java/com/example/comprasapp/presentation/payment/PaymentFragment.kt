package com.example.comprasapp.presentation.payment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.comprasapp.databinding.FragmentPaymentBinding
import com.example.comprasapp.presentation.country.CountryViewModel
import com.example.comprasapp.util.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

/**
 * A simple [Fragment] subclass.
 * Use the [PaymentFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class PaymentFragment : BaseFragment<FragmentPaymentBinding>() {

    private val paymentViewModel: PaymentViewModel by viewModels()
    private val countryViewModel: CountryViewModel by activityViewModels()

    private var productId: Int = 0

    override fun inflateBinding(
        inflater: LayoutInflater, container: ViewGroup?
    ) = FragmentPaymentBinding.inflate(inflater, container, false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            productId = PaymentFragmentArgs.fromBundle(it).productId
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        countryViewModel.selectedCountry.observe(viewLifecycleOwner) { country ->
            country?.let {
                paymentViewModel.loadProductForPayment(productId, it)

                binding.selectedCountryText.text = "País: ${it.name}"
            }
        }

        paymentViewModel.product.observe(viewLifecycleOwner) { product ->
            binding.productTitle.text = product.title
            binding.productPrice.text = "$${product.price}"
        }

        binding.paymentButton.setOnClickListener {
            val cardNumber = binding.cardNumberEditText.text.toString().trim()
            val cvv = binding.cvvEditText.text.toString().trim()
            val expiry = binding.expiryEditText.text.toString().trim()

            val isValidCard = cardNumber == "4242424242424242"
            val isValidCvv = cvv == "123"
            val isValidExpiry = expiry == "12/25"

            if (isValidCard && isValidCvv && isValidExpiry) {
                binding.paymentErrorText.visibility = View.GONE

                paymentViewModel.processPayment()
                Toast.makeText(requireContext(), "Pago procesado correctamente", Toast.LENGTH_SHORT).show()

                findNavController().navigate(PaymentFragmentDirections.actionPaymentFragmentToHomeFragment())

            } else {
                binding.paymentErrorText.visibility = View.VISIBLE
            }
        }
    }
}
