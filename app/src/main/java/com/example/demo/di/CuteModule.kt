package com.example.demo.di

import com.example.demo.repo.FBRepository
import com.example.demo.viewmodel.PostViewModel
import com.google.firebase.firestore.FirebaseFirestore
import org.koin.dsl.module

object CuteModule {
    private val database = FirebaseFirestore.getInstance()
    val module = module {
        single { FBRepository(database) }
        single { PostViewModel(get()) }
    }
}
