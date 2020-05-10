package ro.smg.exchangerates.util

import android.net.Uri
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import ro.smg.exchangerates.R

/**
 * Extension function for ImageView element
 */
fun ImageView.loadImage(
    uri: Uri? = null,
    resource: Int? = null,
    placeholderRes: Int
) = Glide.with(context)
    .load(uri ?: resource)
    .placeholder(placeholderRes)
    .fitCenter()
    .error(placeholderRes)
    .into(this)


/**
 * Display account pic if there is no user logged in
 */
@BindingAdapter("android:imageUrl")
fun loadImage(view: ImageView, url: Uri?) {
    view.loadImage(uri = url, placeholderRes = R.drawable.ic_account)
}


/**
 * Display currency pic if it is available
 */
@BindingAdapter("android:imageRes")
fun loadImage(view: ImageView, resource: Int?) {
    view.loadImage(resource = resource, placeholderRes = R.drawable.ic_info)
}