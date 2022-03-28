package com.zaitsev.hhforstydent.feature.fragment.authorization.launch

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.zaitsev.hhforstydent.R
import com.zaitsev.hhforstydent.core.BaseFragment
import com.zaitsev.hhforstydent.databinding.FragmentLaunchBinding
import by.kirich1409.viewbindingdelegate.viewBinding

class LaunchFragment : BaseFragment(R.layout.fragment_launch) {

    private val viewBinding by viewBinding(FragmentLaunchBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initButtons()
    }

    private fun initButtons() = with(viewBinding) {
        buttonAuth.setOnClickListener { findNavController().navigate(R.id.action_launchFragment_to_authFragment) }
        buttonReg.setOnClickListener { findNavController().navigate(R.id.action_launchFragment_to_registrationFragment) }
    }


}