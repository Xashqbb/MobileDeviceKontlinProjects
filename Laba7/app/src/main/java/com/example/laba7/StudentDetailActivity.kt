package com.example.laba7

import DatabaseHelper
import Student
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView

class StudentDetailsActivity : AppCompatActivity() {

    private lateinit var studentDAO: StudentDAO

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_details)

        studentDAO = StudentDAO(this)
        val studentId = intent.getIntExtra("STUDENT_ID", -1)
        val student = studentDAO.getStudentById(studentId)

        val firstNameEditText = findViewById<EditText>(R.id.firstNameEditText)
        val lastNameEditText = findViewById<EditText>(R.id.lastNameEditText)
        val emailEditText = findViewById<EditText>(R.id.emailEditText)
        val ratingEditText = findViewById<EditText>(R.id.ratingEditText)
        val performanceEditText = findViewById<EditText>(R.id.performanceEditText)
        val specialtyEditText = findViewById<EditText>(R.id.specialtyEditText)
        val groupEditText = findViewById<EditText>(R.id.groupEditText)
        val saveButton = findViewById<Button>(R.id.saveButton)

        if (student != null) {
            firstNameEditText.setText(student.firstName)
            lastNameEditText.setText(student.lastName)
            emailEditText.setText(student.email)
            ratingEditText.setText(student.rating.toString())
            performanceEditText.setText(student.performance)
            specialtyEditText.setText(student.specialty)
            groupEditText.setText(student.student_group)
        } else {
            firstNameEditText.setText("Student not found")
        }

        saveButton.setOnClickListener {
            val updatedFirstName = firstNameEditText.text.toString()
            val updatedLastName = lastNameEditText.text.toString()
            val updatedEmail = emailEditText.text.toString()
            val updatedRating = ratingEditText.text.toString().toIntOrNull() ?: 0
            val updatedPerformance = performanceEditText.text.toString()
            val updatedSpecialty = specialtyEditText.text.toString()
            val updatedGroup = groupEditText.text.toString()

            studentDAO.updateStudent(studentId, updatedFirstName, updatedLastName, updatedEmail, updatedRating, updatedPerformance, updatedSpecialty, updatedGroup)

            setResult(RESULT_OK)
            finish()
        }
    }
}
