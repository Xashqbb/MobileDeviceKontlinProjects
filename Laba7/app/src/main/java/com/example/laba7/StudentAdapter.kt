package com.example.laba7

import Student
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class StudentAdapter(private var students: MutableList<Student>, private val onClick: (Int) -> Unit, private val onDelete: (Int) -> Unit) : RecyclerView.Adapter<StudentAdapter.StudentViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.student_item, parent, false)
        return StudentViewHolder(view)
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        val student = students[position]
        holder.bind(student)

        holder.itemView.setOnClickListener {
            onClick(student.id) // Передаємо ID студента
        }

        holder.deleteButton.setOnClickListener {
            onDelete(student.id) // Передаємо ID студента для видалення
        }
    }

    override fun getItemCount(): Int {
        return students.size
    }

    fun updateStudents(newStudents: List<Student>) {
        students.clear()
        students.addAll(newStudents)
        notifyDataSetChanged()
    }

    inner class StudentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nameTextView: TextView = itemView.findViewById(R.id.nameTextView)
        val deleteButton: Button = itemView.findViewById(R.id.deleteButton)

        fun bind(student: Student) {
            nameTextView.text = "${student.firstName} ${student.lastName}"
        }
    }
}

