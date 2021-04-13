package com.eric.jetpack.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 * @Author: eric
 * @CreateDate: 2021/1/22 2:59 PM
 * @Description: java类作用描述
 */
class MainVmodelFactory(var id: String) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainVModel(id) as T
    }
}
