package com.example.keno

import android.app.ProgressDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.keno.base.FragmentBase
import com.example.keno.databinding.FragmentCitiesDetailBinding
import com.example.keno.vm.FragmentCityDetailViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
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
        val progressDialog = ProgressDialog.show(requireContext(),"","Loading",true,true)
        CoroutineScope(Dispatchers.Main).launch {
            val cityDetails = vm.getCityDetails(args.id)
            binding.item = cityDetails
            cityDetails.name?.let{
                binding.toolbar.toolbarTitle.text = it
            }
            progressDialog.dismiss()
        }
    }
}
