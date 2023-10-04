package com.example.test

class CalculatorPresenter:CalculatorContract.Presenter {

    private lateinit var model: CalculatorModel
    private lateinit var view:CalculatorContract.View

    //called in the MainActivity to set the view of the calculator
    override fun setView(newView: CalculatorContract.View) {
        view = newView
    }

    //called in the MainActivity to set the model object
    override fun start() {
        model = CalculatorModel()
    }
}