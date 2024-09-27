package br.com.devjmcn.httprequestandroid

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import br.com.devjmcn.httprequestandroid.data.ktor.KtorHttpClient
import br.com.devjmcn.httprequestandroid.data.ktor.KtorRepositoryImpl
import br.com.devjmcn.httprequestandroid.data.model.CepModel
import br.com.devjmcn.httprequestandroid.data.retrofit.RetrofitHttpClient
import br.com.devjmcn.httprequestandroid.data.retrofit.RetrofitRepositoryImpl
import br.com.devjmcn.httprequestandroid.databinding.ActivityMainBinding
import br.com.devjmcn.httprequestandroid.util.Response
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.supervisorScope
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val repository by lazy {
        //RetrofitRepositoryImpl(RetrofitHttpClient)
        KtorRepositoryImpl(KtorHttpClient)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        initConfig()
    }

    private fun initConfig() {
        with(binding) {
            btnSearch.setOnClickListener {
                clearFields()
                val cep = edtCep.text.toString()
                if (cep.isBlank()) {
                    Toast.makeText(this@MainActivity, "Invalid CEP", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
                searchCity(cep)
            }

        }
    }

    private fun searchCity(cep: String) {
        lifecycleScope.launch {
            val result = withContext(Dispatchers.IO) {
                repository.getCep(cep)
            }

            when (result) {
                is Response.OnSuccess -> populateScreen(result.data)
                is Response.OnFailure -> binding.txvError.text = result.message
            }
        }
    }

    private fun populateScreen(cepModel: CepModel) {
        with(binding) {
            txvCityName.text = cepModel.localidade
            txvLocality.text = cepModel.logradouro
            txvDistrict.text = cepModel.bairro
            txvUf.text = cepModel.uf
        }
    }

    private fun clearFields() {
        with(binding) {
            txvCityName.text = ""
            txvLocality.text = ""
            txvDistrict.text = ""
            txvUf.text = ""
        }
    }
}