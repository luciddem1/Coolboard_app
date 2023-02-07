package com.vasylev.coolboard

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley

lateinit var readProgressLayout:RelativeLayout
lateinit var readProgressBar: ProgressBar
lateinit var recyclerView:RecyclerView
lateinit var layoutManager:LinearLayoutManager
lateinit var recyclerAdapter: ReadRecyclerAdapter

class ReadActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_read)

        readProgressLayout=findViewById(R.id.readProgressLayout)
        readProgressBar=findViewById(R.id.readProgressBar)
        recyclerView=findViewById(R.id.recyclerView)
        layoutManager= LinearLayoutManager(this)


        val elevatorList = arrayListOf<Elevator>()

        val queue =Volley.newRequestQueue(this)
        val url ="https://script.google.com/macros/s/AKfycbx9CIdMJClFupv6TsItugS76Mqy1SwI5bUXD7XLyuQE6_zXdAaMcOP_qwp6Sg5oWYF_/exec"
        val jsonObjectRequest =object:JsonObjectRequest(com.android.volley.Request.Method.GET,url,null, Response.Listener {
            readProgressLayout.visibility=View.GONE
            readProgressBar.visibility=View.GONE
          val data =it.getJSONArray("elList")
          for(i in 0 until data.length()){
              val elevatorJasonObject = data.getJSONObject(i)
              val elevatorObject = Elevator(
                          elevatorJasonObject.getString("itemElevatorName"),
                          elevatorJasonObject.getString("itemCultureName"),
                          elevatorJasonObject.getString("itemYear"),
                          elevatorJasonObject.getString("itemStartDay"),
                          elevatorJasonObject.getString("itemEnter"),
                          elevatorJasonObject.getString("itemOut"),
                          elevatorJasonObject.getString("itemLose"),
                          elevatorJasonObject.getString("itemFinishDay")
              )
              elevatorList.add(elevatorObject)
          }
            recyclerAdapter= ReadRecyclerAdapter(this,elevatorList)
            recyclerView.adapter= recyclerAdapter
            recyclerView.layoutManager= layoutManager
        },Response.ErrorListener {
            readProgressLayout.visibility=View.GONE
            readProgressBar.visibility=View.GONE
            Toast.makeText(this@ReadActivity,it.toString(), Toast.LENGTH_SHORT).show()
        }){
            override fun getHeaders(): MutableMap<String, String> {
                return super.getHeaders()
            }
        }
        queue.add(jsonObjectRequest)
    }
}