package pcui.beans.elements

import androidx.compose.ui.graphics.Color
import createcode.templatecode.elements.ElementCreator
import createcode.templatecode.elements.TextFieldCreator
import pcui.beans.Element
import androidx.compose.ui.text.style.TextAlign
import pcui.main.PageMainViewModel
import pcui.previews.ElementPreview
import pcui.previews.TextFieldPreview

open class TextFieldElement(
    id: String,
    width: Int? = null,
    height: Int? = null,
    paddingTop: Int? = null,
    paddingBottom: Int? = null,
    paddingStart: Int? = null,
    paddingEnd: Int? = null,
    backgroundColor: Color? = null, // 背景颜色
    backgroundRoundTopLeft: Int? = null, // 背景的圆角左上角
    backgroundRoundTopRight: Int? = null, // 背景的圆角右上角
    backgroundRoundBottomLeft: Int? = null, // 背景的圆角左下角
    backgroundRoundBottomRight: Int? = null, // 背景的圆角右下角
    weight: Float? = null,
    val text: String? = null, // 文本内容
    val hintText: String? = null, // 提示文字
    val textColor: Color? = null, //字体颜色
    val textSize: Int? = null, // 字体大小，dp
    val textWeight: TextWeight? = null, // 字体的粗细
    val textAlign: TextAlign? = null // 文字的对齐方式
) : Element(
    id,
    width,
    height,
    paddingTop,
    paddingBottom,
    paddingStart,
    paddingEnd,
    backgroundColor,
    backgroundRoundTopLeft,
    backgroundRoundTopRight,
    backgroundRoundBottomLeft,
    backgroundRoundBottomRight,
    weight
) {
    override fun createElementCreator(space: String): ElementCreator<out Element> =
        TextFieldCreator(this, space)

    override fun createElementPreview(viewModel: PageMainViewModel): ElementPreview<out Element> =
        TextFieldPreview(this, viewModel)

    override fun getElementName(): String = "输入框"

    companion object {
        fun new(): TextFieldElement {
            return TextFieldElement("TextField" + getBaseId())
        }
    }
}
