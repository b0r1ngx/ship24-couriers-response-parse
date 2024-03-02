import kotlinx.serialization.json.Json
import model.Response
import java.io.File

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
        }
    }
}
