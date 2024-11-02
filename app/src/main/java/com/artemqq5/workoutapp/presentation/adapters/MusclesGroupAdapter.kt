package com.artemqq5.workoutapp.presentation.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.artemqq5.workoutapp.databinding.RecyclerMuscleItemBinding
import com.artemqq5.workoutapp.domain.model.Muscles

class MusclesGroupAdapter(
    private val callback: (Muscles) -> Unit
) : ListAdapter<Muscles, MusclesGroupAdapter.MuscleViewHolder>(MusclesDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MuscleViewHolder {
        val binding = RecyclerMuscleItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MuscleViewHolder(binding, callback)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MuscleViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    // ViewHolder with data binding and click callback
    class MuscleViewHolder(
        private val binding: RecyclerMuscleItemBinding,
        private val callback: (Muscles) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bind(muscle: Muscles) {
            binding.image.setImageResource(muscle.icon)
            binding.title.text = muscle.nameMusclesGroup
            binding.subtitle.text = "${muscle.exercises.size} Exercises"
            binding.time.text = formatTime(muscle)
            binding.Start.setOnClickListener { callback(muscle) }
        }

        // Format the total time of exercises in minutes
        @SuppressLint("DefaultLocale")
        private fun formatTime(muscle: Muscles): String {
            val timeInMinutes = muscle.exercises.sumOf { it.time } / 60F
            return if (timeInMinutes % 1 == 0F) {
                String.format("%.0f min", timeInMinutes)
            } else {
                String.format("%.1f min", timeInMinutes)
            }
        }
    }
}


object MusclesDiffCallback : DiffUtil.ItemCallback<Muscles>() {
    override fun areItemsTheSame(oldItem: Muscles, newItem: Muscles): Boolean {
        return oldItem.nameMusclesGroup == newItem.nameMusclesGroup
    }

    override fun areContentsTheSame(oldItem: Muscles, newItem: Muscles): Boolean {
        return oldItem == newItem
    }
}
