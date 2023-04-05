package com.example.colocviu1_2
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private lateinit var nextTermEditText: EditText
    private lateinit var addTermButton: Button
    private lateinit var allTermsTextView: TextView
    private lateinit var computeButton: Button

    private var terms = mutableListOf<Int>()
    private var lastComputedTerms = mutableListOf<Int>()
    private var lastComputedSum = 0

    private val REQUEST_CODE_COMPUTE_ACTIVITY = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        nextTermEditText = findViewById(R.id.editText_nextTerm)
        addTermButton = findViewById(R.id.button_add)
        allTermsTextView = findViewById(R.id.textView_allTerms)
        computeButton = findViewById(R.id.button_compute)

        addTermButton.setOnClickListener {
            val term = nextTermEditText.text.toString().toIntOrNull()

            if (term != null) {
                terms.add(term)
                allTermsTextView.text = terms.joinToString(" + ")
                nextTermEditText.text.clear()
            }
        }

        computeButton.setOnClickListener {
            if (terms == lastComputedTerms) {
                Toast.makeText(this, "Sum: $lastComputedSum", Toast.LENGTH_SHORT).show()
            } else {
                val intent = Intent(this, ComputeActivity::class.java)
                intent.putIntegerArrayListExtra("terms", ArrayList(terms))
                startActivityForResult(intent, REQUEST_CODE_COMPUTE_ACTIVITY)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_CODE_COMPUTE_ACTIVITY && resultCode == RESULT_OK) {
            lastComputedTerms = ArrayList(terms)
            lastComputedSum = data?.getIntExtra("sum", 0) ?: 0
            Toast.makeText(this, "Sum: $lastComputedSum", Toast.LENGTH_SHORT).show()
        }
    }
}




//echo "# Colocviu1_2" >> README.md
//git init
//git add README.md
//git commit -m "first commit"
//git branch -M main
//git remote add origin git@github.com:dogukanolgun/Colocviu1_2.git
//git push -u origin main