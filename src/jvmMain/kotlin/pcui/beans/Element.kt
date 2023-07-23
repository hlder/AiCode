package pcui.beans

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import createcode.templatecode.elements.ElementCreator
import pcui.beans.elements.LayoutElement
import pcui.main.PageMainViewModel
import pcui.previews.ElementPreview

abstract class Element(
    var id: String, // element的id
    var width: Int?, // 大于等于Int.MAX_VALUE表示充满，其他则直接设置
    var height: Int?, // 大于等于Int.MAX_VALUE表示充满，其他则直接设置
    var paddingTop: Int?,
    var paddingBottom: Int?,
    var paddingStart: Int?,
    var paddingEnd: Int?,
    var backgroundColor: Color?, // 背景颜色
    var backgroundRounded:Int?, // 背景的圆角
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
        if(elementPreview==null){
            elementPreview = createElementPreview(viewModel)

        }
        return elementPreview as ElementPreview<out Element>
    }

    /**
     * 模板方法，创建creator
     */
    protected abstract fun createElementCreator(space: String): ElementCreator<out Element>

    protected abstract fun createElementPreview(viewModel: PageMainViewModel): ElementPreview<out Element>
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