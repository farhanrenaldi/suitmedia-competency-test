package com.farhan.suitmediacompetencytest.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import com.farhan.suitmediacompetencytest.R
import com.farhan.suitmediacompetencytest.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivitySecondBinding

    companion object {
        const val EXTRA_NAME = "extra_name"
        const val EXTRA_USERNAME = "extra_username"
    }

    private val resultLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == ThirdActivity.RESULT_CODE && result.data != null ) {
            val selectedName = result.data?.getStringExtra(ThirdActivity.EXTRA_SELECTED_VALUE)
            binding.tvSeletedUser.text = selectedName
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setTitle(R.string.second_screen)

        var name = intent.getStringExtra(EXTRA_NAME)

        binding.tvName.text = name

        binding.btnChooseUser.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.btn_choose_user -> {
                val moveForResultIntent = Intent(this@SecondActivity, ThirdActivity::class.java)
                resultLauncher.launch(moveForResultIntent)
            }
        }
    }
}