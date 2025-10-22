package com.dzo.announcerclock.presentation.fragments.sound_fragment.adapter

import android.animation.ValueAnimator
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.graphics.toColorInt
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dzo.announcerclock.R
import com.dzo.announcerclock.data.local_source.AppPreferences
import com.dzo.announcerclock.databinding.SoundOptionsRawBindingBinding
import com.dzo.announcerclock.presentation.fragments.sound_fragment.model.SoundOption
import dagger.hilt.android.scopes.FragmentScoped
import javax.inject.Inject

@FragmentScoped
class SoundOptionAdapter @Inject constructor() :
    ListAdapter<SoundOption, SoundOptionAdapter.ViewHolder>(DIFF_CALLBACK) {

    var onItemClick: ((SoundOption) -> Unit)? = null
    private var themeColor = AppPreferences.ThemeManager.getActiveThemeColor()


    init {
        AppPreferences.ThemeManager.registerListener { colorHex ->
            themeColor = colorHex
            notifyDataSetChanged()
        }
    }

    inner class ViewHolder(val binding: SoundOptionsRawBindingBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: SoundOption) = with(binding) {
            title.text = item.title

            //Speaker icon visibility
            selectedItem.visibility = if (item.isSelected) {
                selectedItem.setImageResource(R.drawable.ic_speaker)
                selectedItem.setColorFilter(themeColor!!.toColorInt())
                View.VISIBLE
            } else {
                View.GONE
            }

            //Animate text size
            val startSize =
                title.textSize / title.resources.displayMetrics.scaledDensity // current size in sp
            val endSize = if (item.isSelected) 22f else 18f

            val sizeAnimator = ValueAnimator.ofFloat(startSize, endSize)
            sizeAnimator.duration = 200
            sizeAnimator.addUpdateListener {
                title.setTextSize(TypedValue.COMPLEX_UNIT_SP, it.animatedValue as Float)
            }
            sizeAnimator.start()

            //Change text color
            title.setTextColor(
                if (item.isSelected) themeColor!!.toColorInt() else ContextCompat.getColor(root.context, R.color.black)
            )
            //Zoom animation
            root.animate()
                .scaleX(if (item.isSelected) 1.05f else 1f)
                .scaleY(if (item.isSelected) 1.05f else 1f)
                .setDuration(200)
                .start()

            //Click handler
            root.setOnClickListener {
                onItemClick?.invoke(item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = SoundOptionsRawBindingBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<SoundOption>() {
            override fun areItemsTheSame(oldItem: SoundOption, newItem: SoundOption): Boolean {
                return oldItem.title == newItem.title
            }

            override fun areContentsTheSame(oldItem: SoundOption, newItem: SoundOption): Boolean {
                return oldItem == newItem
            }
        }
    }
}
