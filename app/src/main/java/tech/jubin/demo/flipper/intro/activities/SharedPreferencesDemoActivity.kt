package tech.jubin.demo.flipper.intro.activities

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import tech.jubin.demo.flipper.intro.R

class SharedPreferencesDemoActivity : AppCompatActivity() {
    private lateinit var content:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shared_preferences_demo)
        content = findViewById(R.id.sp_content)

        findViewById<Button>(R.id.show_sp_content).setOnClickListener {
            refresh()
        }
        refresh()
    }

    @SuppressLint("SetTextI18n")
    private fun refresh(){
        val pref = this.getSharedPreferences("demo_sp", Context.MODE_PRIVATE) ?: return
        content.text = "demo_string: ${pref.getString("demo_string", "default")}\n" +
                "demo_int: ${pref.getInt("demo_int", -1)}"
    }
}