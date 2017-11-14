package com.diegoalvis.android.sicor

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_news.view.*
import kotlinx.android.synthetic.main.item_article.view.*
import java.util.*

class NewsFragment : Fragment() {

    private lateinit var articlesAdapter: ArticlesAdapter

    val urls = arrayOf("https://www.ejercito.mil.co/recursos_user/imagenes//Zapata_Tic/Mujer_militar_/mujermilitar.jpg",
                        "https://contactohoy.com.mx/wp-content/uploads/2017/02/Ejercito-Mexicano-min.jpg",
                        "https://nuestrosheroesejc.files.wordpress.com/2017/07/img_7931.jpg?w=940",
                        "https://upload.wikimedia.org/wikipedia/commons/9/91/Armas_eje_ecu.JPG",
                        "https://image.isu.pub/160316193332-7850eccb62341551d3ed1b5813357eb7/jpg/page_1.jpg",
                        "http://www.elcolombiano.com/documents/10157/0/2000x1515/0c0/0d0/none/11101/IBSI/image_content_25728552_20160405233938.jpg")

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val v = inflater!!.inflate(R.layout.fragment_news, container, false)
        articlesAdapter = ArticlesAdapter(context)
        v.recyclerArticle.adapter = articlesAdapter


        for (i in 0..10) {
            articlesAdapter.items.add(urls[Random().nextInt(6)])
        }

        return v
    }


    class ArticlesAdapter(val context: Context) : RecyclerView.Adapter<ArticlesAdapter.ArticleViewHolder>() {

        var items: ArrayList<String> = ArrayList()
            set(value) {
                field = value
                notifyDataSetChanged()
            }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder
                = ArticleViewHolder(parent.inflate(R.layout.item_article))

        override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
            Picasso.with(context).load(items[position]).into(holder.image)
        }

        override fun getItemCount() = items.size


        class ArticleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            var image: ImageView = itemView.articleImage
        }

    }

}
