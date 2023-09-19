package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {


    private lateinit var input: EditText
    private lateinit var one: Button
    private lateinit var two: Button
    private lateinit var three: Button
    private lateinit var four: Button
    private lateinit var five: Button
    private lateinit var six: Button
    private lateinit var seven: Button
    private lateinit var eight: Button
    private lateinit var nine: Button
    private lateinit var zero: Button
    private lateinit var decimal: Button
    private lateinit var sqrt: Button
    private lateinit var add: Button
    private lateinit var subtract: Button
    private lateinit var multiply: Button
    private lateinit var divide: Button
    private lateinit var modulus: Button
    private lateinit var equals: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        input = findViewById(R.id.input)
        one = findViewById(R.id.one)
        two = findViewById(R.id.two)
        three = findViewById(R.id.three)
        four = findViewById(R.id.four)
        five = findViewById(R.id.five)
        six = findViewById(R.id.six)
        seven = findViewById(R.id.seven)
        eight = findViewById(R.id.eight)
        nine = findViewById(R.id.nine)
        zero = findViewById(R.id.zero)
        decimal = findViewById(R.id.decimal)
        modulus = findViewById(R.id.modulus)
        add = findViewById(R.id.add)
        subtract = findViewById(R.id.subtract)
        sqrt = findViewById(R.id.sqrt)
        multiply = findViewById(R.id.multiply)
        divide = findViewById(R.id.division)
        equals = findViewById(R.id.equals)


    }
}