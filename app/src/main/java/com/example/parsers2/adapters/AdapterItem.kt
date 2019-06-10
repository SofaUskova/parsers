package com.example.parsers2.adapters

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.parsers2.ItemModel
import com.example.parsers2.R

class AdapterItem(private var mContext: Context, private var layoutId: Int, data: Array<ItemModel>) :
    ArrayAdapter<ItemModel>(mContext, layoutId, data) {

    private var data: Array<ItemModel>? = null

    init {
        this.data = data
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val inflater = (mContext as Activity).layoutInflater
        val listItem = inflater.inflate(layoutId, parent, false)

        val imageIcon = listItem.findViewById(R.id.imageIcon) as ImageView
        val mName = listItem.findViewById(R.id.mName) as TextView

        val model = data!![position]

        imageIcon.setImageResource(model.icon)
        mName.text = model.name

        return listItem
    }
}