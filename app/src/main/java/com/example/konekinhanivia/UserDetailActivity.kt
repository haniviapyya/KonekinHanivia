package com.example.konekinhanivia

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.konekinhanivia.databinding.ActivityUserDetailBinding
import com.example.konekinhanivia.model.UserDetail
import com.example.konekinhanivia.network.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUserDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Ambil ID yang dikirim dari MainActivity
        val userId = intent.getIntExtra("USER_ID", -1)

        if (userId != -1) {
            fetchUserDetail(userId)
        } else {
            Toast.makeText(this, "ID User tidak valid", Toast.LENGTH_SHORT).show()
        }
    }

    private fun fetchUserDetail(id: Int) {
        val client = ApiClient.getInstance()
        client.getUserById(id).enqueue(object : Callback<UserDetail> {
            override fun onResponse(call: Call<UserDetail>, response: Response<UserDetail>) {
                if (response.isSuccessful && response.body() != null) {
                    val user = response.body()!!.data

                    // Tampilkan data ke TextView
                    binding.tvId.text = "ID: ${user.id}"
                    binding.tvName.text = "Name: ${user.employeeName}"
                    binding.tvAge.text = "Age: ${user.employeeAge} years"
                    binding.tvSalary.text = "Salary: $${user.employeeSalary}"
                } else {
                    Toast.makeText(this@UserDetailActivity, "Gagal mengambil detail user", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<UserDetail>, t: Throwable) {
                Toast.makeText(this@UserDetailActivity, "Koneksi Error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}