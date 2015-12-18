package com.benny.app.sample.viewcomponent

import android.app.Activity
import android.graphics.drawable.Drawable
import android.view.ViewGroup
import com.benny.app.sample.R
import com.benny.app.sample.converter.StockPriceChangeConverter
import com.benny.app.sample.converter.StockPriceConverter
import com.benny.app.sample.converter.TagBackgroundConverter
import org.jetbrains.anko.*

import com.benny.library.neobinding.view.ViewBinderComponent
import com.benny.library.neobinding.converter.OneWayConverter
import com.benny.library.neobinding.extension.*

/**
 * Created by benny on 12/17/15.
 */

class TagTextWidgetView(val textSize: Float, val colorResource: Int, val horizontalPadding: Int, val verticalPadding: Int, val backgroundConverter: OneWayConverter<Drawable>) : ViewBinderComponent<Activity> {
    override fun builder(): AnkoContext<*>.() -> Unit = {
        textView {
            this.textSize = textSize
            textColorResource = colorResource
            horizontalPadding = dip(this@TagTextWidgetView.horizontalPadding)
            verticalPadding = dip(this@TagTextWidgetView.verticalPadding)
            bind { background("tag", converter = backgroundConverter) }
            bind { text("tag") }
        }
    }
}

class SrockWidgetView(val highlight: Boolean = true) : ViewBinderComponent<Activity> {
    val symbolConverter = object : OneWayConverter<CharSequence> {
        override fun convert(source: Any?): CharSequence = if(source == null) "" else "(" + source.toString() + ")"
    }

    override fun builder(): AnkoContext<*>.() -> Unit = {
        linearLayout {
            inflate(TagTextWidgetView(9f, R.color.color_e6, 2, 0, TagBackgroundConverter(ctx)), this@linearLayout)
            textView {
                textSize = 12f
                textColorResource = R.color.color_6
                horizontalPadding = dip(2)
                bind { text("name") }
            }
            textView {
                textSize = 12f
                textColorResource = R.color.color_6
                horizontalPadding = dip(2)
                bind { text("symbol", converter = symbolConverter) }
            }
            textView {
                textSize = 12f
                textColorResource = R.color.color_6
                horizontalPadding = dip(2)
                bind { text("price", converter = StockPriceConverter()) }
            }
            textView {
                textSize = 12f
                textColorResource = R.color.color_6
                horizontalPadding = dip(2)
                bind { text("changePercent", converter = StockPriceChangeConverter()) }
            }
        }.layoutParams = ViewGroup.LayoutParams(matchParent, dip(50))
    }
}



