package tech.jubin.demo.flipper.intro.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import tech.jubin.demo.flipper.intro.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.crasher).setOnClickListener {
            throw IllegalStateException("Crashed by crasher 233333333333333333333333")
        }
        findViewById<Button>(R.id.layout_inspector).setOnClickListener {
            startActivity(Intent(this, LayoutInspectorDemoActivity::class.java))
        }
        findViewById<Button>(R.id.database).setOnClickListener {
            startActivity(Intent(this, DatabaseDemoActivity::class.java))
        }
//        findViewById<Button>(R.id.navigation).setOnClickListener {
//
//        }
        findViewById<Button>(R.id.network).setOnClickListener {
            startActivity(Intent(this, NetworkDemoActivity::class.java))
        }
        findViewById<Button>(R.id.shared_preferences).setOnClickListener {
            startActivity(Intent(this, SharedPreferencesDemoActivity::class.java))
        }

        // put some value in sp
        val sharedPref = getSharedPreferences("demo_sp", Context.MODE_PRIVATE) ?: return
        if (!sharedPref.getBoolean("demo_updated", false)) {
            with(sharedPref.edit()) {
                putString("demo_string", "hello")
                putInt("demo_int", 5)
                putBoolean("demo_updated", true)
                apply()
            }
        }
    }
}