package pcui.beans.elements

import createcode.templatecode.elements.ElementCreator
import createcode.templatecode.elements.SpaceCreator
import pcui.beans.Element

class SpaceElement(
    id: String,
    width: Int? = null,
    height: Int? = null,
    paddingTop: Int? = null,
    paddingBottom: Int? = null,
    paddingStart: Int? = null,
    paddingEnd: Int? = null,
    backgroundColor: Int? = null, // 背景颜色
) : Element(id, width, height, paddingTop, paddingBottom, paddingStart, paddingEnd, backgroundColor) {
    private var spaceCreator: SpaceCreator? = null
    override fun createElementCreator(): ElementCreator<out Element> = spaceCreator ?: SpaceCreator(this).apply {
        spaceCreator = this
    }
}
