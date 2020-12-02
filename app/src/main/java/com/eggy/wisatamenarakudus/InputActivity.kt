package com.eggy.wisatamenarakudus

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.eggy.wisatamenarakudus.model.DataItem
import com.eggy.wisatamenarakudus.model.ResponseAction
import com.eggy.wisatamenarakudus.networking.NetworkModule
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_input.*
import kotlinx.android.synthetic.main.item_pengunjung.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*

class InputActivity : AppCompatActivity(), DatePickerFragment.DialogDateListener {

    companion object{
        const val DATA ="data"
        const val UPDATE_ID = "UPDATE"
        private const val DATE_PICKER_TAG = "DatePicker"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_input)



        val data = intent.getParcelableExtra<DataItem>(DATA)

        if (data != null){
            edt_nama.setText(data.nama)
            edt_nohp.setText(data.nohp)
            edt_asal_kota.setText(data.asalKota)
            edt_alamat.setText(data.alamat)
            edt_tanggal.setText(data.tanggalKunjungan)

            btn_save.text = UPDATE_ID
        }

        when(btn_save.text){
            UPDATE_ID ->{
                btn_save.setOnClickListener {

                    updateData(data?.id, edt_nama.text.toString(), edt_nohp.text.toString(), edt_asal_kota.text.toString(), edt_alamat.text.toString(), edt_tanggal.text.toString() )
                }
            }
            else ->{
                btn_save.setOnClickListener {
                    insertData(edt_nama.text.toString(), edt_nohp.text.toString(), edt_asal_kota.text.toString(),edt_alamat.text.toString(), edt_tanggal.text.toString())
                }
            }
        }



        btn_cancel.setOnClickListener { finish() }
        btn_tanggal.setOnClickListener {
            val datePickerFragment = DatePickerFragment()
            datePickerFragment.show(supportFragmentManager, DATE_PICKER_TAG )
        }


    }


    private fun insertData(nama:String?, noHp:String?,asal_kota:String?, alamat:String?, tanggal:String?){
        NetworkModule.getRetrofit().insertData( nama?:"", noHp?:"", asal_kota?:"", alamat?:"", tanggal?:"")
            .enqueue(object : Callback<ResponseAction>{
                override fun onResponse(
                    call: Call<ResponseAction>,
                    response: Response<ResponseAction>
                ) {
                    Snackbar.make(findViewById(R.id.input_view), "Data berhasil disimopan", Snackbar.LENGTH_LONG).show()
                    finish()
                }

                override fun onFailure(call: Call<ResponseAction>, t: Throwable) {
                    Log.e("err",t.message.toString(), )
                    Snackbar.make(findViewById(R.id.input_view), t.message.toString(), Snackbar.LENGTH_LONG).show()
                }

            })
    }


    private fun updateData(id:String?, nama:String?, noHp:String?,asal_kota:String?, alamat:String?, tanggal:String?){
        NetworkModule.getRetrofit().updateData(id ?:"", nama?:"", noHp?:"", asal_kota?:"", alamat?:"", tanggal?:"")
            .enqueue(object : Callback<ResponseAction>{
                override fun onResponse(
                    call: Call<ResponseAction>,
                    response: Response<ResponseAction>
                ) {
                    Snackbar.make(findViewById(R.id.input_view), "Data berhasil diubah", Snackbar.LENGTH_LONG).show()
                    finish()
                }

                override fun onFailure(call: Call<ResponseAction>, t: Throwable) {
                    Log.e("err",t.message.toString(), )
                    Snackbar.make(findViewById(R.id.input_view), t.message.toString(), Snackbar.LENGTH_LONG).show()
                }

            })
    }

    override fun onDialogDateSet(year: Int, month: Int, dayOfMonth: Int) {
        val calendar = Calendar.getInstance()
        calendar.set(year, month, dayOfMonth)
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val dateString= dateFormat.format(calendar.time)

        edt_tanggal.setText(dateString)
    }

}