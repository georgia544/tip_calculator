package com.example.tipscalculator

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.widget.SwitchCompat

class HomeActivity : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        sharedPreferences = getSharedPreferences("sharedPrefs_home", MODE_PRIVATE)

        val booleanValueHome = sharedPreferences.getBoolean("switch_home", false)

        val switchHome = findViewById<SwitchCompat>(R.id.switch_theme_home)

        switchHome.isChecked = booleanValueHome

        switchHome.setOnCheckedChangeListener { compoundButton, isChecked ->
            sharedPreferences.edit().putBoolean("switch_home", isChecked).apply()
        }

        val calculatorButton = findViewById<Button>(R.id.calculator)
        calculatorButton.setOnClickListener {
            val calculatorIntent = Intent(this, CalculatorActivity::class.java)
            startActivity(calculatorIntent)
        }

        val tipCalculatorButton = findViewById<Button>(R.id.tip_calculator)
        tipCalculatorButton.setOnClickListener {
            val tipCalculatorIntent = Intent(this, TipCalculatorActivity::class.java)
            startActivity(tipCalculatorIntent)
        }

        val suggestedTipButton = findViewById<Button>(R.id.suggested_tip)
        suggestedTipButton.setOnClickListener {
            val suggestedTipIntent = Intent(this, SuggestedTipActivity::class.java)
            startActivity(suggestedTipIntent)
        }


    }

}