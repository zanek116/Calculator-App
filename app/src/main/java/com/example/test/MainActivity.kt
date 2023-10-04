package com.example.test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity(),CalculatorContract.View,View.OnClickListener{


    //variables used throughout the MainActivity initialized here
    private lateinit var presenter: CalculatorPresenter
    private var newInput:Boolean = true
    private var oldNum:Double = 0.0
    private var operator:String = ""
    private var opNum = 0

    //starting point for the application
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //sets the calculator view
        presenter=CalculatorPresenter()
        presenter.setView(this)
        presenter.start()

        //initialize all buttons on the calculator and set a listener
        findViewById<Button>(R.id.buttonPlus).setOnClickListener(this)
        findViewById<Button>(R.id.buttonMinus).setOnClickListener(this)
        findViewById<Button>(R.id.buttonDivide).setOnClickListener(this)
        findViewById<Button>(R.id.buttonMultiply).setOnClickListener(this)
        findViewById<Button>(R.id.buttonBackspace).setOnClickListener(this)
        findViewById<Button>(R.id.buttonModulo).setOnClickListener(this)
        findViewById<Button>(R.id.buttonC).setOnClickListener(this)
        findViewById<Button>(R.id.buttonDecimal).setOnClickListener(this)
        findViewById<Button>(R.id.buttonEquals).setOnClickListener(this)
        findViewById<Button>(R.id.buttonSign).setOnClickListener(this)
        findViewById<Button>(R.id.button0).setOnClickListener(this)
        findViewById<Button>(R.id.button1).setOnClickListener(this)
        findViewById<Button>(R.id.button2).setOnClickListener(this)
        findViewById<Button>(R.id.button3).setOnClickListener(this)
        findViewById<Button>(R.id.button4).setOnClickListener(this)
        findViewById<Button>(R.id.button5).setOnClickListener(this)
        findViewById<Button>(R.id.button6).setOnClickListener(this)
        findViewById<Button>(R.id.button7).setOnClickListener(this)
        findViewById<Button>(R.id.button8).setOnClickListener(this)
        findViewById<Button>(R.id.button9).setOnClickListener(this)
    }

    //runs this function when any button is clicked
    override fun onClick(v: View) {

        //checks if input is a new number or same operation
        //clears the text view if a new number is starting
        if (newInput && v.id!=R.id.buttonSign && v.id!=R.id.buttonMultiply && v.id!=R.id.buttonDivide && v.id!=R.id.buttonMinus && v.id!=R.id.buttonPlus && v.id!=R.id.buttonDecimal)
        {
            val tv:TextView = findViewById(R.id.textView)
            tv.text = ""
        }
        newInput = false
        when (v.id)
        {
            //all button clicks handled
            R.id.button0 -> numberClick("0")
            R.id.button1 -> numberClick("1")
            R.id.button2 -> numberClick("2")
            R.id.button3 -> numberClick("3")
            R.id.button4 -> numberClick("4")
            R.id.button5 -> numberClick("5")
            R.id.button6 -> numberClick("6")
            R.id.button7 -> numberClick("7")
            R.id.button8 -> numberClick("8")
            R.id.button9 -> numberClick("9")
            R.id.buttonDecimal -> numberClick(".")
            R.id.buttonC -> clearClick()
            R.id.buttonBackspace -> backspace()
            R.id.buttonPlus -> operatorClick("+")
            R.id.buttonMinus -> operatorClick("-")
            R.id.buttonDivide -> operatorClick("/")
            R.id.buttonMultiply -> operatorClick("*")
            R.id.buttonEquals -> equalsClick()
            R.id.buttonSign -> signChange()
        }
    }
    //handles all numberClicks
    private fun numberClick(num: String){
        var textOld:String
        val textNew:TextView = findViewById(R.id.textView)
        textOld = textNew.text.toString()
        var finalAdd:String = num

        //handles a decimal point error because of empty text view
        if (num==".")
        {
            if (textOld.contains("."))
            {
                finalAdd = ""
            }
            else { }
        }
        //handles decimals when 0 is the only thing in text view
        if (textOld.toString() == "0")
        {
            if (num==".")
            {
                textOld = "0"
            }
            else
            {
                textOld = ""
            }
        }
        //handles negative sign switch when 0 is the only thing in text view
        else if (textOld.toString() == "-0")
        {
            textOld = "-"
        }
        //adds the number typed to the text view
        textNew.text = textOld + finalAdd
    }
    //handles the negative/positive button on calculator
    private fun signChange()
    {
        var textOld:String
        var temp:String
        val textNew:TextView = findViewById(R.id.textView)
        textOld = textNew.text.toString()
        temp = textOld.substring(0,1)
        //handles if number in text view is 0
        if (temp == "")
        {
            textNew.text = "-0"
        }
        //handles if number is already negative
        else if (temp == "-")
        {
            temp = textOld.substring(1,textOld.length)
            textNew.text = temp
        }
        else
        {
            temp = "-"
            textNew.text = temp + textOld
        }
    }
    //handles all clicks from operators
    private fun operatorClick(op: String)
    {
        val tv:TextView = findViewById(R.id.textView)
        val temp = tv.text
        //handles operation click if no number has been typed yet
        if (temp=="")
        { }
        else
        {
            opNum++
            Log.d("opNum",opNum.toString())

            var tempNum:String = ""
            newInput = true
            Log.d("oldNum", oldNum.toString())

            //on first operation typed, calculate
            if (opNum==1)
            {
                val tv:TextView = findViewById(R.id.textView)
                tempNum = tv.text.toString()
                oldNum = tempNum.toDouble()
                when (op)
                {
                    "+" -> operator = "+"
                    "-" -> operator = "-"
                    "*" -> operator = "*"
                    "/" -> operator = "/"
                }
            }
            //handles all operations typed
            else
            {
                equalsClick()
                when(op)
                {
                    "+" -> operator = "+"
                    "-" -> operator = "-"
                    "*" -> operator = "*"
                    "/" -> operator = "/"
                }
            }
        }
    }
    //handles clear click button
    private fun clearClick()
    {
        //sets text view to "0", sets all operator and number values to 0
        val textResult:TextView = findViewById(R.id.textView)
        textResult.text="0"
        newInput = true
        opNum=0
        Log.d("opNum",opNum.toString())
        oldNum=0.0
        Log.d("oldNum", oldNum.toString())

    }
    //backspaces by taking a substring with the last digit taken off
    private fun backspace()
    {
        var textOld:String
        val textNew:TextView = findViewById(R.id.textView)
        textOld = textNew.text.toString()
        var textSubstring:String = textOld.substring(0,textOld.length-1)
        textNew.text = textSubstring
    }
    //handles the equals button
    private fun equalsClick()
    {
        Log.d("operator",operator)
        val tv:TextView = findViewById(R.id.textView)
        val newNum = tv.text.toString()
        var result = 0.0
        //takes in operator and calculates values to result
        when(operator)
        {
            "+" -> result = oldNum + newNum.toDouble()
            "-" -> result = oldNum - newNum.toDouble()
            "*" -> result = oldNum * newNum.toDouble()
            "/" -> result = oldNum / newNum.toDouble()
        }
        var resultString = result.toString()

        //if either have decimal show double, if neither do, check result>1 .
        Log.d("1",oldNum.toString())
        Log.d("2",newNum)
        Log.d("3",resultString)
        //if answer is a double and shouldn't be, converts to int
        if (((oldNum.toString().endsWith(".0")) && !(newNum.contains("."))) && resultString.endsWith(".0"))
        {
            val intResult = result.toInt()
            tv.text= intResult.toString()
            oldNum=result
            Log.d("haha","haha")
        }
        //prints answer if it should be a double
        else
        {
            Log.d("here","here")
            tv.text=result.toString()
            oldNum=result
        }
    }
}