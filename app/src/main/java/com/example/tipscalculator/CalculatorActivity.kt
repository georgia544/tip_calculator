package com.example.tipscalculator

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class CalculatorActivity : AppCompatActivity() {

    private val calculator = Calculator()
    private lateinit var input: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculator)
        input = findViewById(R.id.input_text)

        val result: TextView = findViewById(R.id.result_text)


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
                    result.text = i.toString()
                    shouldClearNumber = false
                } else {

                    result.text = result.text.toString() + i
                }


                input.text = input.text.toString() + i
            }
        }


        buttonDot.setOnClickListener {
            result.text = result.text.toString() + "."
            input.text = input.text.toString() + "."
        }
        buttonDelete.setOnClickListener {
            result.text = removeLastChar(result.text.toString())
            input.text = removeLastChar(input.text.toString())
        }
        buttonDeleteAll.setOnClickListener {
            result.text = ""
            input.text = ""
        }


        buttonPlus.setOnClickListener {
            shouldClearNumber = true
            setCalculatorProperties(CalculatorAction.PLUS)
            input.text = input.text.toString() + "+"
        }

        buttonMinus.setOnClickListener {
            shouldClearNumber = true
            setCalculatorProperties(CalculatorAction.MINUS)
            input.text = input.text.toString() + "-"
        }

        buttonDivide.setOnClickListener {
            shouldClearNumber = true
            setCalculatorProperties(CalculatorAction.DIVIDE)
            input.text = input.text.toString() + "÷"
        }

        buttonMultiple.setOnClickListener {
            shouldClearNumber = true
            setCalculatorProperties(CalculatorAction.MULTIPLY)
            input.text = input.text.toString() + "x"
        }

        buttonEquals.setOnClickListener {
            calculator.calculate(result.text.toSafeDouble())
            result.text = calculator.number.removeZeroAfterDot()
        }


    }



    private fun Double.removeZeroAfterDot():String{
        val newNum = this.toInt()
        if (this-newNum != 0.0){
            return this.toString()
        }else{
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
        calculator.number = input.text.toSafeDouble()


        calculator.action = action
    }

    private fun CharSequence.toSafeDouble(): Double {
        return this.toString().toDouble()
    }

    private fun charSequenceToSafeDouble(charSequence: CharSequence):Double{
        return charSequence.toString().toDouble()
    }

}
