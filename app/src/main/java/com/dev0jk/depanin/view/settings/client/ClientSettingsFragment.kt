package com.dev0jk.depanin.view.settings.client

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.dev0jk.depanin.databinding.FragmentSettingBinding
import com.dev0jk.depanin.utils.Strings


class ClientSettingsFragment: Fragment()  {

    private var _binding : FragmentSettingBinding? =null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSettingBinding.inflate(inflater, container, false)
        val settingsAdapter = ClientSettingsAdapter(requireContext(), Strings().settingsClient,this.parentFragmentManager)
        binding.settings.layoutManager = LinearLayoutManager(requireContext())
        binding.settings.layoutManager = LinearLayoutManager(
            requireContext(),
            )

        binding.settings.adapter = settingsAdapter

        return binding.root
    }

}