package tech.jubin.demo.flipper.intro.activities

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import tech.jubin.demo.flipper.intro.R
import tech.jubin.demo.flipper.intro.network.Contributor
import tech.jubin.demo.flipper.intro.network.NetworkUtils
import tech.jubin.demo.flipper.intro.network.NetworkCallback

class NetworkDemoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_network)

        val tvCount = findViewById<TextView>(R.id.count)
        findViewById<Button>(R.id.send_https_request).setOnClickListener {
            Thread {
                NetworkUtils.sendDemoRequest(object : NetworkCallback {
                    override fun onContributorsReturn(contributors: List<Contributor>?) {
                        Handler(Looper.getMainLooper()).post {
                            tvCount.text = contributors?.size.toString()
                        }
                    }
                })
            }.start()
            Toast.makeText(this, "Https Request Sent!", Toast.LENGTH_SHORT).show()
        }
    }
}