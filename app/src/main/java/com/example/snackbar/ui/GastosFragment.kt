package com.example.snackbar.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.snackbar.R
import kotlinx.android.synthetic.main.gastos_body.*
import kotlinx.android.synthetic.main.gastos_body.view.*
import java.math.BigDecimal
import java.time.LocalDateTime

class GastosFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view : View =  inflater.inflate(R.layout.fragment_gasto, container, false)

        view.botaoCadastrar.setOnClickListener{
            val descricao = view.descricao.text.toString()
            val categoria = view.categoria.text.toString()
            val data_hora = view.data_hora.text.toString()
            val valor = BigDecimal(view.valor.text.toString())

        }

        return view
    }

    companion object{
        fun newInstance() = GastosFragment()
    }
}