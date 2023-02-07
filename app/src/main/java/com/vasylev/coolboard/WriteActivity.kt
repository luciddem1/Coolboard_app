package com.vasylev.coolboard


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

lateinit var writeProgressLayout: RelativeLayout
lateinit var writeProgressBar: ProgressBar
lateinit var edtElecatorName:EditText
lateinit var edtCulrureName:EditText
lateinit var edtYear:EditText
lateinit var edtStartDay:EditText
lateinit var edtEnter:EditText
lateinit var edtOut:EditText
lateinit var edtFinishDay:EditText
lateinit var edtLose:EditText
lateinit var btnSaveToDrive:Button

class WriteActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_write)

        writeProgressLayout=findViewById(R.id.writeProgressLayout)
        writeProgressBar=findViewById(R.id.writeProgressBar)
        edtElecatorName=findViewById(R.id.edtElecatorName)
        edtCulrureName=findViewById(R.id.edtCulrureName)
        edtYear=findViewById(R.id.edtYear)
        edtStartDay=findViewById(R.id.edtStartDay)
        edtEnter=findViewById(R.id.edtEnter)
        edtOut=findViewById(R.id.edtOut)
        edtLose=findViewById(R.id.edtLose)
        edtFinishDay=findViewById(R.id.edtFinishDay)
        btnSaveToDrive=findViewById(R.id.btnSaveToDrive)

        writeProgressLayout.visibility = View.GONE
        writeProgressBar.visibility = View.GONE

        btnSaveToDrive.setOnClickListener {
            if(edtElecatorName.text.toString().isEmpty() or edtCulrureName.text.toString().isEmpty() or edtYear.text.toString().isEmpty() or edtStartDay.text.toString().isEmpty() or
                edtEnter.text.toString().isEmpty() or edtOut.text.toString().isEmpty() or edtFinishDay.text.toString().isEmpty()){
                Toast.makeText(this@WriteActivity,"Заповніть всі поля!",Toast.LENGTH_SHORT).show()
            }else{
                val url ="https://script.google.com/macros/s/AKfycbx1l2_aAKcB1zuPsHqU9Hy0uS0ycuAh3KRaafEMxaDA0aqpNg0Iv1c5XGZlbBsAZDPS/exec"
                val stringRequest =object :StringRequest(Request.Method.POST,url,
                    Response.Listener {
                        Toast.makeText(this@WriteActivity,it.toString(),Toast.LENGTH_SHORT).show()
                    },Response.ErrorListener {
                        Toast.makeText(this@WriteActivity,it.toString(),Toast.LENGTH_SHORT).show()
                    }){
                    override  fun getParams(): MutableMap<String,String>{
                        val params =HashMap<String,String>()
                        params["ElevatorName"]= edtElecatorName.text.toString()
                        params["CultureName"]= edtCulrureName.text.toString()
                        params["Year"] = edtYear.text.toString()
                        params["StartDay"]= edtStartDay.text.toString()
                        params["Enter"]= edtEnter.text.toString()
                        params["Out"]= edtOut.text.toString()
                        params["Lose"]= edtLose.text.toString()
                        params["FinishDay"]= edtFinishDay.text.toString()
                        return  params
                    }
                }
                val queue: RequestQueue =Volley.newRequestQueue((this@WriteActivity))
                queue.add(stringRequest)
            }
            val intent = Intent(this@WriteActivity,MainActivity::class.java)
            startActivity(intent)
        }

    }
}