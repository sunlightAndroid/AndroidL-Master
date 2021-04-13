package com.eric.jetpack.liveData

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * @Author: eric
 * @CreateDate: 2/24/21 8:10 PM
 * @Description: java类作用描述
 */
class LiveDataVModel : ViewModel() {
    private var liveData: MutableLiveData<Int> = MutableLiveData()
    fun getLike(): MutableLiveData<Int> {
        return liveData
    }
    fun star() {
        if (liveData.value == null) {
            liveData.value = 0
        }
        liveData.value = liveData.value!! + 1
    }

    fun down() {
        if (liveData.value == null) {
            liveData.value = 0
        }
        liveData.value = liveData.value!! - 1
    }
}