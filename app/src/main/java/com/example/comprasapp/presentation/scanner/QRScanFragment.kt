package com.example.comprasapp.presentation.scanner

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.comprasapp.databinding.FragmentQRScanBinding

/**
 * A simple [Fragment] subclass.
 * Use the [QRScanFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class QRScanFragment : Fragment() {
    private var _binding: FragmentQRScanBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentQRScanBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}