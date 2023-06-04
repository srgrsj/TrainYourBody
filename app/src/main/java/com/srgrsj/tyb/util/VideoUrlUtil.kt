package com.srgrsj.tyb.util

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import java.util.regex.Pattern

object VideoUrlUtil {
    @Serializable
    data class VideoItem(val id: VideoId, val snippet: VideoSnippet)

    @Serializable
    data class VideoId(val videoId: String)

    @Serializable
    data class VideoSnippet(val title: String, val thumbnails: VideoThumbnails)

    @Serializable
    data class VideoThumbnails(val default: VideoThumbnail)

    @Serializable
    data class VideoThumbnail(val url: String)

    @Serializable
    data class VideoResponse(val items: List<VideoItem>)

    suspend fun getCorrectUrl(url: String): String {
        val httpClient = HttpClient()
        val apiKey = "AIzaSyAGWKsuaQcvGmcu5yVtg_C07SqlesU7lsw"
        val videoId = extractVideoIdFromUrl(url)

        val response: HttpResponse =
            httpClient.get("https://www.googleapis.com/youtube/v3/videos?part=snippet&id=$videoId&key=$apiKey")

        val responseBody = response.body<String>()
        val videoResponse = Json.decodeFromString<VideoResponse>(responseBody)

        return videoResponse.items[0].snippet.thumbnails.default.url
    }


    fun extractVideoIdFromUrl(url: String): String? {
        val pattern =
            "(?<=youtu.be/|watch\\?v=|/videos/|embed\\/|\\/v\\/|\\/e\\/|watch\\?v%3D|watch\\?feature=player_embedded&v=|%2Fvideos%2F|embed%2F|youtu.be%2F|\\/v%2F|\\?v=|&v=|^youtu\\.be/|watch\\?v=|/videos/|embed\\/|\\/v\\/|\\/e\\/|watch\\?v%3D|watch\\?feature=player_embedded&v=|%2Fvideos%2F|embed%2F|youtu.be%2F|\\/v%2F|\\?v=|&v=|^youtu\\.be/|watch\\?v=|/videos/|embed\\/|\\/v\\/|\\/e\\/|watch\\?v%3D|watch\\?feature=player_embedded&v=|%2Fvideos%2F|embed%2F|youtu.be%2F|\\/v%2F|\\?v=|&v=|^youtu\\.be/|watch\\?v=|/videos/|embed\\/|\\/v\\/|\\/e\\/|watch\\?v%3D|watch\\?feature=player_embedded&v=|%2Fvideos%2F|embed%2F|youtu.be%2F|\\/v%2F|\\?v=|&v=|^youtu\\.be/|watch\\?v=|/videos/|embed\\/|\\/v\\/|\\/e\\/|watch\\?v%3D|watch\\?feature=player_embedded&v=|%2Fvideos%2F|embed%2F|youtu.be%2F|\\/v%2F|\\?v=|&v=)[^#\\&\\?\\n]*"
        val compiledPattern = Pattern.compile(pattern)
        val matcher = compiledPattern.matcher(url)
        if (matcher.find()) {
            return matcher.group()
        }
        return null
    }
}