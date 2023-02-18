package com.example.tipscalculator

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class CalculatorActivity : AppCompatActivity() {

    private val calculator = Calculator()
    private lateinit var allCalculationsTextView: TextView
    private  lateinit var  currentValueTextView: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculator)
        allCalculationsTextView = findViewById(R.id.all_calculations_text)

       currentValueTextView = findViewById(R.id.current_value_text)


        val button0 = findViewById<Button>(R.id.button0)
        val button1 = findViewById<Button>(R.id.button1)
        val button2 = findViewById<Button>(R.id.button2)
        val button3 = findViewById<Button>(R.id.button3)


        val button4 = findViewById<Button>(R.id.button4)
        val button5 = findViewById<Button>(R.id.button5)
        val button6 = findViewById<Button>(R.id.button6)

        val button7 = findViewById<Button>(R.id.button7)
        val button8 = findViewById<Button>(R.id.button8)
        val button9 = findViewById<Button>(R.id.button9)

        val buttonDelete = findViewById<Button>(R.id.delete)
        val buttonDeleteAll = findViewById<Button>(R.id.ac)
        val buttonDot = findViewById<Button>(R.id.dot)

        val buttonPlus = findViewById<Button>(R.id.plus)
        val buttonMinus = findViewById<Button>(R.id.minus)
        val buttonDivide = findViewById<Button>(R.id.divide)
        val buttonMultiple = findViewById<Button>(R.id.multiply)

        val buttonEquals = findViewById<Button>(R.id.equals)

        val buttons = listOf<Button>(
            button0, button1, button2,
            button3, button4, button5,
            button6, button7, button8, button9
        )

        var shouldClearNumber: Boolean = false

        for (i in buttons.indices) {
            buttons[i].setOnClickListener {
                if (shouldClearNumber) {
                    currentValueTextView.text = i.toString()
                    shouldClearNumber = false
                } else {

                    currentValueTextView.text = currentValueTextView.text.toString() + i
                }


                allCalculationsTextView.text = allCalculationsTextView.text.toString() + i
            }
        }


        buttonDot.setOnClickListener {
            currentValueTextView.text = currentValueTextView.text.toString() + "."
            allCalculationsTextView.text = allCalculationsTextView.text.toString() + "."
        }
        buttonDelete.setOnClickListener {
            currentValueTextView.text = removeLastChar(currentValueTextView.text.toString())
            allCalculationsTextView.text = removeLastChar(allCalculationsTextView.text.toString())
        }
        buttonDeleteAll.setOnClickListener {
            currentValueTextView.text = ""
            allCalculationsTextView.text = ""
        }


        buttonPlus.setOnClickListener {
            shouldClearNumber = true
            setCalculatorProperties(CalculatorAction.PLUS)
            allCalculationsTextView.text = allCalculationsTextView.text.toString() + "+"
        }

        buttonMinus.setOnClickListener {
            shouldClearNumber = true
            setCalculatorProperties(CalculatorAction.MINUS)
            allCalculationsTextView.text = allCalculationsTextView.text.toString() + "-"
        }

        buttonDivide.setOnClickListener {
            shouldClearNumber = true
            setCalculatorProperties(CalculatorAction.DIVIDE)
            allCalculationsTextView.text = allCalculationsTextView.text.toString() + "÷"
        }

        buttonMultiple.setOnClickListener {
            ifNoneInResult()
            shouldClearNumber = true
            setCalculatorProperties(CalculatorAction.MULTIPLY)
            allCalculationsTextView.text = allCalculationsTextView.text.toString() + "x"
        }

        buttonEquals.setOnClickListener {
            calculator.calculate(currentValueTextView.text.toSafeDouble())
            currentValueTextView.text = calculator.number.removeZeroAfterDot()
        }

    }

    private fun ifNoneInResult(){
        if(currentValueTextView.text==""){
            currentValueTextView.text="0"
            allCalculationsTextView.text ="0"
        }
    }

    private fun Double.removeZeroAfterDot(): String {
        val newNum = this.toInt()
        if (this - newNum != 0.0) {
            return this.toString()
        } else {
            return newNum.toString()
        }
    }

    private fun removeLastChar(text: String): String {
        return if (text.isNotEmpty())
            text.substring(0, text.length - 1)
        else
            text
    }


    private fun setCalculatorProperties(action: CalculatorAction) {
        calculator.number = currentValueTextView.text.toSafeDouble()

        calculator.action = action
    }

    private fun CharSequence.toSafeDouble(): Double {
        return this.toString().toDouble()
    }

    private fun charSequenceToSafeDouble(charSequence: CharSequence): Double {
        return charSequence.toString().toDouble()
    }

}
