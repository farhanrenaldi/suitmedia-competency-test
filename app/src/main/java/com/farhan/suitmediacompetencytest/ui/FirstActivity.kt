package com.farhan.suitmediacompetencytest.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.farhan.suitmediacompetencytest.R
import com.farhan.suitmediacompetencytest.databinding.ActivityFirstBinding

class FirstActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityFirstBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFirstBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        binding.btnCheck.setOnClickListener(this)
        binding.btnNext.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        if (v?.id == R.id.btn_check) {
            val palindromeInput = binding.textboxInput.text.toString().trim()

            if (palindromeInput.isEmpty()) {
                toggleVisibility(binding.tvPalindromeError, true)
            } else {
                toggleVisibility(binding.tvPalindromeError,false)
                if (isPalindrome(palindromeInput)) {
                    Toast.makeText(
                        this@FirstActivity,
                        "The string '$palindromeInput' IS palindrome",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    Toast.makeText(
                        this@FirstActivity,
                        "The string '$palindromeInput' IS NOT palindrome",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }

        if (v?.id == R.id.btn_next) {
            val name = binding.textboxName.text.toString()

            if (name.isEmpty()) {
                toggleVisibility(binding.tvNameError, true)
            } else {
                toggleVisibility(binding.tvNameError, false)
                val moveIntent = Intent(this@FirstActivity, SecondActivity::class.java)
                moveIntent.putExtra(SecondActivity.EXTRA_NAME, name)
                startActivity(moveIntent)
            }
        }
    }

    private fun isPalindrome(string: String) : Boolean {
        val original = string.filter { !it.isWhitespace() }
        return original.equals(original.reversed(), ignoreCase = true)
    }

    private fun toggleVisibility(view: View, state: Boolean) {
        when(state) {
            false -> view.visibility = View.INVISIBLE
            true  -> view.visibility = View.VISIBLE
        }
    }
}