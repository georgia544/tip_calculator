package com.example.tipscalculator

class Calculator {
    var number:Double = 0.0
    var action = CalculatorAction.NONE

    fun calculate(newNumber:Double){
        when(action){
            CalculatorAction.PLUS -> number += newNumber
            CalculatorAction.MINUS -> number -= newNumber
            CalculatorAction.DIVIDE -> number /= newNumber
            CalculatorAction.MULTIPLY -> number*=newNumber
            CalculatorAction.NONE -> {}
        }
        action = CalculatorAction.NONE
    }
}

enum class CalculatorAction{
    PLUS,
    MINUS,
    DIVIDE,
    MULTIPLY,
    NONE
}