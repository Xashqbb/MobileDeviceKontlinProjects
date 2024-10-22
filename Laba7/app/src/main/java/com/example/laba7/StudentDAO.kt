package com.example.laba7
import DatabaseHelper
import Student
import android.content.ContentValues
import android.content.Context
import android.database.Cursor

class StudentDAO(context: Context) {
    private val dbHelper = DatabaseHelper(context)

    fun getStudentById(id: Int): Student? {
        val db = dbHelper.readableDatabase
        val cursor: Cursor = db.query(
            DatabaseHelper.TABLE_NAME, null,
            "${DatabaseHelper.COLUMN_ID} = ?", arrayOf(id.toString()),
            null, null, null
        )

        return if (cursor.moveToFirst()) {
            val student = Student(
                id = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_ID)),
                firstName = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_FIRST_NAME)),
                lastName = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_LAST_NAME)),
                email = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_EMAIL)),
                rating = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_RATING)),
                performance = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_PERFORMANCE)),
                specialty = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_SPECIALTY)),
                student_group = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_GROUP))
            )
            cursor.close()
            student
        } else {
            cursor.close()
            null
        }
    }

    fun insertStudent(student: Student): Long {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put(DatabaseHelper.COLUMN_FIRST_NAME, student.firstName)
            put(DatabaseHelper.COLUMN_LAST_NAME, student.lastName)
            put(DatabaseHelper.COLUMN_EMAIL, student.email)
            put(DatabaseHelper.COLUMN_RATING, student.rating)
            put(DatabaseHelper.COLUMN_PERFORMANCE, student.performance)
            put(DatabaseHelper.COLUMN_SPECIALTY, student.specialty)
            put(DatabaseHelper.COLUMN_GROUP, student.student_group)
        }
        return db.insert(DatabaseHelper.TABLE_NAME, null, values)
    }

    fun updateStudent(id: Int, firstName: String, lastName: String, email: String, rating: Int, performance: String, specialty: String, studentGroup: String) {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put(DatabaseHelper.COLUMN_FIRST_NAME, firstName)
            put(DatabaseHelper.COLUMN_LAST_NAME, lastName)
            put(DatabaseHelper.COLUMN_EMAIL, email)
            put(DatabaseHelper.COLUMN_RATING, rating)
            put(DatabaseHelper.COLUMN_PERFORMANCE, performance)
            put(DatabaseHelper.COLUMN_SPECIALTY, specialty)
            put(DatabaseHelper.COLUMN_GROUP, studentGroup)
        }

        // Оновлення даних у базі
        db.update(DatabaseHelper.TABLE_NAME, values, "${DatabaseHelper.COLUMN_ID} = ?", arrayOf(id.toString()))
        db.close()
    }

    fun getAllStudents(): List<Student> {
        val students = mutableListOf<Student>()
        val db = dbHelper.readableDatabase
        val cursor: Cursor = db.query(
            DatabaseHelper.TABLE_NAME, null, null, null, null, null, null
        )

        if (cursor.moveToFirst()) {
            do {
                val student = Student(
                    id = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_ID)),
                    firstName = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_FIRST_NAME)),
                    lastName = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_LAST_NAME)),
                    email = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_EMAIL)),
                    rating = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_RATING)),
                    performance = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_PERFORMANCE)),
                    specialty = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_SPECIALTY)),
                    student_group = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_GROUP))
                )
                students.add(student)
            } while (cursor.moveToNext())
        }
        cursor.close()
        return students
    }

    fun searchBySpecialty(specialty: String): List<Student> {
        val db = dbHelper.readableDatabase
        val cursor: Cursor = db.query(
            DatabaseHelper.TABLE_NAME, null,
            "${DatabaseHelper.COLUMN_SPECIALTY} = ?", arrayOf(specialty),
            null, null, null
        )
        return getStudentsFromCursor(cursor)
    }

    fun searchByRating(rating: Int): List<Student> {
        val db = dbHelper.readableDatabase
        val cursor: Cursor = db.query(
            DatabaseHelper.TABLE_NAME, null,
            "${DatabaseHelper.COLUMN_RATING} = ?", arrayOf(rating.toString()),
            null, null, null
        )
        return getStudentsFromCursor(cursor)
    }

    private fun getStudentsFromCursor(cursor: Cursor): List<Student> {
        val students = mutableListOf<Student>()
        if (cursor.moveToFirst()) {
            do {
                val student = Student(
                    id = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_ID)),
                    firstName = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_FIRST_NAME)),
                    lastName = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_LAST_NAME)),
                    email = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_EMAIL)),
                    rating = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_RATING)),
                    performance = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_PERFORMANCE)),
                    specialty = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_SPECIALTY)),
                    student_group = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_GROUP))
                )
                students.add(student)
            } while (cursor.moveToNext())
        }
        cursor.close()
        return students
    }
    fun deleteStudent(id: Int) {
        val db = dbHelper.writableDatabase
        db.delete(DatabaseHelper.TABLE_NAME, "${DatabaseHelper.COLUMN_ID} = ?", arrayOf(id.toString()))
        db.close()
    }
    fun searchStudents(query: String): List<Student> {
        val students = mutableListOf<Student>()
        students.addAll(searchBySpecialty(query))

        val ratingQuery = query.toIntOrNull()
        if (ratingQuery != null) {
            students.addAll(searchByRating(ratingQuery))
        }

        return students
    }
}

