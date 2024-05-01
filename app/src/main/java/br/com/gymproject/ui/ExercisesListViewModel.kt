package br.com.gymproject.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.gymproject.data.ExerciseRepository
import br.com.gymproject.data.local.database.Exercise
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ExercisesListViewModel: ViewModel() {
    private val exerciseRepository by lazy {
        ExerciseRepository()
    }

    private var _exercise = MutableLiveData<List<Exercise?>>()
    val exercise: LiveData<List<Exercise?>> get() = _exercise

    fun getAllExercises() {
        viewModelScope.launch(Dispatchers.Main) {
            exerciseRepository.getAllExercises() {
                _exercise.value = it
            }
        }
    }
}
