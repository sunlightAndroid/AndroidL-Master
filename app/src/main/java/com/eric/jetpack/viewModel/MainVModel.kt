package com.eric.jetpack.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

/**
 * @Author: eric
 * @CreateDate: 2021/1/19 8:18 PM
 * @Description: java类作用描述
 */
class MainVModel(var id: String) : ViewModel() {
    var text = 1

    private fun test() {
        viewModelScope.launch {

        }
    }
}