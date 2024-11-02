package com.artemqq5.workoutapp.presentation.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.artemqq5.workoutapp.databinding.RecyclerItemExcericeBinding
import com.artemqq5.workoutapp.domain.model.Exercise

class ExercisesAdapter :
    ListAdapter<Exercise, ExercisesAdapter.ExerciseViewHolder>(ExerciseDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExerciseViewHolder {
        val binding =
            RecyclerItemExcericeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ExerciseViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ExerciseViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    // ViewHolder class to bind Exercise data
    class ExerciseViewHolder(private val binding: RecyclerItemExcericeBinding) :
        RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n", "DefaultLocale")
        fun bind(exercise: Exercise) {
            binding.imageExercise.setImageResource(exercise.img)
            binding.textExerciseName.text = exercise.name

            // Format time as min with 0 or 1 decimal place
            val timeInMinutes = exercise.time / 60F
            binding.textExerciseTime.text = if (timeInMinutes % 1 == 0F) {
                String.format("%.0f min", timeInMinutes)
            } else {
                String.format("%.1f min", timeInMinutes)
            }
        }
    }

}


object ExerciseDiffCallback : DiffUtil.ItemCallback<Exercise>() {
    override fun areItemsTheSame(oldItem: Exercise, newItem: Exercise): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: Exercise, newItem: Exercise): Boolean {
        return oldItem == newItem
    }
}

