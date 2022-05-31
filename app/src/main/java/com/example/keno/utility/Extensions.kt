package com.example.keno.utility


inline fun <A, B> ifNotNull(a: A?, b: B?, callback: (A, B) -> Unit) {
    if (a != null && b != null) {
        callback.invoke(a, b)
    }
}
