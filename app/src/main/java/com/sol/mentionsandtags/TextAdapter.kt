package com.sol.mentionsandtags

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sol.mentionsandtags.databinding.SocialtextviewBinding
import com.sol.textviewutils.OnViewClick

class TextAdapter(private val textSamples:List<String>, val callback: OnViewClick, val onClick: View.OnClickListener):
    RecyclerView.Adapter<TextAdapter.TextViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TextViewHolder {

        return TextViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.socialtextview,parent,false)
        )
    }

    override fun onBindViewHolder(holder: TextViewHolder, position: Int) {
        val txt = textSamples[position]
        holder.binding.socialTextView.text = txt

        if(position%2 ==0){
            holder.binding.socialTextView.mentionColor = Color.RED
            holder.binding.socialTextView.hashColor  = Color.YELLOW
            holder.binding.socialTextView.boldMentionText = true
            holder.binding.socialTextView.boldHashText = true
        }else{
            holder.binding.socialTextView.mentionColor = Color.parseColor("#FF8A80")
            holder.binding.socialTextView.hashColor  = Color.parseColor("#80D8FF")
        }
    }

    override fun getItemCount(): Int {
        return textSamples.size
    }


    inner class TextViewHolder(item: View) : RecyclerView.ViewHolder(item){
        val binding: SocialtextviewBinding = SocialtextviewBinding.bind(itemView)

        init {
            binding.socialTextView.setSocialCallback(callback)
            binding.socialTextView.setOnClickListener(onClick)
        }

    }



}