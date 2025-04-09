package com.example.comprasapp.presentation.payment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.comprasapp.databinding.FragmentPaymentBinding
import com.example.comprasapp.util.BaseFragment

/**
 * A simple [Fragment] subclass.
 * Use the [PaymentFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PaymentFragment : BaseFragment<FragmentPaymentBinding>() {
   private val viewModel :PaymentViewModel by viewModels()

    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentPaymentBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.paymentButton.setOnClickListener {
            //Para realizar alguna validación de la tarjeta y procesar el pago
        }
    }
}