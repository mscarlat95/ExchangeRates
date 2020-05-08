package ro.smg.exchangerates.util

import android.net.Uri
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import ro.smg.exchangerates.R

/**
 * Extension function for ImageView element
 */
fun ImageView.loadImage(uri: Uri?, placeholderRes: Int) = Glide.with(context)
    .load(uri)
    .placeholder(placeholderRes)
    .fitCenter()
    .error(placeholderRes)
    .into(this)

/**
 * Display account pic if there is no user logged in
 */
@BindingAdapter("android:imageUrl")
fun loadImage(view: ImageView, url: Uri?) {
    view.loadImage(url, R.drawable.ic_account)
}
