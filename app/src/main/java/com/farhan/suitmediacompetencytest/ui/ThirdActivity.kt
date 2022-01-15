package com.farhan.suitmediacompetencytest.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.farhan.suitmediacompetencytest.model.ItemModel
import com.farhan.suitmediacompetencytest.adapter.PaginationScrollListener
import com.farhan.suitmediacompetencytest.R
import com.farhan.suitmediacompetencytest.databinding.ActivityThirdBinding
import com.google.gson.Gson
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import cz.msebera.android.httpclient.Header
import org.json.JSONObject
import java.lang.Exception

class ThirdActivity : AppCompatActivity() {


    companion object {
        private val TAG = ThirdActivity::class.java.simpleName
        const val EXTRA_SELECTED_VALUE = "extra_selected_value"
        const val RESULT_CODE = 110
    }

    private lateinit var binding: ActivityThirdBinding
    private var pageNum: Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityThirdBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setTitle(R.string.third_screen)

        val layoutManager = LinearLayoutManager(this)
        binding.recyclerView.layoutManager = layoutManager

        var isLastPage: Boolean = false
        var isLoading: Boolean = false

        binding.recyclerView.addOnScrollListener(object : PaginationScrollListener(layoutManager) {
            override fun isLastPage(): Boolean {
                return isLastPage
            }

            override fun isLoading(): Boolean {
                return isLoading
            }

            override fun loadMoreItems() {
                isLoading = true
                getDataFromApi(1)
            }
        })
    }

    fun getDataFromApi(pageNum: Int) {
        val client = AsyncHttpClient()
        val url = "https://reqres.in/api/users?page=$pageNum"

        client.get(url, object : AsyncHttpResponseHandler() {
            override fun onSuccess(
                statusCode: Int,
                headers: Array<out Header>?,
                responseBody: ByteArray?
            ) {
                val result = String(responseBody!!)
                Log.d(TAG, result)

                try {
                    val response = JSONObject(result)
                    val rawData = response.getJSONArray("data")

                    val gson = Gson()
                    //val listData = gson.fromJson(rawData,Array<ItemModel>)

                }
                catch (e: Exception) {
                    Toast.makeText(this@ThirdActivity, e.message, Toast.LENGTH_SHORT).show()
                    e.printStackTrace()
                }
            }

            override fun onFailure(
                statusCode: Int,
                headers: Array<out Header>?,
                responseBody: ByteArray?,
                error: Throwable?
            ) {
                val errorMessage = when (statusCode) {
                    401 -> "$statusCode : Bad Request"
                    403 -> "$statusCode : Forbidden"
                    404 -> "$statusCode : Not Found"
                    else -> "$statusCode : ${error?.message}"
                }
                Toast.makeText(this@ThirdActivity, errorMessage, Toast.LENGTH_LONG).show()
            }
        })
    }
}