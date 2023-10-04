package com.example.test

class CalculatorModel {
    enum class Operator{
        PLUS,MINUS,TIMES,DIVIDE,NONE
    }
    var operand1:Double = 0.0
    var operand2:Double = 0.0
    var operator:Operator = Operator.NONE

    fun calculation():Double?{
        return when(operator){
            Operator.PLUS -> operand1+operand2
            Operator.MINUS -> operand1-operand2
            Operator.TIMES -> operand1*operand2
            Operator.DIVIDE -> operand1/operand2
            Operator.NONE -> null
        }
    }
    fun resetModel(){
        operand1 = 0.0
        operand2 = 0.0
        operator = Operator.NONE
    }

}