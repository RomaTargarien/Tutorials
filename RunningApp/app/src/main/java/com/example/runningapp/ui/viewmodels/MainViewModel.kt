package com.example.runningapp.ui.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.room.Insert
import com.example.runningapp.repositories.MainRepository

class MainViewModel @ViewModelInject constructor(val mainRepository: MainRepository) : ViewModel() {

}