package com.example.keno

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.keno.adapters.CitiesAdapter
import com.example.keno.adapters.vh.CityDisplay
import com.kartik.grevocab.adapters.OnBindClickListener
import com.example.keno.base.FragmentBase
import com.example.keno.databinding.FragmentCitiesListBinding
import com.example.keno.vm.FragmentCityListViewModel
import com.example.keno.vm.FragmentCityDetailViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class FragmentCitiesList : FragmentBase() {

    lateinit var adapter: CitiesAdapter
    lateinit var binding: FragmentCitiesListBinding
    private val vm: FragmentCityListViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        setHasOptionsMenu(true)
        binding = FragmentCitiesListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.toolbar.toolbarTitle.text = resources.getString(R.string.Cities)

        adapter = CitiesAdapter(object : OnBindClickListener {
            override fun onItemClick(view: View, position: Int, item: Any) {
                val city = item as CityDisplay
                city.city.id?.let {
                    navigate(FragmentCitiesListDirections.actionFragmentCitiesListToFragmentCityDetail(it))
                }
            }
        })
        binding.groupListView.adapter = adapter

        CoroutineScope(Dispatchers.Main).launch {
            val list = vm.getCitiesList()
            adapter.replaceData(ArrayList(list))
        }
    }
}
