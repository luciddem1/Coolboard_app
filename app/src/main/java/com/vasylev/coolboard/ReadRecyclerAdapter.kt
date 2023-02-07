package com.vasylev.coolboard

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.time.Year

class ReadRecyclerAdapter(val context:Context, val elevatorList:ArrayList<Elevator>):RecyclerView.Adapter<ReadRecyclerAdapter.ReadViewHolder>( ){

    class  ReadViewHolder(view: View):RecyclerView.ViewHolder(view){
        val ElevatorName:TextView=view.findViewById(R.id.txtElevatorName)
        val CultureName:TextView=view.findViewById(R.id.txtCultureName)
        val Year:TextView=view.findViewById(R.id.txtYear)
        val StartDay:TextView=view.findViewById(R.id.txtStartDay)
        val FinishDay:TextView=view.findViewById(R.id.txtFinishDay)
        val Enter:TextView=view.findViewById(R.id.txtEnter)
        val Out:TextView=view.findViewById(R.id.txtOut)
        val Lose:TextView=view.findViewById(R.id.txtLose)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReadViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.single_elevator_item,parent,false)
        return ReadViewHolder(view)
    }

    override fun onBindViewHolder(holder: ReadViewHolder, position: Int) {
        holder.ElevatorName.text =elevatorList[position].elevatorName
        holder.CultureName.text=elevatorList[position].cultureName
        holder.Year.text=elevatorList[position].year
        holder.StartDay.text=elevatorList[position].startDay
        holder.Enter.text=elevatorList[position].enter
        holder.Out.text=elevatorList[position].out
        holder.Lose.text=elevatorList[position].lose
        holder.FinishDay.text=elevatorList[position].finishDay
    }

    override fun getItemCount(): Int {
        return elevatorList.size
    }
}