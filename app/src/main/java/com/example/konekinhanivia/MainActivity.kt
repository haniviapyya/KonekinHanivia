package com.example.konekinhanivia

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.konekinhanivia.databinding.ActivityMainBinding
import com.example.konekinhanivia.model.Data
import com.example.konekinhanivia.model.Users
import com.example.konekinhanivia.network.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val client = ApiClient.getInstance()
        val response = client.getAllUsers()

        // Simpan list data objek lengkap (bukan cuma String nama)
        val rawUserList = ArrayList<Data>()
        val userNameList = ArrayList<String>()

        response.enqueue(object : Callback<Users> {
            override fun onResponse(call: Call<Users>, response: Response<Users>) {
                if (response.isSuccessful && response.body() != null) {
                    for (i in response.body()!!.data) {
                        rawUserList.add(i)
                        userNameList.add(i.employeeName)
                    }

                    val listAdapter = ArrayAdapter(
                        this@MainActivity,
                        android.R.layout.simple_list_item_1,
                        userNameList
                    )
                    binding.lvNama.adapter = listAdapter

                    // Tambahkan Event Klik pada ListView
                    binding.lvNama.setOnItemClickListener { _, _, position, _ ->
                        val selectedUser = rawUserList[position]

                        // Berpindah ke UserDetailActivity sambil membawa ID
                        val intent = Intent(this@MainActivity, UserDetailActivity::class.java)
                        intent.putExtra("USER_ID", selectedUser.id)
                        startActivity(intent)
                    }
                }
            }

            override fun onFailure(call: Call<Users>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Koneksi error", Toast.LENGTH_LONG).show()
            }
        })
    }
}