package br.com.silas.digiocash.config

import com.google.gson.Gson
import java.io.BufferedReader
import java.io.FileInputStream
import java.io.InputStreamReader

const val ASSET_BASE_PATH = "../app/src/assets/"

inline fun <reified T> read(path: String): T {
    return Gson().fromJson(readJsonFile(path = path), T::class.java)
}

fun readJsonFile(path: String): String {
    val buffer = BufferedReader(InputStreamReader(FileInputStream(ASSET_BASE_PATH + path)))
    val sb = StringBuilder()
    buffer.lines().forEach { sb.append(it) }
    return sb.toString()
}