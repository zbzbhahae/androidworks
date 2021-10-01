package com.zb.glide.option;

import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.annotation.GlideExtension;
import com.bumptech.glide.annotation.GlideOption;
import com.bumptech.glide.annotation.GlideType;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.request.BaseRequestOptions;
import com.zb.glide.R;

import jp.wasabeef.glide.transformations.BlurTransformation;

@GlideExtension
public final class MyGlideExtension {
    private MyGlideExtension() {}

    @GlideOption
    public static BaseRequestOptions<?> applyAvatar(BaseRequestOptions<?> options, int size) {
        return options.placeholder(R.mipmap.icon_pic_loading)
                .error(R.mipmap.icon_pic_load_error)
                .override(size)
//                .fitCenter()
                .transform(new BlurTransformation(25, 2), new CircleCrop())
//                .circleCrop()
                ;
//                .centerCrop();
    }

    @GlideOption
    public static BaseRequestOptions<?> applyBlur(BaseRequestOptions<?> requestOptions) {
        return requestOptions.transform(new BlurTransformation(25, 2));
    }

    @GlideType(GifDrawable.class)
    public static RequestBuilder<GifDrawable> asGif2(RequestBuilder<GifDrawable> requestBuilder) {
        return requestBuilder.transition(new DrawableTransitionOptions())
                .apply(GlideOptions.decodeTypeOf(GifDrawable.class).lock());
    }
}
