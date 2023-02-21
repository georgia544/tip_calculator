package com.example.tipscalculator

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Switch
import androidx.appcompat.widget.SwitchCompat
import androidx.core.content.edit

class HomeActivity : AppCompatActivity() {

    private val sharedPreferences = getSharedPreferences("sharedPrefs", MODE_PRIVATE)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val booleanValue = sharedPreferences.getBoolean("switch", false)

        val switch = findViewById<SwitchCompat>(R.id.switch_theme_home)

        switch.isChecked = booleanValue

        switch.setOnCheckedChangeListener { compoundButton, isChecked ->
            sharedPreferences.edit().putBoolean("switch", isChecked).apply()
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