package com.zaitsev.hhforstydent.utils

import android.app.Activity
import android.content.Intent
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso
import com.zaitsev.hhforstydent.R

fun showToast(message: String) {
    Toast.makeText(APP_ACTIVITY, message, Toast.LENGTH_SHORT).show()
}

fun AppCompatActivity.replaceActivity(activity: Activity) {
    val intent = Intent(APP_ACTIVITY, activity::class.java)
    startActivity(intent)
    this.finish()
}

fun Fragment.replaceActivity(activity: Activity) {
    val intent = Intent(this.context, activity::class.java)
    startActivity(intent)
}

fun Fragment.showBaseSnackBar(text: String) {
    Snackbar.make(requireView(), "", Snackbar.LENGTH_LONG).apply {
        val snackView: View = layoutInflater.inflate(R.layout.view_snackbar_text, null)
        snackView.findViewById<TextView>(R.id.textViewSnakeBar).text = text
        (view as Snackbar.SnackbarLayout).apply {
            addView(snackView, 0)
        }
    }.show()
}

fun setImageToImageView(imageUrl: String, imageView: ImageView) {
    if(imageUrl.isNotEmpty()){
        Picasso.get()
            .load(imageUrl)
            .placeholder(R.drawable.ic_image_placeholder)
            .centerCrop()
            .resize(500, 500)
            .into(imageView)
    }
}
