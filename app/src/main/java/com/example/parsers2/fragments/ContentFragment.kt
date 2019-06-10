package com.example.parsers2.fragments

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import com.example.parsers2.R
import com.example.parsers2.activities.ResultActivity

class ContentFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_content, container, false)
        val editText: EditText = view.findViewById(R.id.editText)

        view.findViewById<Button>(R.id.result).setOnClickListener {
            val intent = Intent(view.context, ResultActivity::class.java)
            intent.putExtra("TEXT", editText.text.toString())

            startActivity(intent)
        }

        return view
    }
}