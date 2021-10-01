package com.zb.common.utils.graphics;


import android.content.Intent;
import android.graphics.BlendMode;
import android.graphics.Color;
import android.graphics.ColorSpace;
import android.graphics.Paint;
import android.os.Build;
import android.os.Bundle;
import android.os.LocaleList;

import androidx.annotation.ColorInt;
import androidx.annotation.ColorLong;
import androidx.annotation.NonNull;
import androidx.annotation.Px;
import androidx.annotation.RequiresApi;
import androidx.annotation.Size;
import java.util.Locale;

public class PaintUtil {
    private static volatile Paint paint;


    private static synchronized Paint getPaint() {
        if(null == paint) {
            synchronized (PaintUtil.class) {
                if(null == paint)
                    paint = new Paint();
                paint.reset();
            }
        }
        return paint;
    }

    public static class Builder {

        private Paint paint;

        public Builder() {
            paint = new Paint();
        }
        public Paint build() {
            return paint;
        }

        public Builder(Paint paint) {
            if(null == paint)
                throw new IllegalArgumentException("Paint cant be null");
            this.paint = paint;
        }


        public Builder set(Paint src) {
            paint.set(src);
            return this;
        }


        /**
         * Set the paint's flags. Use the Flag enum to specific flag values.
         *
         * @param flags The new flag bits for the paint
         */
        public Builder setFlags(int flags) {
            paint.setFlags(flags);
            return this;
        }
        /**
         * Set the paint's hinting mode.  May be either
         */
        public Builder setHinting(int mode) {
            paint.setHinting(mode);
            return this;
        }
        /**
         * Helper for setFlags(), setting or clearing the ANTI_ALIAS_FLAG bit
         * AntiAliasing smooths out the edges of what is being drawn, but is has
         * no impact on the interior of the shape. See setDither() and
         * setFilterBitmap() to affect how colors are treated.
         *
         * @param aa true to set the antialias bit in the flags, false to clear it
         */
        public Builder setAntiAlias(boolean aa) {
            paint.setAntiAlias(aa);
            return this;
        }
        /**
         * Helper for setFlags(), setting or clearing the DITHER_FLAG bit
         * Dithering affects how colors that are higher precision than the device
         * are down-sampled. No dithering is generally faster, but higher precision
         * colors are just truncated down (e.g. 8888 -> 565). Dithering tries to
         * distribute the error inherent in this process, to reduce the visual
         * artifacts.
         *
         * @param dither true to set the dithering bit in flags, false to clear it
         */
        public Builder setDither(boolean dither) {
            paint.setDither(dither);
            return this;
        }
        /**
         * Helper for setFlags(), setting or clearing the LINEAR_TEXT_FLAG bit
         *
         * @param linearText true to set the linearText bit in the paint's flags,
         *                   false to clear it.
         */
        public Builder setLinearText(boolean linearText) {
            paint.setLinearText(linearText);
            return this;
        }
        /**
         * Helper for setFlags(), setting or clearing the SUBPIXEL_TEXT_FLAG bit
         *
         * @param subpixelText true to set the subpixelText bit in the paint's
         *                     flags, false to clear it.
         */
        public Builder setSubpixelText(boolean subpixelText) {
            paint.setSubpixelText(subpixelText);
            return this;
        }
        /**
         * Helper for setFlags(), setting or clearing the UNDERLINE_TEXT_FLAG bit
         *
         * @param underlineText true to set the underlineText bit in the paint's
         *                      flags, false to clear it.
         */
        public Builder setUnderlineText(boolean underlineText) {
            paint.setUnderlineText(underlineText);
            return this;
        }

        /**
         * Helper for setFlags(), setting or clearing the STRIKE_THRU_TEXT_FLAG bit
         *
         * @param strikeThruText true to set the strikeThruText bit in the paint's
         *                       flags, false to clear it.
         */
        public Builder setStrikeThruText(boolean strikeThruText) {
            paint.setStrikeThruText(strikeThruText);
            return this;
        }

