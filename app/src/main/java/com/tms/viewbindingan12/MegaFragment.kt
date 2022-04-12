package com.tms.viewbindingan12

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.tms.viewbindingan12.databinding.FragmentMegaBinding

class MegaFragment : Fragment(R.layout.fragment_mega) {
    private val binding by viewBinding<FragmentMegaBinding>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.imageView.setOnClickListener {

        }
    }
}