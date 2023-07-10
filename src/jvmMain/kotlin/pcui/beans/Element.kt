package pcui.beans

import androidx.compose.ui.graphics.Color
import createcode.templatecode.elements.ElementCreator
import pcui.beans.elements.LayoutElement
import pcui.previews.ElementPreview

abstract class Element(
    val id: String, // element的id
    val width: Int?, // 大于等于9999表示充满，其他则直接设置
    val height: Int?, // 大于等于9999表示充满，其他则直接设置
    val paddingTop: Int?,
    val paddingBottom: Int?,
    val paddingStart: Int?,
    val paddingEnd: Int?,
    val backgroundColor: Color?, // 背景颜色
    val backgroundRounded:Int?, // 背景的圆角
    val weight: Float?, // 权重，可以设置平均分配
) {
    private var elementCreator: ElementCreator<out Element>? = null

    /**
     * 获取creator
     */
    fun getCreator(space: String): ElementCreator<out Element> {
        if (elementCreator == null) {
            elementCreator = createElementCreator(space)
        }
        return elementCreator as ElementCreator<out Element>
    }

    private var elementPreview: ElementPreview<out Element>? = null

    /**
     * 获取预览
     */
    fun getPreview(): ElementPreview<out Element> {
        if(elementPreview==null){
            elementPreview = createElementPreview()

        }
        return elementPreview as ElementPreview<out Element>
    }

    /**
     * 模板方法，创建creator
     */
    protected abstract fun createElementCreator(space: String): ElementCreator<out Element>

    protected abstract fun createElementPreview(): ElementPreview<out Element>
}

/**
 * 遍历整个element元素树
 */
fun Element.foreach(block: (element: Element) -> Unit) {
    if (this is LayoutElement) {
        this.childs?.forEach {
            it.foreach(block)
        }
    }
    block(this)
}

enum class TextWeight {
    W100, W200, W300, W400, W500, W600, W700, W800, W900, Thin, ExtraLight, Light, Normal, Medium, SemiBold, Bold, ExtraBold, Black
}

enum class TextAlign {
    Left, Right, Center, Start, End
}