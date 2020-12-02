package com.eggy.wisatamenarakudus

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.eggy.wisatamenarakudus.adapter.VisitorAdapter
import com.eggy.wisatamenarakudus.model.DataItem
import com.eggy.wisatamenarakudus.model.ResponseAction
import com.eggy.wisatamenarakudus.model.ResponseWisata
import com.eggy.wisatamenarakudus.networking.NetworkModule
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        showAll()

        btn_add.setOnClickListener {
            startActivity(Intent(this, InputActivity::class.java))
        }


    }


    private fun showAll(){
        NetworkModule.getRetrofit().getData().enqueue(object : Callback<ResponseWisata>{
            override fun onResponse(
                call: Call<ResponseWisata>,
                response: Response<ResponseWisata>
            ) {
                if (response.isSuccessful){
                    bindItem(response.body()?.data)
                }
            }

            override fun onFailure(call: Call<ResponseWisata>, t: Throwable) {
                Log.e("err",t.message.toString(), )
                Snackbar.make(findViewById(R.id.main_view), t.message.toString(), Snackbar.LENGTH_LONG).show()
            }
        })
    }

    fun deleteData(id:String?){
        NetworkModule.getRetrofit().deleteData(id ?:"").enqueue(object : Callback<ResponseAction>{
            override fun onResponse(
                call: Call<ResponseAction>,
                response: Response<ResponseAction>
            ) {
                Snackbar.make(findViewById(R.id.main_view), "Data berhasil dihapus", Snackbar.LENGTH_LONG).show()
                showAll()
            }

            override fun onFailure(call: Call<ResponseAction>, t: Throwable) {
                Log.e("err",t.message.toString(), )
                Snackbar.make(findViewById(R.id.main_view), t.message.toString(), Snackbar.LENGTH_LONG).show()

            }

        })
    }
    private fun bindItem(item: List<DataItem?>?){
        list_pengunjung.setHasFixedSize(true)
        list_pengunjung.adapter = VisitorAdapter(item, object : VisitorAdapter.OnClickListener{
            override fun detail(item: DataItem?) {

                val intent = Intent(this@MainActivity, InputActivity::class.java)
                intent.putExtra(InputActivity.DATA, item)
                startActivity(intent)

            }

            override fun delete(item: DataItem?) {

                AlertDialog.Builder(this@MainActivity).apply {
                    setTitle("Hapus")
                    setMessage("Apakah anda yakin menghapus data ini?")
                    setPositiveButton("Ya"){dialog, _->
                        deleteData(item?.id)
                        dialog.dismiss()
                    }
                    setNegativeButton("Tidak"){dialog, _->
                        dialog.dismiss()

                    }
                }.show()

            }

        })
    }

    override fun onResume() {
        super.onResume()
        showAll()
    }
}