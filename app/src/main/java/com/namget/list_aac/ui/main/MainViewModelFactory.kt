package com.namget.list_aac.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.namget.list_aac.data.repository.Repository
import com.namget.list_aac.util.Logger

@Suppress("UNCHECKED_CAST")
class MainViewModelFactory(val repository: Repository, val logger: Logger) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(repository, logger) as T
    }
}