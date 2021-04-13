package com.example.demo.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.demo.model.Post
import com.example.demo.repo.FBRepository

class PostViewModel(private val repo: FBRepository): ViewModel() {
    private val _posts = MutableLiveData(emptyList<Post>())

    var posts: LiveData<List<Post>> = _posts

    fun onPostsValuesChange(): LiveData<List<Post>> = repo.onPostsValuesChange()

    fun onPostUpdateFields(post: Post) {
        val key: String = post.id ?: ""
        repo.updatePostContent(
            key,
            false,
            { Log.d("POST", "key $key added successfully $post")},
            { Log.d("POST", "key $key failed to add $post")}
        )
    }


    fun onPostsChange(post: List<Post>) {
        _posts.value = post
    }

    fun stopListeningForPostChanges() = repo.stopListeningForPostChanges()
}