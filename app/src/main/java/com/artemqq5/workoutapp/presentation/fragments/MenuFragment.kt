package com.artemqq5.workoutapp.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.artemqq5.workoutapp.R
import com.artemqq5.workoutapp.databinding.FragmentMenuBinding
import com.artemqq5.workoutapp.domain.model.Muscles
import com.artemqq5.workoutapp.presentation.adapters.MusclesGroupAdapter
import com.artemqq5.workoutapp.presentation.animations.Animations
import com.artemqq5.workoutapp.presentation.vm.MenuViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MenuFragment : Fragment() {

    private lateinit var binding: FragmentMenuBinding
    private val menuViewModel: MenuViewModel by viewModels()
    private val musclesAdapter by lazy { MusclesGroupAdapter { onMuscleSelected(it) } }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMenuBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.bottomNav.playIcon.startVerticalBounceAnimation()

        binding.bottomNav.settingsIcon.setOnClickListener {
            binding.bottomNav.settingsIcon.startRotateAnimation {
                binding.drawerLayout.open()
            }
        }

        binding.close.setOnClickListener {
            binding.drawerLayout.close()
        }

        binding.recyclerMuscles.adapter = musclesAdapter

        observeMuscleGroups()
    }

    private fun observeMuscleGroups() {
        viewLifecycleOwner.lifecycleScope.launch {
            menuViewModel.muscles.collect { result ->
                result.onSuccess { muscles ->
                    muscles?.let {
                        musclesAdapter.submitList(it)
                    }
                }
            }
        }
    }

    private fun onMuscleSelected(muscle: Muscles) {
        findNavController().navigate(
            R.id.action_menuFragment_to_startFragment,
            bundleOf("muscle" to muscle)
        )
    }

    private fun ImageView.startVerticalBounceAnimation() {
        Animations.verticalBounce(this)
    }

    private fun ImageView.startRotateAnimation(onAnimationEnd: () -> Unit) {
        Animations.rotate(this, onAnimationEnd)
    }
}
