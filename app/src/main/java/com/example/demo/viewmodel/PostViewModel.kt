package com.example.demo.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.demo.model.Post
import com.example.demo.repo.FBRepository

class PostViewModel(private val repo: FBRepository): ViewModel() {
    private val _posts = MutableLiveData(emptyList<Post>())

    var posts: LiveData<List<Post>> = _posts

    fun onPostsValuesChange(): LiveData<List<Post>> = repo.onPostsValuesChange()

    fun onPostsChange(post: List<Post>) {
        _posts.value = post
    }

    fun stopListeningForPostChanges() = repo.stopListeningForPostChanges()
}