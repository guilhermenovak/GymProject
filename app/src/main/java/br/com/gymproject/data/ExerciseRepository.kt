package br.com.gymproject.data

import br.com.gymproject.data.local.database.Exercise
import br.com.gymproject.data.network.ExerciseRemoteDataSource

class ExerciseRepository {

    private val exerciseRemoteDataSource: ExerciseRemoteDataSource = ExerciseRemoteDataSource()

    fun insertExercise(exercise: Exercise) = exerciseRemoteDataSource.insertExercise(exercise)

    fun getAllExercises(myCallback: (result: MutableList<Exercise>) -> Unit) = exerciseRemoteDataSource.getAllExercises(myCallback)

}