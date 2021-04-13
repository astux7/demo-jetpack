package com.example.demo.repo

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.demo.model.Post
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ListenerRegistration
import com.google.firebase.firestore.QuerySnapshot
// https://www.raywenderlich.com/books/saving-data-on-android/v1.0/chapters/13-reading-to-writing-from-realtime-database
// https://en.proft.me/2019/10/20/firebase-cloud-firestore-and-livedata/

class FBRepository(val database: FirebaseFirestore) {
    // TODO change here - Firestore collection
    private  val PAGE_COLLECTION = "mine"

    private val postsValues = MutableLiveData<List<Post>>()
    private lateinit var postsRegistration: ListenerRegistration

//        fun addPost(content: String, onSuccessAction: () -> Unit, onFailureAction: () -> Unit) {
//            val documentReference = database.collection(POSTS_COLLECTION).document()
//            val post = HashMap<String, Any>()
//
//            post[AUTHOR_KEY] = authenticationManager.getCurrentUser()
//            post[CONTENT_KEY] = content
//            post[TIMESTAMP_KEY] = System.currentTimeMillis()
//            post[ID_KEY] = documentReference.id
//
//            documentReference
//                .set(post)
//                .addOnSuccessListener { onSuccessAction() }
//                .addOnFailureListener { onFailureAction() }
//        }

    fun onPostsValuesChange(): LiveData<List<Post>> {
        listenForPostsValueChanges()
        return postsValues
    }

    private fun listenForPostsValueChanges() {
        postsRegistration = database.collection(PAGE_COLLECTION)
            .addSnapshotListener(EventListener<QuerySnapshot> { value, error ->
                if (error != null || value == null) {
                    return@EventListener
                }

                if (value.isEmpty) {
                    postsValues.postValue(emptyList())
                } else {
                    val posts = ArrayList<Post>()
                    for (doc in value) {
                        val post = doc.toObject(Post::class.java)
                        posts.add(post.copy(id = doc.id))
                    }
                    postsValues.postValue(posts)
                }
            })
    }

    fun stopListeningForPostChanges() = postsRegistration.remove()

    fun updatePostContent(key: String, content: Boolean = true,
                          onSuccessAction: () -> Unit, onFailureAction: () -> Unit) {

        val updatedPost = HashMap<String, Any>()
        updatedPost["display"] = content
        database.collection(PAGE_COLLECTION)
            .document(key)
            .update(updatedPost)
            .addOnSuccessListener { onSuccessAction() }
            .addOnFailureListener { onFailureAction() }
    }

//
//        fun deletePost(key: String, onSuccessAction: () -> Unit, onFailureAction: () -> Unit) {
//            database.collection(PAGE_COLLECTION)
//                .document(key)
//                .delete()
//                .addOnSuccessListener { onSuccessAction() }
//                .addOnFailureListener { onFailureAction() }
//        }

}