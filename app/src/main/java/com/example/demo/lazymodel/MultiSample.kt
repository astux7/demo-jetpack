package com.example.demo.lazymodel
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.foundation.clickable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.demo.lazymodel.BaseLazyModel.MultiTypeLazyItem
import com.example.demo.lazymodel.BaseLazyModel.BaseMultiViewModel
import com.example.demo.lazymodel.BaseLazyModel.ComplexId
import com.example.demo.lazymodel.BaseLazyModel.MultiViewModelFactory

object MultiSample {

    class ExampleItemType1(override val complexId: ComplexId) :
        MultiTypeLazyItem {
        @Composable
        // Composable functions that return Unit should start with an uppercase letter
        override fun GetCompose() {
            ComposeLayoutString(complexId, getViewModelFactoryNew())
        }
    }

    @Composable
    fun ComposeLayoutString(
        complexId: ComplexId,
        factory: MultiViewModelFactory
    ) {
        val viewModel: ExampleMultiViewModel1 = viewModel(complexId.getKey(), factory)
        val state = viewModel.viewState.observeAsState("10")
        Divider()
        Text(
            text = "type task03", fontSize = 24.sp,
            color = Color.Green
        )
        Text(
            text = state.value, fontSize = 20.sp,
            color = Color.Green,
            modifier = Modifier.clickable(onClick = {
                viewModel.updateState()
            })
        )
    }

    // ComplexViewModel that requires an initial state parameter
    class ExampleMultiViewModel1(private var complexId: ComplexId) :
        BaseMultiViewModel<String>() {
        override val _viewState = MutableLiveData<String>()

        // Watch this LiveData within the @Composable function
        override val viewState = _viewState as LiveData<String>

        // set the current state to the initial state passed from the Factory
        private var currentState = 0

        // Call this function within the @Composable function to update the state
        override fun updateState() {
            currentState += 1
            _viewState.value = "Current id ${complexId.getKey()} state: $currentState"
        }
    }

    class ExampleItemType2(override val complexId: ComplexId) :
        MultiTypeLazyItem {
        @Composable
        override fun GetCompose() {
            ComposeLayoutInt(complexId, getViewModelFactoryNew())
        }
    }

    @Composable
    fun ComposeLayoutInt(
        complexId: ComplexId,
        factory: MultiViewModelFactory
    ) {
        val viewModel: MultiViewModelTwo = viewModel(complexId.getKey(), factory)
        val state = viewModel.viewState.observeAsState(10)
        Divider()
        Text(
            text = "type task04", fontSize = 24.sp,
            color = Color.Green
        )
        Text(
            text = state.value.toString(), fontSize = 20.sp,
            color = Color.Green,
            modifier = Modifier.clickable(onClick = {
                viewModel.updateState()
            })
        )
    }

    class MultiViewModelTwo(private var complexId: ComplexId) :
        BaseMultiViewModel<Int>() {
        override val _viewState = MutableLiveData<Int>()

        // Watch this LiveData within the @Composable function
        override val viewState = _viewState as LiveData<Int>

        // set the current state to the initial state passed from the Factory
        private var currentState = 0

        // Call this function within the @Composable function to update the state
        override fun updateState() {
            currentState += 2
            _viewState.value = currentState
        }
    }
}