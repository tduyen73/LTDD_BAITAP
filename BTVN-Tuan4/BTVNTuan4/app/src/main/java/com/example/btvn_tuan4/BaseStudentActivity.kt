package com.example.btvn_tuan4

import android.app.ActivityOptions
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.*

open class BaseStudentActivity : AppCompatActivity() {

    private lateinit var searchBar: EditText
    protected lateinit var txtStudentName: EditText
    protected lateinit var btnChange: Button
    protected lateinit var bookList: LinearLayout
    protected lateinit var btnAdd: Button
    private lateinit var btnBack: ImageButton
    private lateinit var pageTitle: TextView

    private val books = mutableListOf<Pair<String, String>>()
    private lateinit var sharedPref: android.content.SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base_student)

        // Ánh xạ view
        searchBar = findViewById(R.id.searchBar)
        txtStudentName = findViewById(R.id.txtStudentName)
        btnChange = findViewById(R.id.btnChange)
        bookList = findViewById(R.id.bookList)
        btnAdd = findViewById(R.id.btnAdd)
        btnBack = findViewById(R.id.btnBack)
        pageTitle = findViewById(R.id.pageTitle)

        sharedPref = getSharedPreferences("LibraryPrefs", Context.MODE_PRIVATE)

        // ✅ Nút quay lại dùng ActivityOptions (chuẩn mới, không warning)
        btnBack.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            val options = ActivityOptions.makeCustomAnimation(
                this,
                android.R.anim.slide_in_left,
                android.R.anim.slide_out_right
            )
            startActivity(intent, options.toBundle())
            finish()
        }

        // ✅ Đổi tên sinh viên
        btnChange.setOnClickListener {
            Toast.makeText(this, "Đổi tên sinh viên thành: ${txtStudentName.text}", Toast.LENGTH_SHORT).show()
            pageTitle.text = "Quản lý thư viện - ${txtStudentName.text}"
        }

        // ✅ Thêm sách mới
        btnAdd.setOnClickListener {
            val newBook = "Sách ${books.size + 1}"
            val time = SimpleDateFormat("HH:mm - dd/MM/yyyy", Locale.getDefault()).format(Date())
            books.add(Pair(newBook, time))
            saveBooks()
            renderBookList("")
        }

        // ✅ Tìm kiếm sách
        searchBar.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                renderBookList(s.toString())
            }
            override fun afterTextChanged(s: Editable?) {}
        })

        loadSavedBooks()
        renderBookList("")
    }

    private fun renderBookList(filter: String) {
        bookList.removeAllViews()
        val filteredBooks = if (filter.isBlank()) books else books.filter {
            it.first.contains(filter, ignoreCase = true)
        }

        if (filteredBooks.isEmpty()) {
            val tv = TextView(this)
            tv.text = "Bạn chưa mượn quyền sách nào\nNhấn 'Thêm' để bắt đầu hành trình đọc sách!"
            tv.textAlignment = TextView.TEXT_ALIGNMENT_CENTER
            bookList.addView(tv)
        } else {
            for ((bookName, time) in filteredBooks) {
                val row = LinearLayout(this)
                row.orientation = LinearLayout.HORIZONTAL
                row.setPadding(0, 8, 0, 8)

                val checkBox = CheckBox(this)
                checkBox.text = "$bookName\n$time"
                checkBox.isChecked = true
                checkBox.layoutParams =
                    LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1f)

                val btnDelete = Button(this)
                btnDelete.text = "Xóa"
                btnDelete.setOnClickListener {
                    books.remove(Pair(bookName, time))
                    saveBooks()
                    renderBookList("")
                }

                row.addView(checkBox)
                row.addView(btnDelete)
                bookList.addView(row)
            }
        }
    }

    private fun saveBooks() {
        val editor = sharedPref.edit()
        val key = txtStudentName.text.toString()
        val serialized = books.joinToString(";") { "${it.first}|${it.second}" }
        editor.putString(key, serialized)
        editor.apply()
    }

    private fun loadSavedBooks() {
        val key = txtStudentName.text.toString()
        val saved = sharedPref.getString(key, null)
        books.clear()
        saved?.split(";")?.forEach {
            val parts = it.split("|")
            if (parts.size == 2) {
                books.add(Pair(parts[0], parts[1]))
            }
        }
    }
}
