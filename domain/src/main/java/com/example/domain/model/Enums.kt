package com.example.domain.model

enum class Theme(val darkVariant: Boolean? = null) {
    System,
    Light(false),
    Dark(true)
}

data class Languages(
    val name: String,
    val code: String
)