package com.example.myapplication

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import java.util.*

class NewsListFragment : Fragment() {
    private var rootView: View? = null
    private var rlNewsList: RecyclerView? = null
    private var newsListAdapter: NewsListAdapter? = null

    companion object {
        var isTwoPage = false
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView = LayoutInflater.from(context).inflate(R.layout.fragment_news_list, container, false)
        rlNewsList = rootView!!.findViewById(R.id.rv_news_list)
        rlNewsList!!.layoutManager = LinearLayoutManager(context)
        newsListAdapter = NewsListAdapter(activity, getNews())
        rlNewsList!!.adapter = newsListAdapter
        return rootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        isTwoPage = activity.findViewById<FrameLayout>(R.id.fl_news_detail) != null
    }

    //-----------------------------------生成列表数据-------------------------------
    private fun getNews(): ArrayList<News> {
        var newsList = ArrayList<News>()
        for (i in 0 until 50) {
            var news = News()
            news.title = "This is news title $i"
            news.content = getRandomLengthContent("This is news content $i. ")
            newsList.add(news)
        }
        return newsList
    }

    private fun getRandomLengthContent(content: String): String {
        var length = Random().nextInt(20) + 1;
        var stringBuffer = StringBuffer()
        for (i in 0 until length) {
            stringBuffer.append(content)
        }
        return stringBuffer.toString()
    }

    //-------------------------------------------RecyclerView Adapter----------------------------------------
    class NewsListAdapter(var mContext: FragmentActivity, var listData: ArrayList<News>) : RecyclerView.Adapter<NewsListAdapter.NewsListHolder>() {
        class NewsListHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            var tvTitle: TextView = itemView.findViewById(R.id.tv_news_title)
        }

        override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): NewsListHolder {
            return NewsListHolder(LayoutInflater.from(mContext).inflate(R.layout.item_title_list, parent, false))
        }

        override fun getItemCount(): Int {
            return listData.size
        }

        override fun onBindViewHolder(holder: NewsListHolder?, position: Int) {
            holder!!.tvTitle.text = listData[position].title
            holder!!.tvTitle.setOnClickListener {
                if (isTwoPage) {
                    var newsDetailFragment= mContext.supportFragmentManager.findFragmentById(R.id.fg_main_news_detail) as NewsDetailFragment
                    newsDetailFragment.setPageData(listData[position].title, listData[position].content)
                } else {
                    NewsDetailActivity.jumpPage(mContext, listData[position].title, listData[position].content)
                }
            }
        }
    }
}