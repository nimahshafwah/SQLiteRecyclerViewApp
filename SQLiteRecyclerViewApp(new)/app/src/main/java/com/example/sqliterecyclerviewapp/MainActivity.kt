package com.example.sqliterecyclerviewapp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sqliterecyclerviewapp.DatabaseHelper
import com.example.sqliterecyclerviewapp.R

class MainActivity : AppCompatActivity() {

    private lateinit var databaseHelper: DatabaseHelper
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: MyAdapter
    private lateinit var editTextName: EditText
    private var itemList: MutableList<String> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        databaseHelper = DatabaseHelper(this)

        editTextName = findViewById(R.id.editTextName)
        val buttonAdd: Button = findViewById(R.id.buttonAdd)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        loadData()

        buttonAdd.setOnClickListener {
            val name = editTextName.text.toString()
            if (name.isNotEmpty()) {
                databaseHelper.addData(name)
                editTextName.text.clear()
                loadData()
            }
        }
    }

    private fun loadData() {
        itemList.clear()
        itemList.addAll(databaseHelper.getAllData())
        adapter = MyAdapter(itemList)
        recyclerView.adapter = adapter
    }
}
