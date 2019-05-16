package com.example.architecturecomponents.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView
import com.example.architecturecomponents.Model.Word
import com.example.architecturecomponents.R
import java.util.zip.Inflater

class WordListAdapter internal constructor(context: Context): RecyclerView.Adapter<WordListAdapter.WordViewHolder>(){

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var words = emptyList<Word>() //copy of words

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {

        val itemView = inflater.inflate(R.layout.recyclerview_item, parent, false)
        return WordViewHolder(itemView)
    }

    override fun getItemCount(): Int {

        return words.size

    }

    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {

        val current = words[position]
        holder.wordItemView.text = current.word

    }

    internal fun setWords(words: List<Word>) {
        this.words = words
        notifyDataSetChanged()
    }

    inner class WordViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        val wordItemView : TextView = itemView.findViewById(R.id.textView)

    }
}