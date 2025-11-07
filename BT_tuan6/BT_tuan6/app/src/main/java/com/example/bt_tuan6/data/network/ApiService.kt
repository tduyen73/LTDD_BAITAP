package com.example.bt_tuan6.data.network

import com.example.bt_tuan6.data.model.Task
import retrofit2.Response
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("api/tduyen73/tasks")
    suspend fun getTasks(): Response<List<Task>>

    @GET("api/tduyen73/task/{id}")
    suspend fun getTaskDetail(@Path("id") id: Int): Response<Task>

    @DELETE("api/tduyen73/task/{id}")
    suspend fun deleteTask(@Path("id") id: Int): Response<Unit>
}
