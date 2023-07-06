package pcui.beans

import createcode.templatecode.elements.ElementCreator

abstract class Element(
    val id: String, // element的id
    val width: Int?, // 大于等于9999表示充满，其他则直接设置
    val height: Int?, // 大于等于9999表示充满，其他则直接设置
    val paddingTop: Int?,
    val paddingBottom: Int?,
    val paddingStart: Int?,
    val paddingEnd: Int?,
    val backgroundColor: Int?, // 背景颜色
) {
    private var elementCreator: ElementCreator<out Element>? = null

    /**
     * 获取creator
     */
    fun getCreator(): ElementCreator<out Element> {
        if (elementCreator == null) {
            elementCreator = createElementCreator()
        }
        return elementCreator as ElementCreator<out Element>
    }

    /**
     * 模板方法，创建creator
     */
    protected abstract fun createElementCreator(): ElementCreator<out Element>
}

enum class TextWeight {
    W100, W200, W300, W400, W500, W600, W700, W800, W900, Thin, ExtraLight, Light, Normal, Medium, SemiBold, Bold, ExtraBold, Black
}

enum class TextAlign {
    Left, Right, Center, Start, End
}