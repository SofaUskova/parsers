package com.example.parsers2.activities

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import com.example.parsers2.R

class ResultActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.result_activity)

        val settings = this.getSharedPreferences("SETTINGS", Context.MODE_PRIVATE)
        var setting: String = ""
        if (settings != null) {
            setting = settings.getString("settings", "0")
        }

        val text = intent.getStringExtra("TEXT")

        findViewById<TextView>(R.id.textView).text = when (setting) {
            "minus" -> {
                val result = Regex("""\s+""").replace(text, "-")
                result
            }
            "number" -> {
                val array = text.split(" ")
                val result =
                    array.map {
                        if (it.matches(Regex("""8\(\d{3}\)\d{3}\-\d{2}\-\d{2}""")))
                            it.replace(Regex("""8\(\d"""), "+375-")
                                .replace(Regex("""\)"""), "-")
                        else
                            it
                    }
                result.joinToString(" ")
            }
            "four" -> {
                val array = text.split(" ")
                val result =
                    array.map {
                        if (it.matches(Regex("""[a-zA-Z]{4}+""")))
                            it.toUpperCase()
                        else
                            it
                    }
                result.joinToString(" ")
            }
            "tags" -> {
                val array = text.split(" ")
                val result =
                    array.map {
                        if (it.matches(Regex("""^<([a-z]+)([^>]+)*(?:>(.*)<\/\1>|\s+\/>)${'$'}""")))
                            it
                    }
                result.joinToString(" ")
            }
            "link" -> {
                val array = text.split(" ")
                val result =
                    array.map {
                        if (it.matches(Regex("""(www.)([\da-z]+\.(com|ru))""")))
                            "http://$it"
                        else
                            it
                    }
                result.joinToString(" ")
            }
            else -> {
                text
            }
        }

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> {
                super.onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}