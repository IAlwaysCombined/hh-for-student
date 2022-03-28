package com.zaitsev.hhforstydent.core

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlin.coroutines.CoroutineContext

open class BaseViewModel : ViewModel(), CoroutineScope {


    override val coroutineContext: CoroutineContext by lazy {
        SupervisorJob() + Dispatchers.IO
    }

    override fun onCleared() {
        super.onCleared()
        coroutineContext.cancel()
    }

}