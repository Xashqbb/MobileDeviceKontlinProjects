package com.example.laba7

import Student
import android.app.Activity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class AddStudentActivity : AppCompatActivity() {

    private lateinit var studentDAO: StudentDAO

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_student)

        studentDAO = StudentDAO(this)

        val firstNameEditText = findViewById<EditText>(R.id.firstNameEditText)
        val lastNameEditText = findViewById<EditText>(R.id.lastNameEditText)
        val emailEditText = findViewById<EditText>(R.id.emailEditText)
        val ratingEditText = findViewById<EditText>(R.id.ratingEditText)
        val performanceEditText = findViewById<EditText>(R.id.performanceEditText)
        val specialtyEditText = findViewById<EditText>(R.id.specialtyEditText)
        val groupEditText = findViewById<EditText>(R.id.groupEditText)
        val saveButton = findViewById<Button>(R.id.saveButton)

        saveButton.setOnClickListener {
            val newStudent = Student(
                id = 0, // id буде автоматично генеруватися в БД
                firstName = firstNameEditText.text.toString(),
                lastName = lastNameEditText.text.toString(),
                email = emailEditText.text.toString(),
                rating = ratingEditText.text.toString().toIntOrNull() ?: 0,
                performance = performanceEditText.text.toString(),
                specialty = specialtyEditText.text.toString(),
                student_group = groupEditText.text.toString()
            )

            studentDAO.insertStudent(newStudent)

            setResult(Activity.RESULT_OK)
            finish()
        }
    }
}
