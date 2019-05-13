package com.namget.list_aac.ui.base

interface BaseAdapterInf<T> {
    fun addItems(items: T)
    fun setItems(items: T)
    fun clear()
}