package com.zb.jetpack.mvvm.bindingadapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.zb.jetpack.utils.P

open class ImageViewBindingAdapter {

    companion object {
        @JvmStatic
        @BindingAdapter(value = ["imageurl", "default_image"], requireAll = false)
        fun setImage(imageView: ImageView, imageurl: String?, res: Int) {
            P.p("来setImage了 ->$imageurl , ->$res")
            if(imageurl.isNullOrBlank()) {
                if(0 != res)
                    imageView.setImageResource(res)
            } else {
                Glide.with(imageView).load(imageurl).into(imageView)
            }
        }


//        @JvmStatic
//        @BindingAdapter("imageurl")
//        fun setImage(imageView: ImageView, url : String) {
//            if(url.isNullOrBlank())
//                return
//            Glide.with(imageView).load(url).into(imageView)
//
//        }
//
//        @JvmStatic
//        @BindingAdapter("imageurl")
//        fun setImage(imageView: ImageView, resourceId : Int) {
//            if(resourceId == 0)
//                return
//            imageView.setImageResource(resourceId)
//
//        }
    }
}