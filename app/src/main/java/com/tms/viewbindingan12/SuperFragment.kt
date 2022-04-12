package com.tms.viewbindingan12

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.tms.viewbindingan12.databinding.FragmentSuperBinding

class SuperFragment : Fragment() {

    private var _binding: FragmentSuperBinding? = null
    private val binding: FragmentSuperBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSuperBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        initViews()
    }

    private fun initViews() {
        binding.btn.setOnClickListener {
            Toast.makeText(requireContext(), "Hello world!", Toast.LENGTH_LONG)
                .show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}