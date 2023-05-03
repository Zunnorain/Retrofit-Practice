package com.example.retrofitapi.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.retrofitapi.R
import com.example.retrofitapi.newsly.Article

class NewsRVAdapter(private val context: Context, private val articles: List<Article>) : RecyclerView.Adapter<NewsRVAdapter.ArticleViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_layout,parent,false)
        return ArticleViewHolder(view)
    }

    override fun getItemCount(): Int {
        return articles.size
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val article:Article = articles[position]
        holder.newsText.text = article.title
        Glide.with(context).load(article.urlToImage).into(holder.newsImage)
    }


    class ArticleViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var newsImage: ImageView = itemView.findViewById(R.id.glideIV)
        var newsText: TextView = itemView.findViewById(R.id.generatedText)
    }

}