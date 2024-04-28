package com.developersmarket.componentscompose.util

import android.util.Log
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.URL
import java.net.URLConnection
import java.util.regex.Pattern
import java.util.regex.PatternSyntaxException


class PlayStoreVersionChecker {

    companion object{
        var play_StoreVersion: String = "0.0.0"
        var client = OkHttpClient()



         fun execute(url: String): String {
            val request: Request = Request.Builder()
                .url(url)
                .build()
            val response = client.newCall(request).execute()
            return response.body.toString()
        }


         fun getPlayStoreVersion():String{

            try {
                val apppack ="com.q4u.software"
                val html = execute("https://play.google.com/store/apps/details?id=$apppack&hl=en")
                val blockPattern = Pattern.compile("Current Version.*([0-9]+\\.[0-9]+\\.[0-9]+)</span>")
                val blockMatch = blockPattern.matcher(html)
                if (blockMatch.find()) {
                    val versionPattern = Pattern.compile("[0-9]+\\.[0-9]+\\.[0-9]+")
                    val versionMatch = blockMatch.group(1)?.let { versionPattern.matcher(it) }
                    if (versionMatch!!.find()) {
                        play_StoreVersion = versionMatch.group(1)
                    }
                }

            }catch (e: IOException){
                Log.d("myerror",e.message.toString())
            }

            return play_StoreVersion

        }

    }




}