              /**
         * Helper for setFlags(), setting or clearing the FAKE_BOLD_TEXT_FLAG bit
         *
         * @param fakeBoldText true to set the fakeBoldText bit in the paint's
         *                     flags, false to clear it.
         */
        public Builder setFakeBoldText(boolean fakeBoldText) {
            paint.setFakeBoldText(fakeBoldText);
            return this;
        }


        /**
         * Helper for setFlags(), setting or clearing the FILTER_BITMAP_FLAG bit.
         * Filtering affects the sampling of bitmaps when they are transformed.
         * Filtering does not affect how the colors in the bitmap are converted into
         * device pixels. That is dependent on dithering and xfermodes.
         *
         * @param filter true to set the FILTER_BITMAP_FLAG bit in the paint's
         *               flags, false to clear it.
         */
        public Builder setFilterBitmap(boolean filter) {
            paint.setFilterBitmap(filter);
            return this;
        }



        /**
         * Set the paint's style, used for controlling how primitives'
         * geometries are interpreted (except for drawBitmap, which always assumes
         * Fill).
         *
         * @param style The new style to set in the paint
         */
        public Builder setStyle(Paint.Style style) {
            paint.setStyle(style);
            return this;
        }


        /**
         * Set the paint's color. Note that the color is an int containing alpha
         * as well as r,g,b. This 32bit value is not premultiplied, meaning that
         * its alpha can be any value, regardless of the values of r,g,b.
         * See the Color class for more details.
         *
         * @param color The new color (including alpha) to set in the paint.
         */
        public Builder setColor(@ColorInt int color) {
            paint.setColor(color);
            return this;
        }

        /**
         * Set the paint's color with a {@code ColorLong}. Note that the color is
         * a long with an encoded {@link ColorSpace} as well as alpha and r,g,b.
         * These values are not premultiplied, meaning that alpha can be any value,
         * regardless of the values of r,g,b. See the {@link Color} class for more
         * details.
         *
         * @param color The new color (including alpha and {@link ColorSpace})
         *      to set in the paint.
         * @throws IllegalArgumentException if the color space encoded in the
         *      {@code ColorLong} is invalid or unknown.
         */
        @RequiresApi(api = Build.VERSION_CODES.Q)
        public Builder setColor(@ColorLong long color) {
            paint.setColor(color);
            return this;
        }


        /**
         * Helper to setColor(), that only assigns the color's alpha value,
         * leaving its r,g,b values unchanged. Results are undefined if the alpha
         * value is outside of the range [0..255]
         *
         * @param a set the alpha component [0..255] of the paint's color.
         */
        public Builder setAlpha(int a) {
            paint.setAlpha(a);
            return this;
        }

        /**
         * Helper to setColor(), that takes a,r,g,b and constructs the color int
         *
         * @param a The new alpha component (0..255) of the paint's color.
         * @param r The new red component (0..255) of the paint's color.
         * @param g The new green component (0..255) of the paint's color.
         * @param b The new blue component (0..255) of the paint's color.
         */
        public Builder setARGB(int a, int r, int g, int b) {
            paint.setARGB(a, r, g, b);
            return this;
        }


        /**
         * Set the width for stroking.
         * Pass 0 to stroke in hairline mode.
         * Hairlines always draws a single pixel independent of the canvas's matrix.
         *
         * @param width set the paint's stroke width, used whenever the paint's
         *              style is Stroke or StrokeAndFill.
         */
        public Builder setStrokeWidth(float width) {
            paint.setStrokeWidth(width);
            return this;
        }


        /**
         * Set the paint's stroke miter value. This is used to control the behavior
         * of miter joins when the joins angle is sharp. This value must be >= 0.
         *
         * @param miter set the miter limit on the paint, used whenever the paint's
         *              style is Stroke or StrokeAndFill.
         */
        public Builder setStrokeMiter(float miter) {
            paint.setStrokeMiter(miter);
            return this;
        }

