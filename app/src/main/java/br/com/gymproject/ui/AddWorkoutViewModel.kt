package br.com.gymproject.ui

import androidx.lifecycle.ViewModel
import br.com.gymproject.data.WorkoutRepository

class AddWorkoutViewModel: ViewModel() {

    private val workoutRepository by lazy {
        WorkoutRepository()
    }

}