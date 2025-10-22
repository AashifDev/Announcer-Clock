package com.dzo.announcerclock.presentation.fragments.our_app_fragment.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.graphics.toColorInt
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dzo.announcerclock.data.local_source.AppPreferences
import com.dzo.announcerclock.databinding.OurAppRowItemBinding
import com.dzo.announcerclock.presentation.fragments.our_app_fragment.model.OtherAppModel
import dagger.hilt.android.scopes.FragmentScoped
import javax.inject.Inject

@FragmentScoped
class OtherAppAdapter @Inject constructor(
) : ListAdapter<OtherAppModel, OtherAppAdapter.OtherAppViewHolder>(OtherAppDiffCallback) {

    var onItemClick: ((OtherAppModel) -> Unit)? = null

    private var themeColor = AppPreferences.ThemeManager.getActiveThemeColor()

    init {
        AppPreferences.ThemeManager.registerListener { colorHex ->
            themeColor = colorHex
            notifyDataSetChanged()
        }
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): OtherAppViewHolder {
        val binding = OurAppRowItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return OtherAppViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: OtherAppViewHolder,
        position: Int
    ) {
        holder.bind(getItem(position))
    }

    inner class OtherAppViewHolder(private val binding: OurAppRowItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: OtherAppModel) {
            binding.appLogo.setImageResource(item.logo)
            binding.appName.text = item.appName
            binding.appName.setTextColor(themeColor!!.toColorInt())
            binding.root.setOnClickListener {
                onItemClick?.invoke(item)
            }
        }

    }

    companion object {
        private val OtherAppDiffCallback = object : DiffUtil.ItemCallback<OtherAppModel>() {
            override fun areItemsTheSame(
                oldItem: OtherAppModel,
                newItem: OtherAppModel
            ): Boolean {
                return oldItem.appName == newItem.appName
            }

            override fun areContentsTheSame(
                oldItem: OtherAppModel,
                newItem: OtherAppModel
            ): Boolean {
                return oldItem.appName == newItem.appName
            }

        }
    }
}