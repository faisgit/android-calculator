package com.example.myclac

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var resultTextView: TextView
    private lateinit var previousCalculationTextView: TextView

    private var firstNumber = 0.0
    private var operation = ""
    private var isNewOperation = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Initialize TextViews
        resultTextView = findViewById(R.id.resultTextView)
        previousCalculationTextView = findViewById(R.id.previousCalculationTextView)

        // Initialize Buttons
        val btn0: Button = findViewById(R.id.btn0)
        val btn1: Button = findViewById(R.id.btn1)
        val btn2: Button = findViewById(R.id.btn2)
        val btn3: Button = findViewById(R.id.btn3)
        val btn4: Button = findViewById(R.id.btn4)
        val btn5: Button = findViewById(R.id.btn5)
        val btn6: Button = findViewById(R.id.btn6)
        val btn7: Button = findViewById(R.id.btn7)
        val btn8: Button = findViewById(R.id.btn8)
        val btn9: Button = findViewById(R.id.btn9)

        val add: Button = findViewById(R.id.btnAdd)
        val sub: Button = findViewById(R.id.btndiff)
        val mul: Button = findViewById(R.id.btnProduct)
        val div: Button = findViewById(R.id.btnDivide)

        val equal: Button = findViewById(R.id.btnEqual)
        val clear: Button = findViewById(R.id.btnClear)
        val backSpace: Button = findViewById(R.id.btnBackSpace)

        val dot: Button = findViewById(R.id.btnDot)
        val percent: Button = findViewById(R.id.btnPercentage)

        // Number Buttons
        btn0.setOnClickListener { appendNumber("0") }
        btn1.setOnClickListener { appendNumber("1") }
        btn2.setOnClickListener { appendNumber("2") }
        btn3.setOnClickListener { appendNumber("3") }
        btn4.setOnClickListener { appendNumber("4") }
        btn5.setOnClickListener { appendNumber("5") }
        btn6.setOnClickListener { appendNumber("6") }
        btn7.setOnClickListener { appendNumber("7") }
        btn8.setOnClickListener { appendNumber("8") }
        btn9.setOnClickListener { appendNumber("9") }
        dot.setOnClickListener { appendNumber(".") }

        // Operations
        add.setOnClickListener { setOperation("+") }
        sub.setOnClickListener { setOperation("-") }
        mul.setOnClickListener { setOperation("x") }
        div.setOnClickListener { setOperation("/") }
        percent.setOnClickListener { setOperation("%") }

        // Actions
        equal.setOnClickListener { calculateResult() }
        clear.setOnClickListener { clearCalculator() }
        backSpace.setOnClickListener {
            val currentText = resultTextView.text.toString()
            if (currentText.isNotEmpty()) {
                resultTextView.text = currentText.dropLast(1)
            }
        }
    }

    private fun appendNumber(number: String) {
        if (isNewOperation) {
            resultTextView.text = number
            isNewOperation = false
        } else {
            resultTextView.text = resultTextView.text.toString() + number
        }
    }

    private fun setOperation(op: String) {
        firstNumber = resultTextView.text.toString().toDoubleOrNull() ?: 0.0
        operation = op
        isNewOperation = true
        previousCalculationTextView.text = "$firstNumber $operation"
        resultTextView.text = "0"
    }

    private fun calculateResult() {
        try {
            val secondNumber = resultTextView.text.toString().toDoubleOrNull() ?: 0.0
            val result = when (operation) {
                "+" -> firstNumber + secondNumber
                "-" -> firstNumber - secondNumber
                "x" -> firstNumber * secondNumber
                "/" -> firstNumber / secondNumber
                "%" -> firstNumber % secondNumber
                else -> secondNumber
            }
            resultTextView.text = result.toString()
            previousCalculationTextView.text = "$firstNumber $operation $secondNumber ="
            isNewOperation = true
        } catch (e: Exception) {
            resultTextView.text = "Error"
        }
    }

    private fun clearCalculator() {
        resultTextView.text = "0"
        previousCalculationTextView.text = ""
        firstNumber = 0.0
        operation = ""
        isNewOperation = true
    }
}
