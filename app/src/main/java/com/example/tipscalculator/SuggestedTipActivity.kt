package com.example.tipscalculator

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SuggestedTipActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_suggested_tip)

        val buttonBill = findViewById<EditText>(R.id.bill)
        val buttonEquals = findViewById<Button>(R.id.equals_suggested_tip)

        val tip10percent = findViewById<TextView>(R.id.tip_10_percent_textview)
        val tip15percent = findViewById<TextView>(R.id.tip_15_percent_textview)
        val tip18percent = findViewById<TextView>(R.id.tip_18_percent_textview)
        val tip20percent = findViewById<TextView>(R.id.tip_20_percent_textview)

        buttonEquals.setOnClickListener {
            tip10percent.text = buttonBill.text.toString().toDouble().tipAmount(10.0).toString()
            tip15percent.text = buttonBill.text.toString().toDouble().tipAmount(15.0).toString()
            tip18percent.text = buttonBill.text.toString().toDouble().tipAmount(18.0).toString()
            tip20percent.text = buttonBill.text.toString().toDouble().tipAmount(20.0).toString()
        }


    }

 //   fun Double.tipAmount(tipPercentage: Double) = (this * tipPercentage) / 100.0
}