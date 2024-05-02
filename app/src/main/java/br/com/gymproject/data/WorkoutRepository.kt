package br.com.gymproject.data

import br.com.gymproject.data.local.database.Exercise
import br.com.gymproject.data.local.database.Workout
import br.com.gymproject.data.network.ExerciseRemoteDataSource
import br.com.gymproject.data.network.WorkoutRemoteDataSource

class WorkoutRepository {

    private val workoutRemoteDataSource: WorkoutRemoteDataSource = WorkoutRemoteDataSource()

    fun insertWorkout(workout: Workout) = workoutRemoteDataSource.insertWorkout(workout)

    fun updateWorkout(workout: Workout) = workoutRemoteDataSource.updateWorkout(workout)

    fun getAllWorkouts(myCallback: (result: MutableList<Workout>) -> Unit) = workoutRemoteDataSource.getAllWorkouts(myCallback)

}