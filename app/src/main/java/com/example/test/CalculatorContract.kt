package com.example.test

interface CalculatorContract {
    interface View{
    }

    interface Presenter{
        fun setView(view:View)
        fun start()
    }

}
