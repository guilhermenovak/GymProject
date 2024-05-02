package br.com.gymproject.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.gymproject.data.ExerciseRepository
import br.com.gymproject.data.WorkoutRepository
import br.com.gymproject.data.local.database.Workout
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WorkoutListViewModel: ViewModel() {
    private val workoutRepository by lazy {
        WorkoutRepository()
    }

    private var _workout = MutableLiveData<List<Workout?>>()
    val workout: LiveData<List<Workout?>> get() = _workout

    fun getAllWorkouts() {
        viewModelScope.launch(Dispatchers.Main) {
            workoutRepository.getAllWorkouts() {
                _workout.value = it
            }
        }
    }
}
