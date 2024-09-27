package br.com.devjmcn.httprequestandroid

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import br.com.devjmcn.httprequestandroid.data.Repository
import br.com.devjmcn.httprequestandroid.data.ktor.KtorHttpClient
import br.com.devjmcn.httprequestandroid.data.ktor.KtorRepositoryImpl
import br.com.devjmcn.httprequestandroid.util.Response
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    private val repository by lazy {
        KtorRepositoryImpl(KtorHttpClient)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val cep = "13578000"

        lifecycleScope.launch {
            val result = withContext(Dispatchers.IO){
                repository.getCep(cep)
            }

            when(result){
                is Response.OnSuccess -> {
                    Toast.makeText(this@MainActivity, result.data.cidade, Toast.LENGTH_SHORT).show()
                }
                is Response.OnFailure -> {
                    Toast.makeText(this@MainActivity, result.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}