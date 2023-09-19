package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private lateinit var inputView: EditText
    private lateinit var outputView: TextView

    //prevents user from inputting two operands in a row
    private var canAddOp = false
    //prevents user from adding more than one dec to a number
    private var canAddDec = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ////////////////////////////////////////
        inputView = findViewById(R.id.inputView)
        //outputView = findViewById(R.id.outputView)
            // outputView SOMEHOW CRASHES THE ENTIRE APP
        ////////////////////////////////////////
    }

    fun equalsInput(view: View) {}

    //This facilitates the input of numbers
    fun numberInput(view: View) {
        if(view is Button) {
            if (view.text == ".") {
                if (canAddDec)
                    inputView.append(view.text)
                canAddDec = false
            }
            else
                inputView.append(view.text)
            canAddOp = true
        }
    }

    //This facilitates the input of operands
    fun operandInput(view: View) {
        if(view is Button && canAddOp) {
            inputView.append(view.text)
            canAddOp = false
            canAddDec = true
        }
    }
}