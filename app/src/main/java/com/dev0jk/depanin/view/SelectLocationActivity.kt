package com.dev0jk.depanin.view

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.dev0jk.depanin.R
import com.dev0jk.depanin.databinding.ActivitySelectedLocationBinding
import com.dev0jk.depanin.model.entity.Gouvernement
import com.dev0jk.depanin.model.entity.Location
import com.dev0jk.depanin.utils.LoadingAlert
import com.dev0jk.depanin.view.home.HomeActivity
import com.dev0jk.depanin.vm.UserVM
import com.dev0jk.depanin.vm.WorkerVM
import org.json.JSONArray
import java.io.IOException
import java.io.InputStream

class SelectLocationActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySelectedLocationBinding
    var allJson = arrayListOf<String>()
    var type = arrayListOf<String>()
    lateinit var userVM : UserVM
    lateinit var workerVM: WorkerVM
    private var gouv_name = arrayListOf<String>()
    private var lenghtOfCities = HashMap<String, String>()
    private var _listOfCites: ArrayList<Gouvernement> = arrayListOf()
    private var _listOfCites2: ArrayList<Gouvernement> = arrayListOf()
    var listOfCites = HashMap<String, String>()
    var adapterItems: ArrayAdapter<String>? = null
    lateinit var adapter : GouvernementAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySelectedLocationBinding.inflate(layoutInflater)
        setContentView(binding.root)
/*        binding.getStarted.isClickable =false
        binding.getStarted.isEnabled = false*/
        userVM = UserVM()
        workerVM = WorkerVM()
        readJson()
        dropDownList()

        adapter = GouvernementAdapter(this, _listOfCites2)
        binding.recyclerViewer.layoutManager = LinearLayoutManager(this)
        binding.recyclerViewer.adapter = adapter

        binding.getStarted.setOnClickListener{
            NextBtn()
        }
        binding.Cancel.setOnClickListener {
            CancelBtn()
        }




    }
    private fun readJson() {
        var json: String? = null

        try {
            val inputStream: InputStream = assets.open("gouv/Tunisian_municipality.json")
            json = inputStream.bufferedReader().use { it.readText() }

            var jsonarr = JSONArray(json)
            for (i in 0 until jsonarr.length()) {
                var jsonobj = jsonarr.getJSONObject(i)
                allJson.add(jsonobj.getString("properties"))
                gouv_name.add(jsonobj.getJSONObject("properties").getString("gouv_name"))
                var len = jsonobj.getJSONObject("properties").getJSONArray("cites").length()


                for (j in 0 until len) {

                    type.add(jsonobj.getJSONObject("properties").getString("gouv_name").toString())
/*                    lenword[jsonobj.getJSONObject("properties").getJSONArray("cites").getJSONObject(j).getString("gouv_name")] =
                        jsonobj.getJSONObject("properties").getJSONArray("cites").getJSONObject(j).getString("NAME_EN")*/



                    lenghtOfCities[jsonobj.getJSONObject("properties").getJSONArray("cites").getJSONObject(j)
                        .getString("gouv_name")] =
                        jsonobj.getJSONObject("properties").getJSONArray("cites").getJSONObject(j)
                            .getString("NAME_EN")

                    //lenword_AR.addAll(listOf(jsonobj.getJSONObject("properties").getJSONArray("cites").getJSONObject(j).getString("NAME_EN_AR")))
                    //Log.v("================>lenword","$lenword")

                    for ((k, v) in lenghtOfCities) {
                        var gouvernement = Gouvernement(k, v)
                        gouvernement.id = v
                        gouvernement.value = k
                        listOfCites[k] = v
                        _listOfCites.add(gouvernement)

                    }



                }


            }

        } catch (e: IOException) {

        }
    }
    private fun dropDownList() {
        adapterItems = ArrayAdapter(this, R.layout.list_item, gouv_name)
        binding.autoCompleteTxt.setAdapter(adapterItems)

        binding.autoCompleteTxt.onItemClickListener =
            AdapterView.OnItemClickListener { parent, view, position, id ->
                val item = parent.getItemAtPosition(position).toString()
                item.dropLast(1)
                //Toast.makeText(applicationContext, "Item: $item", Toast.LENGTH_SHORT).show()

                _listOfCites2.clear()
                Log.println(Log.ASSERT,"================>2", _listOfCites2.toString())
                for ((k, v) in _listOfCites) {
                    var gouvernement = Gouvernement(k, v)

                    if (gouvernement.value.contains(item) && gouvernement.id == k && !_listOfCites2.contains(gouvernement)) {
                        gouvernement.data
                        _listOfCites2.add(gouvernement)


                    }
                }
                //Log.println(Log.ASSERT,"================>2", sortedListOfCities.toString())
                adapter.notifyDataSetChanged()



            }

    }
    fun NextBtn(){
        var loadingAlert=  LoadingAlert(this)
        loadingAlert.startLoadingAlert()
        var image : Uri?
        var phone = intent.getStringExtra("phone").toString()
        image = try {
            Uri.parse(intent.getStringExtra("image"))

        } catch (e : java.lang.NullPointerException){
            null
        }

        var username = intent.getStringExtra("username").toString()
        var password = intent.getStringExtra("password").toString()


        val sharedPref = getSharedPreferences("my_prefs", Context.MODE_PRIVATE)
        val storedValueOfCity = sharedPref.getString("ChosenCity", "default_value")
        val storedValueOfGouv = sharedPref.getString("ChosenGouv", "default_value")
        Log.v("================>name","$storedValueOfCity")
        Log.v("================>name","$storedValueOfGouv")
/*        userVM.updateLocation("", Location(storedValueOfGouv!!,storedValueOfCity!!)).observe(this@SelectLocationActivity,
            Observer {

             loadingAlert.dismissDialog()
            })*/
        val intent = Intent(this, UserTypeActivity::class.java).apply {
            putExtra("ChosenCity",storedValueOfCity)
            putExtra("ChosenGouv",storedValueOfGouv)
            putExtra("phone",phone)
            putExtra("image",image)
            Log.println(Log.ASSERT,"image_1",image.toString())
            putExtra("username",username)
            putExtra("password",password)
        }
        startActivity(intent)
    }
    fun CancelBtn(){
        val intent = Intent(this, SelectLocationActivity::class.java)
        startActivity(intent)
    }
}