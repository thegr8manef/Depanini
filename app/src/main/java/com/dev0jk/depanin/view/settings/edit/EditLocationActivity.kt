package com.dev0jk.depanin.view.settings.edit

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.net.toUri
import androidx.recyclerview.widget.LinearLayoutManager
import com.dev0jk.depanin.R
import com.dev0jk.depanin.databinding.ActivityEditLocationBinding
import com.dev0jk.depanin.databinding.ActivityEditProfileBinding
import com.dev0jk.depanin.model.data.remote.entity.User
import com.dev0jk.depanin.model.entity.Gouvernement
import com.dev0jk.depanin.utils.LoadingAlert
import com.dev0jk.depanin.utils.getUser
import com.dev0jk.depanin.utils.setUser
import com.dev0jk.depanin.view.GouvernementAdapter
import com.dev0jk.depanin.view.SelectLocationActivity
import com.dev0jk.depanin.view.UserTypeActivity
import com.dev0jk.depanin.view.home.HomeActivity
import com.dev0jk.depanin.vm.UserVM
import com.dev0jk.depanin.vm.WorkerVM
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import org.json.JSONArray
import java.io.IOException
import java.io.InputStream

class EditLocationActivity : AppCompatActivity() {

    lateinit var binding : ActivityEditLocationBinding
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
        binding = ActivityEditLocationBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        userVM = UserVM()
        workerVM = WorkerVM()
        readJson()
        dropDownList()
        binding.backBtn.setOnClickListener {
            onBackPressed()
        }
        adapter = GouvernementAdapter(this, _listOfCites2)
        binding.recyclerViewer.layoutManager = LinearLayoutManager(this)
        binding.recyclerViewer.adapter = adapter

        binding.getStarted.setOnClickListener {
            if (adapter.oneTap) {
                NextBtn()
            }
            else{
                Toast.makeText(this,getString(R.string.select_your_town_please), Toast.LENGTH_LONG).show()
            }
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
        //var image = intent.getStringExtra("image")


        val sharedPref = getSharedPreferences("my_prefs", Context.MODE_PRIVATE)
        val storedValueOfCity = sharedPref.getString("ChosenCity", "default_value")
        val storedValueOfGouv = sharedPref.getString("ChosenGouv", "default_value")
        Log.v("================>name","$storedValueOfCity")
        Log.v("================>name","$storedValueOfGouv")
         MainScope().launch {
        workerVM.updateWorkerLocation(getUser(this@EditLocationActivity).id!!,storedValueOfCity!!,storedValueOfGouv!!)
        }
         setUser(User(getUser(this@EditLocationActivity).id!!.toLong(),getUser(this@EditLocationActivity).username,getUser(this@EditLocationActivity).password,storedValueOfCity,storedValueOfGouv,getUser(this@EditLocationActivity).image,getUser(this@EditLocationActivity).phone,getUser(this@EditLocationActivity).cin,getUser(this@EditLocationActivity).speciality,true),this)
         val intent = Intent(this, HomeActivity::class.java)
         startActivity(intent)
    }
    fun CancelBtn(){
        val intent = Intent(this, EditLocationActivity::class.java)
        startActivity(intent)
        /*        _listOfCites2.clear()
        adapter.notifyDataSetChanged()*/
    }
}