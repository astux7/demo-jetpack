package com.example.demo.lazymodel

import androidx.compose.runtime.Composable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

object BaseLazyModel {

    interface MultiTypeLazyItem {
        val complexId: ComplexId

        @Composable
        fun GetCompose()
        fun getViewModelFactoryNew(): MultiViewModelFactory = MultiViewModelFactory(complexId)
    }

    abstract class BaseMultiViewModel<T> : ViewModel() {
        abstract fun updateState()

        abstract val _viewState: MutableLiveData<T>
        abstract val viewState: LiveData<T>
    }

    class MultiViewModelFactory(
        private val initialState: ComplexId
    ) : ViewModelProvider.Factory {

        override fun <T : ViewModel?> create(viewModelClass: Class<T>): T {
            return viewModelClass.getConstructor(ComplexId::class.java)
                .newInstance(initialState)
        }
    }

    interface ComplexId {
        fun getKey(): String
    }

    class ComplexIdImp(private val initialKey: String) : ComplexId {
        override fun getKey() = initialKey
    }
}
