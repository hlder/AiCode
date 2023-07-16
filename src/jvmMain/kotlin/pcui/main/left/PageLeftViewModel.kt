package pcui.main.left

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import pcui.beans.Element

class PageLeftViewModel {
    var contentPosition: Offset? = null
    val childPosition = mutableListOf<Triple<Element, Offset, Rect>>()

    var downPosition = Offset(0f, 0f)

    fun onTouchDown(x: Float, y: Float): Triple<Element, Offset, Rect>? {
        downPosition = Offset(x, y)
        val contentPosition = this.contentPosition ?: return null
        val item = childPosition.find {
            it.third.contains(Offset(x + contentPosition.x, y + contentPosition.y))
        }
        println("==============onTouchDown:(${x},${y})")
        return item
    }
}