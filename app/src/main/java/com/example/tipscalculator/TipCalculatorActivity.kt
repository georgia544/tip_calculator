package com.example.tipscalculator

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class TipCalculatorActivity : AppCompatActivity() {

    private lateinit var billAmount:EditText
    private lateinit var tipPercentage:EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tip_calculator)

        var tipAmountTextView = findViewById<TextView>(R.id.tip_amount_textview)
        var totalAmountOfBillTextView = findViewById<TextView>(R.id.total_amount_of_bill_textview)

        billAmount = findViewById(R.id.bill_amount)
        tipPercentage = findViewById(R.id.percentage)

        val equalsButton = findViewById<Button>(R.id.equals_suggested_tip)

        equalsButton.setOnClickListener {
            tipAmountTextView.text =
                billAmount.text.toString().toDouble().tipAmount(tipPercentage.text.toString().toDouble()).toString()
            totalAmountOfBillTextView.text =totalAmountOfBill(tipAmountTextView.text.toString().toDouble(),
                billAmount.text.toString().toDouble()).toString()
        }
    }


private fun totalAmountOfBill(tips:Double,billAmount:Double):Double{
    val total=tips+billAmount
    return total
}


}
    fun Double.tipAmount(tipPercentage: Double) = (this * tipPercentage) / 100.0
