package com.example.listedetachesapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.RecyclerView
import com.example.listedetachesapp.Entity.Tache
import com.example.listedetachesapp.Entity.TachesAdapter

class MainActivity : AppCompatActivity() {
    private val taches = mutableListOf<Tache>()
    private lateinit var tachesAdapter: TachesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val editTextTask = findViewById<EditText>(R.id.editTextTask)
        val recyclerViewTasks = findViewById<RecyclerView>(R.id.recyclerViewTasks)
        val buttonAdd = findViewById<Button>(R.id.buttonAdd)

        //val recyclerViewTasks = (RecyclerView)findViewById(R.id.recyclerViewTasks);

        tachesAdapter = TachesAdapter(taches)
        recyclerViewTasks.adapter = tachesAdapter

        // Écouteur d'événement pour le bouton "Ajouter"
        buttonAdd.setOnClickListener {
            val description = editTextTask.text.toString()
            if (description.isNotEmpty()) {
                val nouvelleTache = Tache(description)
                taches.add(nouvelleTache)
                tachesAdapter.notifyItemInserted(taches.size - 1)
                editTextTask.text.clear()
            }
        }
    }
}