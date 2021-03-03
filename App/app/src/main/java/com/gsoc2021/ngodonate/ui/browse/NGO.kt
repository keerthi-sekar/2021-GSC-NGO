package com.gsoc2021.ngodonate.ui.browse

import android.content.Context
import org.json.JSONException
import org.json.JSONObject

data class NGO(
    val name: String,
    val email: String,
    val phoneNumber: String,
    val location: String,
    val volunteer: String) {

    companion object {

        fun getNGOsFromFile(filename: String, context: Context): ArrayList<NGO> {
            val recipeList = ArrayList<NGO>()

            try {
                // Load data
                val jsonString = loadJsonFromAsset("ngo.json", context)
                val json = JSONObject(jsonString)
                val ngos = json.getJSONArray("ngos")

                (0 until ngos.length()).mapTo(recipeList) {
                    NGO(ngos.getJSONObject(it).getString("title"),
                        ngos.getJSONObject(it).getString("description"),
                        ngos.getJSONObject(it).getString("image"),
                        ngos.getJSONObject(it).getString("url"),
                        ngos.getJSONObject(it).getString("dietLabel"))
                }
            } catch (e: JSONException) {
                e.printStackTrace()
            }

            return recipeList
        }

        private fun loadJsonFromAsset(filename: String, context: Context): String? {
            var json: String? = null

            try {
                val inputStream = context.assets.open(filename)
                val size = inputStream.available()
                val buffer = ByteArray(size)
                inputStream.read(buffer)
                inputStream.close()
                json = String(buffer, Charsets.UTF_8)
            } catch (ex: java.io.IOException) {
                ex.printStackTrace()
                return null
            }

            return json
        }
    }
}