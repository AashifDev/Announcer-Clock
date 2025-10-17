package com.dzo.announcerclock.presentation.fragments.our_app_fragment

import android.os.Bundle
import android.view.View
import com.dzo.announcerclock.databinding.FragmentOurAppBinding
import com.dzo.announcerclock.presentation.fragments.our_app_fragment.adapter.OtherAppAdapter
import com.dzo.announcerclock.presentation.fragments.our_app_fragment.model.otherAppList
import com.dzo.announcerclock.utils.core.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import com.dzo.announcerclock.utils.Utils.openPlayStore


@AndroidEntryPoint
class OurAppFragment : BaseFragment<FragmentOurAppBinding>(FragmentOurAppBinding::inflate) {

    @Inject
    lateinit var ourAppAdapter: OtherAppAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.ourAppRecyclerView.adapter = ourAppAdapter
        ourAppAdapter.submitList(otherAppList)

        ourAppAdapter.onItemClick = {
            openPlayStore(requireContext(),it.packageName)
        }

    }

}

