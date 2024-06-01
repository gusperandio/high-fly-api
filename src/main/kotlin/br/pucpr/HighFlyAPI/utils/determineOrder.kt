package br.pucpr.HighFlyAPI.utils

import br.pucpr.HighFlyAPI.enums.TypeOrder

fun determineOrder(name: Boolean?, price: Boolean?): TypeOrder {
    return when {
        name == true && price == true -> TypeOrder.PRICE
        price == true -> TypeOrder.PRICE
        else -> TypeOrder.NAME
    }
}