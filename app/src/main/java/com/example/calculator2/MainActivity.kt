package com.example.calculator

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.calculator2.R
import com.google.android.material.card.MaterialCardView

class MainActivity : AppCompatActivity() {

    private lateinit var textView1: TextView
    private lateinit var textView2: TextView

    private var input = ""
    private var operator = ""
    private var firstNumber = 0.0
    private var secondNumber = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize TextViews
        textView1 = findViewById(R.id.textView1)
        textView2 = findViewById(R.id.textView2)

        // Initialize buttons
        val btnAC: MaterialCardView = findViewById(R.id.btn_ac)
        val btnPercent: MaterialCardView = findViewById(R.id.btn_percent)
        val btnDivide: MaterialCardView = findViewById(R.id.btn_divide)
        val btnMultiply: MaterialCardView = findViewById(R.id.btn_multiply)
        val btnPlus: MaterialCardView = findViewById(R.id.btn_plus)
        val btnMinus: MaterialCardView = findViewById(R.id.btn_minus)

        val btn1: MaterialCardView = findViewById(R.id.btn_7)
        val btn2: MaterialCardView = findViewById(R.id.btn_8)
        val btn3: MaterialCardView = findViewById(R.id.btn_9)
        val btn4: MaterialCardView = findViewById(R.id.btn_4)
        val btn5: MaterialCardView = findViewById(R.id.btn_5)
        val btn6: MaterialCardView = findViewById(R.id.btn_6)
        val btn7: MaterialCardView = findViewById(R.id.btn_1)
        val btn8: MaterialCardView = findViewById(R.id.btn_2)
        val btn9: MaterialCardView = findViewById(R.id.btn_3)

        // Clear button
        btnAC.setOnClickListener {
            input = ""
            operator = ""
            firstNumber = 0.0
            secondNumber = 0.0
            textView1.text = "0"
            textView2.text = ""
        }

        // Number buttons
        btn7.setOnClickListener { appendNumber("1") }
        btn8.setOnClickListener { appendNumber("2") }
        btn9.setOnClickListener { appendNumber("3") }
        btn4.setOnClickListener { appendNumber("4") }
        btn5.setOnClickListener { appendNumber("5") }
        btn6.setOnClickListener { appendNumber("6") }
        btn1.setOnClickListener { appendNumber("7") }
        btn2.setOnClickListener { appendNumber("8") }
        btn3.setOnClickListener { appendNumber("9") }

        // Operator buttons
        btnPlus.setOnClickListener { selectOperator("+") }
        btnMinus.setOnClickListener { selectOperator("-") }
        btnMultiply.setOnClickListener { selectOperator("*") }
        btnDivide.setOnClickListener { selectOperator("/") }
        btnPercent.setOnClickListener { calculatePercentage() }

        // "=" Button (execute calculation)
        findViewById<MaterialCardView>(R.id.btn_equal).setOnClickListener {
            calculateResult()
        }
    }

    private fun appendNumber(number: String) {
        input += number
        textView1.text = input
    }

    private fun selectOperator(op: String) {
        if (input.isNotEmpty()) {
            firstNumber = input.toDouble()
            operator = op
            input = ""
            textView2.text = "$firstNumber $operator"
            textView1.text = ""
        }
    }

    private fun calculatePercentage() {
        if (input.isNotEmpty()) {
            val percentage = input.toDouble() / 100
            textView1.text = percentage.toString()
            input = percentage.toString()
        }
    }

    private fun calculateResult() {
        if (input.isNotEmpty() && operator.isNotEmpty()) {
            secondNumber = input.toDouble()
            val result = when (operator) {
                "+" -> firstNumber + secondNumber
                "-" -> firstNumber - secondNumber
                "*" -> firstNumber * secondNumber
                "/" -> firstNumber / secondNumber
                else -> 0.0
            }
            textView1.text = result.toString()
            textView2.text = "$firstNumber $operator $secondNumber"
            input = result.toString()
        }
    }
}
