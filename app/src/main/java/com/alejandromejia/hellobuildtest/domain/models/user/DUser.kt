package com.alejandromejia.hellobuildtest.domain.models.user

data class DUser(
    val id: Int,
    val name: String,
    val userName: String,
    val email: String,
    val address: DUserAddress,
    val phone: String,
    val website: String,
    val company: DUserCompany,
    val iconColor: Int,
    var isFavorite: Boolean
)
