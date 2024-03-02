import com.mohamedrejeb.ksoup.html.parser.KsoupHtmlHandler
import kotlinx.serialization.json.Json
import model.Response
import java.io.File
import java.net.HttpURLConnection
import java.net.URL

private const val FILENAME_FULL = "ship24_couriers_response.json"
private const val FILENAME_HEAD = "ship24_couriers_response_head.json"


fun main() {
    val jsonString = File(
        "./src/main/resources/${FILENAME_HEAD}"
    ).readText(charset = Charsets.UTF_8)

    val deserializing = Json
        .decodeFromString<Response>(jsonString)

    deserializing.data.couriers.forEach { courier ->
        if (courier.website != null) {
            println(courier.website)
            val html = getHTML(courier.website)
            println("HTML: $html")
        }
    }
}

private fun getHTML(url: String): String? {
    val urlConnection =
        URL(url).openConnection() as HttpURLConnection
    return try {
        urlConnection.inputStream
            .bufferedReader().readText()
    } catch (e: Exception) {
        null
    } finally {
        urlConnection.disconnect()
    }
}

private fun parse(html: String): String {
    var string = ""
    val handler = KsoupHtmlHandler
        .Builder()
        .onOpenTag { name, attributes, isImplied -> }
        .onText { text ->
            string += text
        }
        .build()

    return string
}