package com.example.colocviu1_2
import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class ComputeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_compute)

        val terms = intent.getIntegerArrayListExtra("terms") ?: emptyList<Int>()
        val sum = terms.sum()

        val resultTextView = findViewById<TextView>(R.id.textView_result)
        resultTextView.text = "Sum: $sum"

        val returnButton = findViewById<TextView>(R.id.button_return)
        returnButton.setOnClickListener {
            val resultIntent = Intent()
            resultIntent.putExtra("sum", sum)
            setResult(Activity.RESULT_OK, resultIntent)
            finish()
        }
    }
}
