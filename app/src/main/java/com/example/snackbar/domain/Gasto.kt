package com.example.snackbar.domain

import java.math.BigDecimal
import java.time.LocalDateTime

data class Gasto (val descricao: String, val categoria: String, val data: String, val valor: BigDecimal) {
}