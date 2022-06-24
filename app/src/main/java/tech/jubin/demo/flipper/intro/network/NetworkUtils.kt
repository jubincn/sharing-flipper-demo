package tech.jubin.demo.flipper.intro.network

import com.facebook.flipper.plugins.network.FlipperOkhttpInterceptor
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonClass
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.ResponseBody


internal object NetworkUtils {
    private var okHttpClient = OkHttpClient.Builder().build()

    fun addInterceptor(flipperInterceptor: FlipperOkhttpInterceptor) {
        okHttpClient = okHttpClient.newBuilder().addInterceptor(flipperInterceptor).build()
    }

    private const val ENDPOINT = "https://api.github.com/repos/square/okhttp/contributors"
    private val MOSHI: Moshi = Moshi.Builder().build()
    private val CONTRIBUTORS_JSON_ADAPTER: JsonAdapter<List<Contributor>> = MOSHI.adapter(
        Types.newParameterizedType(MutableList::class.java, Contributor::class.java)
    )

    fun sendDemoRequest(callback: NetworkCallback) {
        // Create request for remote resource.
        val request = Request.Builder().url(ENDPOINT).build()


        // Execute the request and retrieve the response.
        okHttpClient.newCall(request).execute().use { response ->
            // Deserialize HTTP response to concrete type.
            val body: ResponseBody? = response.body
            val contributors = body?.source()?.let { CONTRIBUTORS_JSON_ADAPTER.fromJson(it) }
            callback.onContributorsReturn(contributors)
        }
    }
}

internal interface NetworkCallback {
    fun onContributorsReturn(contributors: List<Contributor>?)
}

@JsonClass(generateAdapter = true)
internal data class Contributor(
    var login: String? = null,
    var contributions: Int = 0
)
