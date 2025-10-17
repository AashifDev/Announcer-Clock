package com.dzo.announcerclock.presentation.fragments.repeat_option.adapter

import android.animation.ValueAnimator
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dzo.announcerclock.R
import com.dzo.announcerclock.databinding.RepeatOptionRawItemBinding
import com.dzo.announcerclock.presentation.fragments.repeat_option.model.RepeatOption
import dagger.hilt.android.scopes.FragmentScoped
import javax.inject.Inject

@FragmentScoped
class RepeatOptionAdapter @Inject constructor() :
    ListAdapter<RepeatOption, RepeatOptionAdapter.ViewHolder>(DIFF_CALLBACK) {

    var onItemClick: ((RepeatOption) -> Unit)? = null

    inner class ViewHolder(val binding: RepeatOptionRawItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: RepeatOption) = with(binding) {
            title.text = item.title

            //Show/hide checkmark
            selectedItem.visibility = if (item.isSelected) {
                selectedItem.setImageResource(R.drawable.ic_check)
                View.VISIBLE
            } else View.GONE

            //Animate text size
            val startSize =
                title.textSize / title.resources.displayMetrics.scaledDensity // current sp
            val endSize = if (item.isSelected) 22f else 18f // target sp

            val sizeAnimator = ValueAnimator.ofFloat(startSize, endSize)
            sizeAnimator.duration = 200
            sizeAnimator.addUpdateListener {
                title.setTextSize(TypedValue.COMPLEX_UNIT_SP, it.animatedValue as Float)
            }
            sizeAnimator.start()

            //Change color
            title.setTextColor(
                ContextCompat.getColor(
                    root.context,
                    if (item.isSelected) R.color.blue else R.color.black
                )
            )

            // Optional scale animation
            root.animate()
                .scaleX(if (item.isSelected) 1.05f else 1f)
                .scaleY(if (item.isSelected) 1.05f else 1f)
                .setDuration(200)
                .start()

            // Handle click
            root.setOnClickListener {
                onItemClick?.invoke(item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = RepeatOptionRawItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<RepeatOption>() {
            override fun areItemsTheSame(oldItem: RepeatOption, newItem: RepeatOption): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: RepeatOption, newItem: RepeatOption): Boolean {
                return oldItem == newItem
            }
        }
    }
}
