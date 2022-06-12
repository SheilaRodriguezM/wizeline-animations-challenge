package com.wizeline.academy.animations.ui.splash_screen

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.graphics.Path
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.wizeline.academy.animations.databinding.SplashFragmentBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashFragment : Fragment() {

    private var _binding: SplashFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = SplashFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        animateLogo()
    }
    override fun onResume() {
        super.onResume()
        lifecycleScope.launch {
            delay(2000)
            goToHomeScreen()
        }
    }

    private fun goToHomeScreen() {
        val directions = SplashFragmentDirections.toMainFragment()
        findNavController().navigate(directions)
    }

    private fun animateLogo(){

        val path = Path().apply {
            arcTo(-50f, -250f, 500f, 500f, 60f, -200f, true)
        }

        val animatorMovement = ObjectAnimator.ofFloat(view, View.X, View.Y, path).apply {
            duration = 2000
        }

        val animatorTransparency = ObjectAnimator.ofFloat(view, View.ALPHA, 0f, 1f).apply {
            duration = 2000
        }

        val animatorSet = AnimatorSet()
        animatorSet.playTogether(animatorMovement, animatorTransparency)
        animatorSet.start()
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}