        /**
         * Set the paint's Cap.
         *
         * @param cap set the paint's line cap style, used whenever the paint's
         *            style is Stroke or StrokeAndFill.
         */
        public Builder setStrokeCap(Paint.Cap cap) {
            paint.setStrokeCap(cap);
            return this;
        }

        /**
         * Set the paint's Join.
         *
         * @param join set the paint's Join, used whenever the paint's style is
         *             Stroke or StrokeAndFill.
         */
        public Builder setStrokeJoin(Paint.Join join) {
            paint.setStrokeJoin(join);
            return this;
        }






        /**
         * Set or clear the blend mode. A blend mode defines how source pixels
         * (generated by a drawing command) are composited with the destination pixels
         * (content of the render target).
         * <p />
         * Pass null to clear any previous blend mode.
         * <p />
         *
         * @see BlendMode
         *
         * @param blendmode May be null. The blend mode to be installed in the paint
         */
        @RequiresApi(api = Build.VERSION_CODES.Q)
        public Builder setBlendMode(@org.jetbrains.annotations.Nullable BlendMode blendmode) {
            paint.setBlendMode(blendmode);
            return this;
        }





        /**
         * This draws a shadow layer below the main layer, with the specified
         * offset and color, and blur radius. If radius is 0, then the shadow
         * layer is removed.
         * <p>
         * Can be used to create a blurred shadow underneath text. Support for use
         * with other drawing operations is constrained to the software rendering
         * pipeline.
         * <p>
         * The alpha of the shadow will be the paint's alpha if the shadow color is
         * opaque, or the alpha from the shadow color if not.
         */
        public Builder setShadowLayer(float radius, float dx, float dy, @ColorInt int shadowColor) {
            paint.setShadowLayer(radius, dx, dy, shadowColor);
            return this;
        }

        /**
         * This draws a shadow layer below the main layer, with the specified
         * offset and color, and blur radius. If radius is 0, then the shadow
         * layer is removed.
         * <p>
         * Can be used to create a blurred shadow underneath text. Support for use
         * with other drawing operations is constrained to the software rendering
         * pipeline.
         * <p>
         * The alpha of the shadow will be the paint's alpha if the shadow color is
         * opaque, or the alpha from the shadow color if not.
         *
         * @throws IllegalArgumentException if the color space encoded in the
         *      {@code ColorLong} is invalid or unknown.
         */
        @RequiresApi(api = Build.VERSION_CODES.Q)
        public Builder setShadowLayer(float radius, float dx, float dy, @ColorLong long shadowColor) {
            paint.setShadowLayer(radius, dx, dy, shadowColor);
            return this;
        }

        /**
         * Clear the shadow layer.
         */
        public Builder clearShadowLayer() {
            paint.clearShadowLayer();
            return this;
        }


        /**
         * Set the paint's text alignment. This controls how the
         * text is positioned relative to its origin. LEFT align means that all of
         * the text will be drawn to the right of its origin (i.e. the origin
         * specifieds the LEFT edge of the text) and so on.
         *
         * @param align set the paint's Align value for drawing text.
         */
        public Builder setTextAlign(Paint.Align align) {
            paint.setTextAlign(align);
            return this;
        }



        /**
         * Set the text locale list to a one-member list consisting of just the locale.
         *
         * See {@link #setTextLocales(LocaleList)} for how the locale list affects
         * the way the text is drawn for some languages.
         *
         * @param locale the paint's locale value for drawing text, must not be null.
         */
        public Builder setTextLocale(@NonNull Locale locale) {
            paint.setTextLocale(locale);
            return this;
        }

