package com.example.iecagto

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.iecagto.apis.ApiClient
import com.example.iecagto.datas.LoginRequest
import com.example.iecagto.datas.LoginResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    lateinit var btnLogin : Button
    lateinit var edtEmail: EditText
    lateinit var edtPassword: EditText
    lateinit var nombre: String
    lateinit var token: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnLogin = findViewById(R.id.btnLogin)
        edtEmail = findViewById(R.id.edtEmail)
        edtPassword = findViewById(R.id.edtPassword)

        btnLogin.setOnClickListener {
            val apiService = ApiClient.getClient()

            val body : LoginRequest = LoginRequest(edtEmail.text.toString(),edtPassword.text.toString())

            println(body)
            val call = apiService.login(body)

            call.enqueue(object : Callback<LoginResponse> {
                override fun onResponse(
                    call: Call<LoginResponse>,
                    response: Response<LoginResponse>
                ){
                    if (response.isSuccessful){
                        val loginResponse = response.body()
                        if (loginResponse != null) {
                            nombre = loginResponse.name
                            token = loginResponse.token
                            escribirDatosCache(applicationContext,nombre,token)
                            Toast.makeText(applicationContext, "Caché guardado con éxito!!!", Toast.LENGTH_LONG).show()
                            val intent = Intent(applicationContext, Menu::class.java)
                            startActivity(intent)
                        } else {
                            Toast.makeText(applicationContext, "No hay datos", Toast.LENGTH_LONG).show()
                        }
                        Toast.makeText(applicationContext, "Todo ok", Toast.LENGTH_LONG).show()
                    }else{
                        Toast.makeText(applicationContext, "Error", Toast.LENGTH_LONG).show()
                    }
                }
                override fun onFailure(call: Call<LoginResponse>, t: Throwable){
                    Toast.makeText(applicationContext, "Error de Red", Toast.LENGTH_LONG).show()
                    println(t)
                }
            })
        }
    }

    fun escribirDatosCache(context: Context, nombre: String, token: String){
        val sharedPreferences = context.getSharedPreferences("cache", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString(nombre,token)
        editor.apply()
    }
}