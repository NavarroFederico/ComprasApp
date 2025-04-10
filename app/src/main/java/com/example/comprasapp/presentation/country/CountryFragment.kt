package com.example.comprasapp.presentation.country

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.comprasapp.R
import com.example.comprasapp.databinding.FragmentCountryBinding
import com.example.comprasapp.domain.model.Country
import com.example.comprasapp.util.BaseFragment
import dagger.hilt.android.AndroidEntryPoint


/**
 * A simple [Fragment] subclass.
 * Use the [CountryFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class CountryFragment : BaseFragment<FragmentCountryBinding>() {
    private val countryViewModel: CountryViewModel by activityViewModels()

    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentCountryBinding.inflate(inflater, container, false)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        countryViewModel.selectedCountry.observe(viewLifecycleOwner) { selected ->
            selected?.let {
                when (it) {
                    Country.FAKESTORE -> binding.countryRadioGroup.check(R.id.rbPaisA)
                    Country.PLATZI -> binding.countryRadioGroup.check(R.id.rbPaisB)
                }
            }
        }
        binding.countrySelectButton.setOnClickListener {
            val selectedCountry: Country = when (binding.countryRadioGroup.checkedRadioButtonId) {
                R.id.rbPaisA -> Country.FAKESTORE
                R.id.rbPaisB -> Country.PLATZI
                else -> Country.FAKESTORE
            }
            countryViewModel.selectCountry(selectedCountry)

            val action = CountryFragmentDirections.actionCountryFragmentToHomeFragment()
            findNavController().navigate(action)
        }
    }
}