package dominando.android.saveactivitystate

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {

    private var names = arrayListOf<String>()
    private var adapter : ArrayAdapter<String>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState != null) {
            names = savedInstanceState.getStringArrayList("names_list")!!
        }

        val listNames = findViewById<ListView>(R.id.listViewNames)
        val editTextName = findViewById<EditText>(R.id.editTextInputName)
        val buttonAddName = findViewById<Button>(R.id.buttonAddNameToList)

        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, names)

        listNames.adapter = adapter

        buttonAddName.setOnClickListener {
            val name = editTextName.text.toString()
            names.add(editTextName.text.toString())
            editTextName.text.clear()
            adapter?.notifyDataSetChanged()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putStringArrayList("names_list", names)
    }
}