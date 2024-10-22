package com.example.laba7

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var studentDAO: StudentDAO
    private lateinit var studentAdapter: StudentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        studentDAO = StudentDAO(this)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        studentAdapter = StudentAdapter(mutableListOf(), { studentId -> openStudentDetails(studentId) }) { studentId -> deleteStudent(studentId) }

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = studentAdapter

        updateStudentList()

        // Додаємо обробник натискання на кнопку
        findViewById<Button>(R.id.addStudentButton).setOnClickListener {
            openAddStudentActivity()
        }

        // Додаємо обробник для пошуку
        findViewById<Button>(R.id.searchButton).setOnClickListener {
            val query = findViewById<EditText>(R.id.searchEditText).text.toString()
            updateStudentList(query)
        }
    }

    private fun openAddStudentActivity() {
        val intent = Intent(this, AddStudentActivity::class.java)
        startActivityForResult(intent, ADD_STUDENT_REQUEST)
    }

    private fun openStudentDetails(studentId: Int) {
        val intent = Intent(this, StudentDetailsActivity::class.java)
        intent.putExtra("STUDENT_ID", studentId)
        startActivityForResult(intent, EDIT_STUDENT_REQUEST)
    }

    private fun deleteStudent(studentId: Int) {
        studentDAO.deleteStudent(studentId) // Додайте метод видалення у StudentDAO
        updateStudentList()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == EDIT_STUDENT_REQUEST && resultCode == RESULT_OK) {
            updateStudentList()
        } else if (requestCode == ADD_STUDENT_REQUEST && resultCode == RESULT_OK) {
            updateStudentList()
        }
    }

    private fun updateStudentList(query: String = "") {
        val students = if (query.isEmpty()) {
            studentDAO.getAllStudents()
        } else {
            studentDAO.searchStudents(query)
        }
        studentAdapter.updateStudents(students)
    }

    companion object {
        private const val EDIT_STUDENT_REQUEST = 1
        private const val ADD_STUDENT_REQUEST = 2
    }
}
