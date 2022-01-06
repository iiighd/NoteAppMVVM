package com.example.noteappvm

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NoteRVAdapter(
    val context: Context,
    val noteClickInterface : NoteClickInterface,
    val noteClickDeleteInterface : NoteClickDeleteInterface
) : RecyclerView.Adapter<NoteRVAdapter.ViewHolder>(){

    private val allNotes = ArrayList<Note>()
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val noteTV = itemView.findViewById<TextView>(R.id.idTvNoteTitle)
        val timeTV = itemView.findViewById<TextView>(R.id.idTVTime)
        val deletIV = itemView.findViewById<ImageView>(R.id.idIVDelete)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        TODO("Not yet implemented")
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.note_rv_item, parent, false);
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        TODO("Not yet implemented")
        holder.noteTV.setText(allNotes.get(position).noteTitle)
        holder.timeTV.setText("Last Updated : "+ allNotes.get(position).timestamp)
        holder.deletIV.setOnClickListener {
            noteClickDeleteInterface.onDeleteIconClick(allNotes.get(position))
        }
        holder.itemView.setOnClickListener {
            noteClickInterface.onNoteClick(allNotes.get(position))
        }
    }

    override fun getItemCount(): Int {
        return allNotes.size
    }

    fun updateList(newList : List<Note>){
        allNotes.clear()
        allNotes.addAll(newList)
        notifyDataSetChanged()
    }

}
interface NoteClickInterface{
    fun onNoteClick(note: Note)
}
interface NoteClickDeleteInterface{
    fun onDeleteIconClick(note: Note)
}