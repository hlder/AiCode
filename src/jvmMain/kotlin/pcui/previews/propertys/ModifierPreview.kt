package pcui.previews.propertys

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import pcui.beans.Element
import pcui.beans.elements.ColumnElement
import pcui.beans.elements.LayoutElement
import pcui.beans.elements.RowElement

class ModifierPreview(private val element: Element) {
    @Composable
    fun getModifier(): Modifier {
        val widthModifier = element.width?.let {
            if (it >= Int.MAX_VALUE) {
                Modifier.fillMaxWidth()
            } else {
                Modifier.width(it.dp)
            }
        } ?: Modifier

        val heightModifier = element.height?.let {
            if (it >= Int.MAX_VALUE) {
                Modifier.fillMaxHeight()
            } else {
                Modifier.height(it.dp)
            }
        } ?: Modifier.wrapContentHeight()

        val backGroundModifier = element.backgroundColor?.let {
            val roundedCornerShape = RoundedCornerShape(
                topStart = element.backgroundRoundTopLeft?.dp?:0.dp,
                topEnd = element.backgroundRoundTopRight?.dp?:0.dp,
                bottomStart = element.backgroundRoundBottomLeft?.dp?:0.dp,
                bottomEnd = element.backgroundRoundBottomRight?.dp?:0.dp,
            )
            Modifier.background(color = it, roundedCornerShape)
        } ?: Modifier

        val paddingModifier = Modifier.padding(
            top = element.paddingTop?.dp ?: 0.dp,
            bottom = element.paddingBottom?.dp ?: 0.dp,
            start = element.paddingStart?.dp ?: 0.dp,
            end = element.paddingEnd?.dp ?: 0.dp
        )

        val scrollModifier = if (element is LayoutElement && element.isNeedScroll == true) {
            when (element) {
                is ColumnElement -> {
                    Modifier.verticalScroll(rememberScrollState())
                }
                is RowElement -> {
                    Modifier.horizontalScroll(rememberScrollState())
                }
                else -> {
                    Modifier
                }
            }
        } else {
            Modifier
        }
        return Modifier
            .then(widthModifier)
            .then(heightModifier)
            .then(backGroundModifier)
            .then(paddingModifier)
            .then(scrollModifier)
    }
}
