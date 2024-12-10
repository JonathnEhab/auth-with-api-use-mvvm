package com.example.authenticationwithapi.util

import android.content.Context
import android.widget.EditText
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

const val BASE_URL = "https://reqres.in/"

fun validateFields(
    context: Context,
    vararg fields: Pair<EditText, String>, // بتاخد اي نوع edit text , error massage
    passwordPair: Triple<EditText, EditText, String>? = null // password matching verification
): Boolean {
    var isValid = true
    var ErrorField: EditText? = null
    var ErrorMessage: String? = null

    for ((field, errorMessage) in fields) {
        val value = field.text.toString().trim()

        if (value.isBlank()) {
            ErrorField = field
            ErrorMessage = errorMessage
            isValid = false
            break // نخرج عند أول خطأ
        }
    }
    // التحقق من تطابق كلمة المرور فقط إذا لم نجد خطأ آخر
    if (isValid && passwordPair != null) {
        val (passwordField, confirmPasswordField, mismatchError) = passwordPair
        val password = passwordField.text.toString().trim()
        val confirmPassword = confirmPasswordField.text.toString().trim()

        if (password.isNotEmpty() && confirmPassword.isNotEmpty() && password != confirmPassword) {
            ErrorField = confirmPasswordField
            ErrorMessage = mismatchError
            isValid = false
        }
    }
    // إذا وجدنا خطأ، نعرضه فقط الجزء  الذي به مشكلة
    ErrorField?.let {
        it.error = ErrorMessage
        showToast(context, ErrorMessage ?: "")
        it.requestFocus()
    }
    return isValid
}
fun showToast(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}