        /**
         * Set the text locale list.
         *
         * The text locale list affects how the text is drawn for some languages.
         *
         * For example, if the locale list contains {@link Locale#CHINESE} or {@link Locale#CHINA},
         * then the text renderer will prefer to draw text using a Chinese font. Likewise,
         * if the locale list contains {@link Locale#JAPANESE} or {@link Locale#JAPAN}, then the text
         * renderer will prefer to draw text using a Japanese font. If the locale list contains both,
         * the order those locales appear in the list is considered for deciding the font.
         *
         * This distinction is important because Chinese and Japanese text both use many
         * of the same Unicode code points but their appearance is subtly different for
         * each language.
         *
         * By default, the text locale list is initialized to a one-member list just containing the
         * system locales. This assumes that the text to be rendered will most likely be in the user's
         * preferred language.
         *
         * If the actual language or languages of the text is/are known, then they can be provided to
         * the text renderer using this method. The text renderer may attempt to guess the
         * language script based on the contents of the text to be drawn independent of
         * the text locale here. Specifying the text locales just helps it do a better
         * job in certain ambiguous cases.
         *
         * @param locales the paint's locale list for drawing text, must not be null or empty.
         */
        @RequiresApi(api = Build.VERSION_CODES.N)
        public Builder setTextLocales(@NonNull @Size(min=1) LocaleList locales) {
            paint.setTextLocales(locales);
            return this;
        }



        /**
         * Set the paint's elegant height metrics flag. This setting selects font
         * variants that have not been compacted to fit Latin-based vertical
         * metrics, and also increases top and bottom bounds to provide more space.
         *
         * @param elegant set the paint's elegant metrics flag for drawing text.
         */
        public Builder setElegantTextHeight(boolean elegant) {
            paint.setElegantTextHeight(elegant);
            return this;
        }

               /**
         * Set the paint's text size. This value must be > 0
         *
         * @param textSize set the paint's text size in pixel units.
         */
        public Builder setTextSize(float textSize) {
            paint.setTextSize(textSize);
            return this;
        }


        /**
         * Set the paint's horizontal scale factor for text. The default value
         * is 1.0. Values > 1.0 will stretch the text wider. Values < 1.0 will
         * stretch the text narrower.
         *
         * @param scaleX set the paint's scale in X for drawing/measuring text.
         */
        public Builder setTextScaleX(float scaleX) {
            paint.setTextScaleX(scaleX);
            return this;
        }


        /**
         * Set the paint's horizontal skew factor for text. The default value
         * is 0. For approximating oblique text, use values around -0.25.
         *
         * @param skewX set the paint's skew factor in X for drawing text.
         */
        public Builder setTextSkewX(float skewX) {
            paint.setTextSkewX(skewX);
            return this;
        }


        /**
         * Set the paint's letter-spacing for text. The default value
         * is 0.  The value is in 'EM' units.  Typical values for slight
         * expansion will be around 0.05.  Negative values tighten text.
         *
         * @param letterSpacing set the paint's letter-spacing for drawing text.
         */
        public Builder setLetterSpacing(float letterSpacing) {
            paint.setLetterSpacing(letterSpacing);
            return this;
        }
     /**
         * Set the paint's extra word-spacing for text.
         *
         * Increases the white space width between words with the given amount of pixels.
         * The default value is 0.
         *
         * @param wordSpacing set the paint's extra word-spacing for drawing text in pixels.
         */
        @RequiresApi(api = Build.VERSION_CODES.Q)
        public Builder setWordSpacing(@Px float wordSpacing) {
            paint.setWordSpacing(wordSpacing);
            return this;
        }


        /**
         * Set font feature settings.
         *
         * The format is the same as the CSS font-feature-settings attribute:
         * <a href="https://www.w3.org/TR/css-fonts-3/#font-feature-settings-prop">
         *     https://www.w3.org/TR/css-fonts-3/#font-feature-settings-prop</a>
         *
         *
         * @param settings the font feature settings string to use, may be null.
         */
        public Builder setFontFeatureSettings(String settings) {
            paint.setFontFeatureSettings(settings);
            return this;
        }








    }
}
