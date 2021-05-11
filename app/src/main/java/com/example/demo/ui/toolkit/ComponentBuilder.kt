package com.example.demo.ui.toolkit

import android.content.Context
import androidx.lifecycle.LifecycleOwner

abstract class ComponentBuilder<T : Any, D : ComponentData> {

    open var data: D? = null
    internal var lifecycleOwner: LifecycleOwner? = null

    abstract fun build(context: Context): T

    abstract fun populateView(context: Context, view: T)

    fun withData(componentData: D): ComponentBuilder<T, D> {
        this.data = componentData
        return this
    }

    fun withLifecycleOwner(lifecycleOwner: LifecycleOwner?): ComponentBuilder<T, D> {
        this.lifecycleOwner = lifecycleOwner
        return this
    }
}

interface ComponentData