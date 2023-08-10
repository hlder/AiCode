package pcui.beans

import androidx.compose.ui.graphics.Color
import createcode.templatecode.elements.ElementCreator
import pcui.beans.elements.LayoutElement
import pcui.main.PageMainViewModel
import pcui.previews.ElementPreview
import java.text.SimpleDateFormat
import java.util.Calendar

abstract class Element(
    var id: String, // element的id
    var width: Int?, // 大于等于Int.MAX_VALUE表示充满，其他则直接设置
    var height: Int?, // 大于等于Int.MAX_VALUE表示充满，其他则直接设置
    var paddingTop: Int?,
    var paddingBottom: Int?,
    var paddingStart: Int?,
    var paddingEnd: Int?,
    var backgroundColor: Color?, // 背景颜色
    var backgroundRoundTopLeft: Int?, // 背景的圆角左上角
    var backgroundRoundTopRight: Int?, // 背景的圆角右上角
    var backgroundRoundBottomLeft: Int?, // 背景的圆角左下角
    var backgroundRoundBottomRight: Int?, // 背景的圆角右下角
    var weight: Float?, // 权重，可以设置平均分配
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
    fun getPreview(viewModel: PageMainViewModel): ElementPreview<out Element> {
        if (elementPreview == null) {
            elementPreview = createElementPreview(viewModel)

        }
        return elementPreview as ElementPreview<out Element>
    }

    /**
     * 模板方法，创建creator
     */
    protected abstract fun createElementCreator(space: String): ElementCreator<out Element>

    protected abstract fun createElementPreview(viewModel: PageMainViewModel): ElementPreview<out Element>

    /**
     * 获取元素的名称
     */
    abstract fun getElementName(): String

    companion object{
        private var index = 1

        /**
         * 获取id的公共部分
         */
        fun getBaseId(): String {
            return SimpleDateFormat("yyyyMMddHHmmss").format(Calendar.getInstance().timeInMillis) + (index++)
        }
    }
}

/**
 * 遍历整个element元素树
 */
fun Element.foreach(block: (element: Element) -> Unit) {
    if (this is LayoutElement) {
        this.childs.forEach {
            it.foreach(block)
        }
    }
    block(this)
}

/**
 * 获取持有它的元素
 */
fun Element.getParent(rootElement: Element): LayoutElement? {
    if (rootElement is LayoutElement) {
        var layoutElement: LayoutElement? = null
        rootElement.childs.forEach {
            if (it == this) {
                layoutElement = rootElement
                return rootElement
            }
            if (it is LayoutElement && layoutElement == null) {
                layoutElement = getParent(it)
            }
        }
        return layoutElement
    } else {
        return null
    }
}