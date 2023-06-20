package com.honours.bbpv2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.VideoView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.io.FileOutputStream

class Videos : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_videos)

        // Create the Retrofit API interface
        val apiInterface = RetrofitClient.getApiInterface()

        // Make the API call
        val call: Call<VideoResponse> = apiInterface.getVideos()

        call.enqueue(object : Callback<VideoResponse> {
            override fun onResponse(call: Call<VideoResponse>, response: Response<VideoResponse>) {
                if (response.isSuccessful) {
                    val data: VideoResponse? = response.body()
                    if (data != null) {

                        val videoData: ByteArray = data.viddata

                        val videoFile = createTempVideoFile(videoData)

                        // Get a reference to the VideoView
                        val videoView = findViewById<VideoView>(R.id.videoView)

                        // Set the video file path
                        videoView.setVideoPath(videoFile.absolutePath)

                        // Start playing the video
                        videoView.start()



                    }
                } else {
                    // Handle unsuccessful response
                    Log.e("Videos", "API call failed with response code: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<VideoResponse>, t: Throwable) {
                // Handle network failure
                Log.e("Video failed", "API call failed: ${t.message}")
            }
        })



    }

    private fun createTempVideoFile(videoData: ByteArray): File {
        val tempDir = cacheDir // Use the appropriate directory to store temporary files
        val tempFile = File.createTempFile("video", null, tempDir)
        tempFile.deleteOnExit()
        FileOutputStream(tempFile).use { fileOutputStream ->
            fileOutputStream.write(videoData)
            fileOutputStream.flush()
        }
        return tempFile
    }
}