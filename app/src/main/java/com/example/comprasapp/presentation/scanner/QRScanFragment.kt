package com.example.comprasapp.presentation.scanner

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.comprasapp.databinding.FragmentQRScanBinding
import com.example.comprasapp.util.BaseFragment

/**
 * A simple [Fragment] subclass.
 * Use the [QRScanFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class QRScanFragment : BaseFragment<FragmentQRScanBinding>() {

    private val viewModel: QRScanViewModel by viewModels()
    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentQRScanBinding.inflate(inflater, container, false)

}