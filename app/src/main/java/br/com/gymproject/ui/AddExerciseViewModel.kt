package br.com.gymproject.ui

import androidx.lifecycle.ViewModel
import br.com.gymproject.data.ExerciseRepository

class AddExerciseViewModel: ViewModel() {

    private val exerciseRepository by lazy {
        ExerciseRepository()
    }

}