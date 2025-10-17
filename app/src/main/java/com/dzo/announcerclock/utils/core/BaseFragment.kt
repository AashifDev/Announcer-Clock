package com.dzo.announcerclock.utils.core

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<VB : ViewBinding>(
    private val bindingInflater: (inflater: LayoutInflater, ViewGroup?, Boolean) -> VB
) : Fragment(), MenuProvider {

    private var _binding: VB? = null
    val binding: VB get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = bindingInflater.invoke(inflater, container, false)
        onCreateViewReady()

        requireActivity().addMenuProvider(this, viewLifecycleOwner, Lifecycle.State.RESUMED)

        return binding.root
    }


    open fun onCreateViewReady() {}

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    open fun toast(context: Context, msg:String){
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {}
    override fun onMenuItemSelected(menuItem: MenuItem): Boolean = false

}