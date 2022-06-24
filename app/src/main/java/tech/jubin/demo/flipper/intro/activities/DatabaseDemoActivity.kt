package tech.jubin.demo.flipper.intro.activities

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import tech.jubin.demo.flipper.intro.R
import tech.jubin.demo.flipper.intro.room.UserDatabase

class DatabaseDemoActivity : AppCompatActivity() {
    private lateinit var db: UserDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_database_demo)

        db = UserDatabase.getInstance(this.applicationContext)

        val textView = findViewById<TextView>(R.id.table_content)
        findViewById<Button>(R.id.show_table_content).setOnClickListener {
            Thread {
                val users = db.userDao().getAll()
                Handler(Looper.getMainLooper()).post {
                    textView.text = if (users.isEmpty()) {
                        "Empty"
                    } else {
                        users.joinToString(separator = "\n") {
                            "${it.id}, ${it.firstName}, ${it.lastName}"
                        }
                    }
                }
            }.start()
        }
    }
}