package com.ismita.chromerivals.views.ui

import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ismita.chromerivals.databinding.FragmentLootingBinding

class LootingFragment : Fragment() {

    private lateinit var binding: FragmentLootingBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLootingBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.itemObtained.visibility = View.GONE
        binding.animationView.visibility = View.GONE
        binding.openBoxBtn.setOnClickListener {
            binding.animationView.playAnimation()
            binding.animationView.visibility = View.VISIBLE
            Handler().postDelayed({
                binding.itemObtained.visibility = View.VISIBLE
            }, 1000)
            Handler().postDelayed({
                binding.itemObtained.visibility = View.GONE
            }, 3000)
        }



    }

}