package com.example.keno

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.keno.adapters.CitiesAdapter
import com.example.keno.adapters.vh.CityDisplay
import com.kartik.grevocab.adapters.OnBindClickListener
import com.example.keno.base.FragmentBase
import com.example.keno.databinding.FragmentCitiesDetailBinding
import com.example.keno.databinding.FragmentCitiesListBinding
import com.example.keno.vm.FragmentCityDetailViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class FragmentCityDetail : FragmentBase() {

    lateinit var binding: FragmentCitiesDetailBinding
    private val args:FragmentCityDetailArgs by navArgs()
    private val vm: FragmentCityDetailViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        setHasOptionsMenu(true)
        binding = FragmentCitiesDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val id = args.id
        //toolbarTitle(resources.getString(R.string.Cities))
        CoroutineScope(Dispatchers.Main).launch {
            val cityDetails = vm.getCityDetails(id)
            binding.item = cityDetails
            cityDetails.name?.let{
                toolbarTitle(it)
            }
        }
    }
}
