package com.example.keno.base

import android.content.Context
import android.net.ConnectivityManager
import android.text.Spannable
import android.text.SpannableString
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import org.koin.core.component.KoinComponent

@Suppress("JAVA_CLASS_ON_COMPANION")
abstract class FragmentBase : Fragment(), KoinComponent {

    fun toolbarTitle(title: String) {
        (activity as? ActivityBase)?.supportActionBar?.title = title
    }

    fun isConnected(): Boolean {
        val connectivityManager = requireActivity().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        return activeNetworkInfo?.isConnected == true
    }

    open fun onBackPressed(): Boolean {
        return false
    }

    fun navigate(navDirections: NavDirections){
        findNavController().navigate(navDirections)
    }

}
