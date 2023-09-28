package pcui.beans.elements

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import createcode.templatecode.elements.ElementCreator
import createcode.templatecode.elements.TextCreator
import pcui.beans.Element
import androidx.compose.ui.text.style.TextAlign
import pcui.main.PageMainViewModel
import pcui.previews.ElementPreview
import pcui.previews.TextPreview

open class TextElement(
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
        TextCreator(this, space)

    override fun createElementPreview(viewModel: PageMainViewModel): ElementPreview<out Element> =
        TextPreview(this, viewModel)

    override fun getElementName(): String = "文本"

    companion object {
        fun new(): TextElement {
            return TextElement("text" + getBaseId())
        }
    }
}

enum class TextWeight {
    W100, W200, W300, W400, W500, W600, W700, W800, W900, Thin, ExtraLight, Light, Normal, Medium, SemiBold, Bold, ExtraBold, Black;

    fun getFontWeight(): FontWeight {
        return if (this == W100) {
            FontWeight.W100
        } else if (this == W200) {
            FontWeight.W100
        } else if (this == W300) {
            FontWeight.W300
        } else if (this == W400) {
            FontWeight.W400
        } else if (this == W500) {
            FontWeight.W500
        } else if (this == W600) {
            FontWeight.W600
        } else if (this == W700) {
            FontWeight.W700
        } else if (this == W800) {
            FontWeight.W800
        } else if (this == W900) {
            FontWeight.W900
        } else if (this == Thin) {
            FontWeight.Thin
        } else if (this == ExtraLight) {
            FontWeight.ExtraLight
        } else if (this == Light) {
            FontWeight.Normal
        } else if (this == Normal) {
            FontWeight.Normal
        } else if (this == Medium) {
            FontWeight.Medium
        } else if (this == SemiBold) {
            FontWeight.SemiBold
        } else if (this == Bold) {
            FontWeight.Bold
        } else if (this == ExtraBold) {
            FontWeight.ExtraBold
        } else if (this == Black) {
            FontWeight.Black
        } else {
            FontWeight.W100
        }
    }
}
