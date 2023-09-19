package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

/* Sources
    https://developer.android.com/develop/ui/views/theming/themes
    https://stackoverflow.com/questions/34904895/how-to-make-program-to-calculate-accordingly-to-order-of-operations-in-math-ja
    https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-list/
    https://kotlinlang.org/docs/list-operations.html#binary-search-in-sorted-lists
*/


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
        outputView = findViewById(R.id.outputView)
            // outputView SOMEHOW CRASHES THE ENTIRE APP
                //UPDATE, figured it out!!!!!!!!!
                //I tried to test run the app before calling
                //outputView in any of the funs, which
                //crashed the app (idk why that works that way)
        ////////////////////////////////////////
    }

    fun equalsInput(view: View) {
        outputView.text = calculate()
    }


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

    //Helper for equals, applies the operand helpers in order of operations
    private fun calculate(): String {

        val digitsOp = digitsOp()
        if (digitsOp.isEmpty())
            return ""

        //multiplication and division first (PEMDAS)
        val multDiv = multDivCalculate(digitsOp)
        if (multDiv.isEmpty())
            return ""

        //then add/subtract
        val answer = addSub(multDiv)
        return answer.toString()

    }

    private fun addSub(passedList: MutableList<Any>): Float {
        var answer = passedList[0] as Float

        for(i in passedList.indices) {
            if(passedList[i] is Char && i != passedList.lastIndex) {
                val op = passedList[i]
                val nextDigit = passedList[i + 1] as Float
                if (op == '+')
                    answer += nextDigit
                if (op == '-')
                    answer -= nextDigit
            }
        }
        return answer
    }


    // iterates through the passed list and calls mult and div while those
    // operands are in the list first
    private fun multDivCalculate(passedList: MutableList<Any>): MutableList<Any> {
        var list = passedList
        while (list.contains('*') || list.contains('/')) {
            list = timesDiv(list)
        }
        return list
    }

    private fun timesDiv(passedList: MutableList<Any>): MutableList<Any> {
        val currentList = mutableListOf<Any>()
        var refreshIndex = passedList.size

        for(i in passedList.indices){
            if(passedList[i] is Char && i != passedList.lastIndex && i < refreshIndex) {
                val op = passedList[i]
                val prevDigit = passedList[i-1] as Float
                val nextDigit = passedList[i+1] as Float

                when(op) {
                    '*' -> {
                        currentList.add(prevDigit * nextDigit)
                        refreshIndex = i +1
                    }
                    '/' -> {
                        currentList.add(prevDigit / nextDigit)
                        refreshIndex = i +1
                    }
                }
            }

            if(i > refreshIndex)
                currentList.add(passedList[i])
        }

        return currentList
    }


    //converts the text in inputView to float
    private fun digitsOp(): MutableList<Any> {
        val list = mutableListOf<Any>()
        var currentDigit = ""
        for(char in inputView.text) {
            if(char.isDigit() || char == '.')
                currentDigit += char
            else {
                list.add(currentDigit.toFloat())
                currentDigit = ""
                list.add(char)
            }
        }
        if(currentDigit != "")
            list.add(currentDigit.toFloat())
        return list
    }
}