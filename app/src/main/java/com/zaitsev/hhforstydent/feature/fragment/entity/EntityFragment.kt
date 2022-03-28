package com.zaitsev.hhforstydent.feature.fragment.entity

import android.os.Bundle
import android.view.View
import by.kirich1409.viewbindingdelegate.viewBinding
import com.zaitsev.hhforstydent.R
import com.zaitsev.hhforstydent.core.BaseFragment
import com.zaitsev.hhforstydent.databinding.FragmentEntityBinding


class EntityFragment : BaseFragment(R.layout.fragment_entity) {

    private val viewBinding by viewBinding(FragmentEntityBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initButtons()
    }

    private fun initButtons() = with(viewBinding) {

    }


}