package com.example.tipscalculator

import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SwitchCompat

private const val ACTION_SYMBOL_PLUS = "+"
private const val ACTION_SYMBOL_MINUS = "-"
private const val ACTION_SYMBOL_DIVIDE = "÷"
private const val ACTION_SYMBOL_MULTIPLY = "x"

class CalculatorActivity : AppCompatActivity() {
    private lateinit var sharedPreferences: SharedPreferences

    private val calculator = Calculator()
    private lateinit var allCalculationsTextView: TextView
    private lateinit var currentValueTextView: TextView

    private var shouldClearNumberInCurrentValue: Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        sharedPreferences = getSharedPreferences("sharedPrefs_calc", MODE_PRIVATE)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculator)

        val booleanValueCalc = sharedPreferences.getBoolean("switch_calc", false)

        val switchCalc = findViewById<SwitchCompat>(R.id.switch_theme_calculator)

        switchCalc.isChecked = booleanValueCalc

        switchCalc.setOnCheckedChangeListener { compoundButton, isChecked ->
            sharedPreferences.edit().putBoolean("switch_calc", isChecked).apply()
        }

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
            button1, button2,
            button3, button4, button5,
            button6, button7, button8, button9
        )

        var shouldClearNumberInAllCalculations: Boolean = false


        for (i in buttons.indices) {
            buttons[i].setOnClickListener {
                if (shouldClearNumberInCurrentValue) {
                    currentValueTextView.text = (i + 1).toString()
                    shouldClearNumberInCurrentValue = false
                } else {

                    currentValueTextView.text = currentValueTextView.text.toString() + (i + 1)
                }

                if (shouldClearNumberInAllCalculations) {
                    allCalculationsTextView.text = (i + 1).toString()
                    shouldClearNumberInAllCalculations = false
                } else {
                    allCalculationsTextView.text = allCalculationsTextView.text.toString() + (i + 1)
                }
            }
        }

        button0.setOnClickListener {
            if (currentValueTextView.text == "0") {
                return@setOnClickListener
            }

            if (shouldClearNumberInCurrentValue) {
                currentValueTextView.text = "0"
                shouldClearNumberInCurrentValue = false
            } else {

                currentValueTextView.text = currentValueTextView.text.toString() + "0"
            }


            allCalculationsTextView.text = allCalculationsTextView.text.toString() + "0"
        }


        buttonDot.setOnClickListener {
            currentValueTextView.text = currentValueTextView.text.toString() + "."
            allCalculationsTextView.text = allCalculationsTextView.text.toString() + "."
        }
        buttonDelete.setOnClickListener {
            currentValueTextView.text = currentValueTextView.text.toString().removeLastChar()
            allCalculationsTextView.text = allCalculationsTextView.text.toString().removeLastChar()
        }
        buttonDeleteAll.setOnClickListener {
            currentValueTextView.text = ""
            allCalculationsTextView.text = ""
        }


        buttonPlus.setOnClickListener {
            handleCalculatorAction(CalculatorAction.PLUS, ACTION_SYMBOL_PLUS)
        }
        buttonMinus.setOnClickListener {
            handleCalculatorAction(CalculatorAction.MINUS, ACTION_SYMBOL_MINUS)
        }
        buttonDivide.setOnClickListener {
            handleCalculatorAction(CalculatorAction.DIVIDE, ACTION_SYMBOL_DIVIDE)
        }
        buttonMultiple.setOnClickListener {
            ifNoneInResult()
            handleCalculatorAction(CalculatorAction.MULTIPLY, ACTION_SYMBOL_MULTIPLY)
        }

        buttonEquals.setOnClickListener {
            shouldClearNumberInAllCalculations = true
            shouldClearNumberInCurrentValue = true
            calculateResult()
        }


    }

    private fun calculateResult() {
        calculator.calculate(currentValueTextView.text.toSafeDouble())
        currentValueTextView.text = calculator.number.removeZeroAfterDot()
    }

    private fun ifNoneInResult() {
        if (currentValueTextView.text == "") {
            currentValueTextView.text = "0"
            allCalculationsTextView.text = "0"
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

    private fun String.removeLastChar(): String {
        return if (this.isNotEmpty())
            this.substring(0, this.length - 1)
        else
            this
    }


    private fun handleCalculatorAction(action: CalculatorAction, actionSymbol: String) {
        if (allCalculationsTextView.text.toString().last().toString() in listOf(
                ACTION_SYMBOL_PLUS,
                ACTION_SYMBOL_MINUS, ACTION_SYMBOL_DIVIDE, ACTION_SYMBOL_MULTIPLY
            )
        ) {
            allCalculationsTextView.text = allCalculationsTextView.text.toString().removeLastChar()
        }
        shouldClearNumberInCurrentValue = true

        if (calculator.action != CalculatorAction.NONE) {
            calculateResult()
        }

        calculator.number = currentValueTextView.text.toSafeDouble()

        calculator.action = action

        allCalculationsTextView.text = allCalculationsTextView.text.toString() + actionSymbol
    }

    private fun CharSequence.toSafeDouble(): Double {
        return this.toString().toDouble()
    }

}
