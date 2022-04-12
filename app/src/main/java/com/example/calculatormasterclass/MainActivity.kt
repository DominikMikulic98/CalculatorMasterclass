package com.example.calculatormasterclass

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import org.w3c.dom.Text


class MainActivity : AppCompatActivity() {
    private lateinit var result: EditText
    private lateinit var newNumber: EditText
    private val displayOrientation: TextView by lazy(LazyThreadSafetyMode.NONE) { findViewById(R.id.operation) }

    private var lastNummeric: Boolean = true
    private var pressedOperator: String? = null
    private var operation: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        result = findViewById(R.id.result)
        newNumber = findViewById(R.id.newNumber)
        operation = findViewById(R.id.operation)

        val button0: Button = findViewById(R.id.button0)
        val button1: Button = findViewById(R.id.button1)
        val button2: Button = findViewById(R.id.button2)
        val button3: Button = findViewById(R.id.button3)
        val button4: Button = findViewById(R.id.button4)
        val button5: Button = findViewById(R.id.button5)
        val button6: Button = findViewById(R.id.button6)
        val button7: Button = findViewById(R.id.button7)
        val button8: Button = findViewById(R.id.button8)
        val button9: Button = findViewById(R.id.button9)
        val buttonDot: Button = findViewById(R.id.buttonDot)

        //Operation buttons
        val buttonEquals: Button = findViewById(R.id.buttonEquals)
        val buttonDivide: Button = findViewById(R.id.buttonDivide)
        val buttonMultiply: Button = findViewById(R.id.buttonMultiply)
        val buttonPlus: Button = findViewById(R.id.buttonPlus)
        val buttonMinus: Button = findViewById(R.id.buttonMinus)

        val listenerDigit = View.OnClickListener {
            newNumber.append((it as Button).text)
            lastNummeric = true
        }
        val listenerDot = View.OnClickListener {
            newNumber.append((it as Button).text)
            lastNummeric = false
        }
        button0.setOnClickListener(listenerDigit)
        button1.setOnClickListener(listenerDigit)
        button2.setOnClickListener(listenerDigit)
        button3.setOnClickListener(listenerDigit)
        button4.setOnClickListener(listenerDigit)
        button5.setOnClickListener(listenerDigit)
        button6.setOnClickListener(listenerDigit)
        button7.setOnClickListener(listenerDigit)
        button8.setOnClickListener(listenerDigit)
        button9.setOnClickListener(listenerDigit)
        buttonDot.setOnClickListener(listenerDot)

        val equalListener = View.OnClickListener {
            if (lastNummeric && newNumber.text.isNotEmpty()) {
                performOperation()
            }
        }

        val operatorsListener = View.OnClickListener {
            if (result.text.isEmpty())
                result.setText(newNumber.text.toString())
            pressedOperator = (it as Button).text.toString()
            operation?.setText((it as Button).text.toString())
            lastNummeric = false
            newNumber.text.clear()
        }

        buttonDivide.setOnClickListener(operatorsListener)
        buttonMultiply.setOnClickListener(operatorsListener)
        buttonPlus.setOnClickListener(operatorsListener)
        buttonMinus.setOnClickListener(operatorsListener)
        buttonEquals.setOnClickListener(equalListener)

    }

    private fun performOperation() {
        var finalResult: Double = 0.0
        if (result.text.isEmpty())
            finalResult = newNumber.text.toString().toDouble()
        else {
            when (pressedOperator) {
                "*" -> finalResult =
                    result.text.toString().toDouble() * newNumber.text.toString().toDouble()
                "/" -> finalResult =
                    result.text.toString().toDouble() / newNumber.text.toString().toDouble()
                "+" -> finalResult =
                    result.text.toString().toDouble() + newNumber.text.toString().toDouble()
                "-" -> finalResult =
                    result.text.toString().toDouble() - newNumber.text.toString().toDouble()
            }
        }
        result.setText(finalResult.toString())
        newNumber.text.clear()
        lastNummeric = false

    }


}