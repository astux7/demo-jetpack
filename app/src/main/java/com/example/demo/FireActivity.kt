package com.example.demo

import android.content.Context
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.Observer
import com.example.demo.model.Post
import com.example.demo.viewmodel.PostViewModel
import org.koin.android.ext.android.inject

class FireActivity : AppCompatActivity() {
    private val vm : PostViewModel by inject()

    override fun onStart() {
        super.onStart()
        listenForPostsUpdates()
    }

    override fun onStop() {
        super.onStop()
        vm.stopListeningForPostChanges()
    }

    @ExperimentalAnimationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StoryListPage()
        }
    }

    private fun listenForPostsUpdates() {
        vm.onPostsValuesChange()
            .observe(this, Observer(::onPostsUpdate))
    }

    private fun onPostsUpdate(posts: List<Post>) {
        vm.onPostsChange(posts)
    }

    @ExperimentalAnimationApi
    @Composable
    fun StoryListPage() {
        val listState by vm.posts.observeAsState(initial = emptyList())

        LazyColumn( modifier = Modifier.fillMaxWidth()) {
            items(
                listState.count()
            ) { page ->
                Tile(
                    listState[page].title ?: "",
                    listState[page].body ?: "",
                    listState[page].url ?: "",
                    listState[page].display,
                    context = applicationContext
                )
            }
        }
    }
}