package com.example.snackbar.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.example.snackbar.R
import com.example.snackbar.`interface`.ContractMainActivity
import com.example.snackbar.domain.Usuario
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.tab_entrada.*
import kotlinx.android.synthetic.main.tab_gastos.*
import kotlinx.android.synthetic.main.tab_home.*

class MainActivity : AppCompatActivity(), ContractMainActivity {

    val TAG: String = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Adiciona o toolbar
        setSupportActionBar(findViewById(R.id.homeToolbar))

        //Comeca com o fragment entrada setado
        alterColorEntrada(R.color.colorSelected)
        callFragEntrada()

        //Evento click tab Home
        tabHame.setOnClickListener {
            //Seleciona a tab home
            alterColorEntrada(R.color.colorWhite)
            alterColorHome(R.color.colorSelected)
            alterColorGasto(R.color.colorWhite)
            callFragHome()
        }

        //Evento click tab Entrada
        tabEntrada.setOnClickListener {
            alterColorEntrada(R.color.colorSelected)
            alterColorHome(R.color.colorWhite)
            alterColorGasto(R.color.colorWhite)
            callFragEntrada()
        }

        //Evento click tab Gastos
        tabGastos.setOnClickListener {
            alterColorEntrada(R.color.colorWhite)
            alterColorHome(R.color.colorWhite)
            alterColorGasto(R.color.colorSelected)
            callFragGastos()
        }

        var usuario = intent.getSerializableExtra("key") as? Usuario
        showToast("Bem Vindo ${usuario!!.userName}")
        Log.i(TAG, usuario.toString())



    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.profile -> {
                callProfile()
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }


    fun alterColorHome(idCor: Int) {
        llLinhaTabHome.setBackgroundColor(ContextCompat.getColor(this, idCor));
        tvTabHome.setTextColor(ContextCompat.getColor(this, idCor));
        icTabHome.setColorFilter(ContextCompat.getColor(this, idCor));
    }

    //Coloca o fragment Home na pilha
    fun callFragHome() {
        val fragHome = HomeFragment.newInstance()
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.flFragment, fragHome)
            commit()
        }
    }

    fun alterColorEntrada(idCor: Int) {
        llLinhaTabEntrada.setBackgroundColor(ContextCompat.getColor(this, idCor));
        tvTabEntrada.setTextColor(ContextCompat.getColor(this, idCor));
        icTabEntrada.setColorFilter(ContextCompat.getColor(this, idCor));
    }

    //Coloca o fragment Entrada na pilha
    fun callFragEntrada() {
        val fragEntrada = EntradaFragment.newInstance()
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.flFragment, fragEntrada)
            commit()
        }
    }

    fun alterColorGasto(idCor: Int) {
        llLinhaTabGasto.setBackgroundColor(ContextCompat.getColor(this, idCor));
        tvTabGasto.setTextColor(ContextCompat.getColor(this, idCor));
        icTabGasto.setColorFilter(ContextCompat.getColor(this, idCor));
    }

    //Coloca o fragment Gastos na pilha
    fun callFragGastos() {
        val fragGasto = GastosFragment.newInstance()
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.flFragment, fragGasto)
            commit()
        }
    }

    //Coloca o fragment Details Gastos na pilha
    override fun callFragDetailGastos() {
        val fragDetailGastos = DetailGastosFragment.newInstance(1)
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.flFragDetails, fragDetailGastos)
            commit()
        }
    }

    fun showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
    }

    fun callProfile(){
        var intent = Intent(this, ProfileActivity::class.java)
        intent.putExtra("bundle", Bundle().apply {
            putString("nome", "")
            putInt("idade", 0)
            putInt("peso", 0)
            putString("status", "Solteiro")
        })

        val bundle:Bundle?=intent.extras
        if (bundle != null) {
            val nome = bundle.getString("nome")
            val peso = bundle.getDouble("peso")
            val idade = bundle.getInt("idade")
            val titular = bundle.getBoolean("titular")
            Toast.makeText(this, bundle.toString(), Toast.LENGTH_SHORT).show() }

        startActivity(intent)
    }

}