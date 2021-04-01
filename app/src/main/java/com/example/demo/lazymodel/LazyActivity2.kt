package com.example.demo.lazymodel

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.demo.lazymodel.BaseLazyModel.ComplexIdImp

class LazyActivity2 : AppCompatActivity() {

    @ExperimentalAnimationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LazyColumnForMultiDemo()
        }
    }

    private val itemList = listOf(
        MultiSample.ExampleItemType1(
            ComplexIdImp("1")
        ),
        MultiSample.ExampleItemType2(
            ComplexIdImp("2")
        )
    )

    @Composable
    fun LazyColumnForMultiDemo() {

        // Remember our own LazyListState
        val listState = rememberLazyListState()

        // Provide it to LazyColumn
        LazyColumn(state = listState) {
            items(itemList.size, ::buildItem) { index ->
                itemList[index].GetCompose()
            }
        }
    }

    private fun buildItem(index: Int) = itemList[index].complexId.getKey()

    @Preview
    @Composable
    fun PreviewStoryList() {
        LazyColumnForMultiDemo()
    }
}

