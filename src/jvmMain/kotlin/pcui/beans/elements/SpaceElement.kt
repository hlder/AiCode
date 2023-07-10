package pcui.beans.elements

import androidx.compose.ui.graphics.Color
import createcode.templatecode.elements.ElementCreator
import createcode.templatecode.elements.SpaceCreator
import pcui.beans.Element
import pcui.previews.ElementPreview
import pcui.previews.SpacePreview

class SpaceElement(
    id: String,
    width: Int? = null,
    height: Int? = null,
    paddingTop: Int? = null,
    paddingBottom: Int? = null,
    paddingStart: Int? = null,
    paddingEnd: Int? = null,
    backgroundColor: Color? = null, // 背景颜色
    backgroundRounded: Int? = null, // 背景的圆角
    weight: Float? = null,
) : Element(id, width, height, paddingTop, paddingBottom, paddingStart, paddingEnd, backgroundColor, backgroundRounded, weight) {
    override fun createElementCreator(space: String): ElementCreator<out Element> = SpaceCreator(this, space)
    override fun createElementPreview(): ElementPreview<out Element> = SpacePreview(this)
}
