package com.example.tipscalculator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val calculatorButton = findViewById<Button>(R.id.calculator)
        calculatorButton.setOnClickListener {
            val calculatorIntent = Intent(this,CalculatorActivity::class.java)
            startActivity(calculatorIntent)
        }

        val tipCalculatorButton = findViewById<Button>(R.id.tip_calculator)
        tipCalculatorButton.setOnClickListener {
            val tipCalculatorIntent = Intent(this,TipCalculatorActivity::class.java)
            startActivity(tipCalculatorIntent)
        }

        val suggestedTipButton = findViewById<Button>(R.id.suggested_tip)
        suggestedTipButton.setOnClickListener {
            val suggestedTipIntent = Intent(this,SuggestedTipActivity::class.java)
            startActivity(suggestedTipIntent)
        }

    }
}