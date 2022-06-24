package tech.jubin.demo.flipper.intro.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.facebook.flipper.plugins.navigation.NavigationFlipperPlugin
import tech.jubin.demo.flipper.intro.R
import java.util.*

class DeepLinkActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_deep_link)
        NavigationFlipperPlugin.getInstance()
            .sendNavigationEvent("flipper://deep_link_activity/", "DeepLinkActivity", Date())
    }
}