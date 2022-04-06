package com.zaitsev.hhforstydent.core

import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import com.zaitsev.hhforstydent.MainActivity

abstract class BaseFragment : Fragment {

    constructor(@LayoutRes contentLayoutId: Int) : super(contentLayoutId)

    constructor() : super()

}