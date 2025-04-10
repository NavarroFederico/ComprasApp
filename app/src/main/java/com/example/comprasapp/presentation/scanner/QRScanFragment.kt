package com.example.comprasapp.presentation.scanner

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.comprasapp.databinding.FragmentQRScanBinding
import com.example.comprasapp.util.BaseFragment
import com.google.zxing.client.android.Intents
import com.journeyapps.barcodescanner.CaptureActivity
import dagger.hilt.android.AndroidEntryPoint

/**
 * A simple [Fragment] subclass.
 * Use the [QRScanFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class QrScanFragment : BaseFragment<FragmentQRScanBinding>() {

    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentQRScanBinding.inflate(inflater, container, false)

    private val REQUEST_CODE_QR_SCAN = 101

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.scanQrButton.setOnClickListener {
            val intent = Intent(context, CaptureActivity::class.java)
            startActivityForResult(intent, REQUEST_CODE_QR_SCAN)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_CODE_QR_SCAN && resultCode == Activity.RESULT_OK) {
            val result = data?.getStringExtra(Intents.Scan.RESULT)
            result?.let {
                Toast.makeText(requireContext(), "Escaneado: $it", Toast.LENGTH_LONG).show()

                val productId = it.toIntOrNull()
                    ?: it.substringAfterLast("/").toIntOrNull()

                if (productId != null) {
                    val action = QrScanFragmentDirections
                        .actionQrScanFragmentToDetailFragment(productId)
                    findNavController().navigate(action)
                } else {
                    Toast.makeText(
                        requireContext(),
                        "QR inválido. Intente nuevamente.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        } else if (requestCode == REQUEST_CODE_QR_SCAN) {
            Toast.makeText(requireContext(), "Escaneo cancelado.", Toast.LENGTH_SHORT).show()
        }
    }
}
