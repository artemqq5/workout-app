package com.artemqq5.workoutapp.presentation.animations

import android.animation.Animator
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.AccelerateInterpolator
import android.widget.ImageView

object Animations {

    // Vertical bounce animation
    fun verticalBounce(view: ImageView) {
        val animator = ObjectAnimator.ofFloat(view, "translationY", 0f, -50f)
        animator.duration = 1000
        animator.repeatMode = ValueAnimator.REVERSE
        animator.repeatCount = ValueAnimator.INFINITE
        animator.interpolator = AccelerateDecelerateInterpolator()
        animator.start()
    }

    // Rotate animation with a callback on animation end
    fun rotate(view: ImageView, onAnimationEnd: () -> Unit) {
        val rotateAnimator = ObjectAnimator.ofFloat(view, "rotation", 0f, 360f)
        rotateAnimator.duration = 800
        rotateAnimator.interpolator = AccelerateInterpolator()

        rotateAnimator.addListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(animation: Animator) {}
            override fun onAnimationEnd(animation: Animator) {
                onAnimationEnd()
            }
            override fun onAnimationCancel(animation: Animator) {}
            override fun onAnimationRepeat(animation: Animator) {}
        })

        rotateAnimator.start()
    }
}
