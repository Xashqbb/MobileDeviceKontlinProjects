package com.example.laba7

import Student
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class StudentViewModel(private val studentDAO: StudentDAO) : ViewModel() {

    private val _students = MutableLiveData<List<Student>>()
    val students: LiveData<List<Student>> get() = _students

    fun loadStudents() {
        _students.value = studentDAO.getAllStudents()
    }

    fun updateStudent(id: Int, firstName: String, lastName: String, email: String, rating: Int, performance: String, specialty: String, studentGroup: String) {
        studentDAO.updateStudent(id, firstName, lastName, email, rating, performance, specialty, studentGroup)
        loadStudents()
    }
}
