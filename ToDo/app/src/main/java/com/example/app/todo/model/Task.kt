package com.example.app.todo.model

import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://jsonplaceholder.typicode.com/"

data class Success(val tasks: List<Task>)
data class Task(
    // Example of how to rename a field
    // @SerializedName("userId") var uID: Int,
    val userId: Int,
    val id: Int,
    val title: String,
    val completed: Boolean
)

interface TaskRepository {
    @GET("todos")
    suspend fun getTasks(): List<Task>

    @GET("todos/{id}")
    suspend fun getTask(id: Int): Task

    // Create an instance of TaskRepository
    companion object {
        private var instance: TaskRepository? = null
        fun getInstance(): TaskRepository {
            if (instance == null) {
                instance = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(TaskRepository::class.java)
            }
            return instance!!
        }
    }
}
