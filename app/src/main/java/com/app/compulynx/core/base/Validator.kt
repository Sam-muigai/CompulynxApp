package com.app.compulynx.core.base

object Validator{
    fun validatePin(pin: String): String? {
        return when {
            pin.isEmpty() -> null
            pin.length < 4 -> "Password must be at least 6 characters"
            else -> null
        }
    }

    fun validateCustomerId(customerId: String): String? {
        return when {
            customerId.isEmpty() -> null
            !customerId.startsWith("CUST")-> "Customer ID must start with CUST"
            else -> null
        }
    }

}