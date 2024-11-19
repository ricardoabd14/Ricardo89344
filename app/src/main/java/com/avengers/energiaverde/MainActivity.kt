package com.avengers.energiaverde

import android.content.Intent
import android.os.Bundle
import android.widget.SearchView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var searchView: SearchView
    private lateinit var adapter: TipAdapter
    private val tips = mutableListOf(
        Tip("Use lâmpadas LED", "Elas consomem menos energia e duram mais."),
        Tip("Desligue aparelhos que não estão em uso", "Evite o consumo em modo de espera."),
        Tip("Aproveite a luz natural", "Abra janelas para economizar energia.")
    )

    // Configurar o launcher para receber o resultado da AddTipActivity
    private val addTipLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == RESULT_OK) {
            val data = result.data
            val title = data?.getStringExtra("TIP_TITLE")
            val description = data?.getStringExtra("TIP_DESCRIPTION")

            if (!title.isNullOrEmpty() && !description.isNullOrEmpty()) {
                tips.add(Tip(title, description))
                adapter.notifyDataSetChanged()
                Toast.makeText(this, "Dica adicionada com sucesso!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        searchView = findViewById(R.id.searchView)
        val fab = findViewById<FloatingActionButton>(R.id.fabAddTip)

        adapter = TipAdapter(tips) { tip ->
            Toast.makeText(this, "Dica: ${tip.title}\n${tip.description}", Toast.LENGTH_SHORT).show()
        }
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean = false

            override fun onQueryTextChange(newText: String?): Boolean {
                val filteredTips = tips.filter {
                    it.title.contains(newText ?: "", ignoreCase = true) ||
                            it.description.contains(newText ?: "", ignoreCase = true)
                }
                adapter.updateList(filteredTips)
                return true
            }
        })

        fab.setOnClickListener {
            val intent = Intent(this, AddTipActivity::class.java)
            addTipLauncher.launch(intent)
        }
    }
}
