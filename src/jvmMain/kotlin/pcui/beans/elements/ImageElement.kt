package pcui.beans.elements

import androidx.compose.ui.graphics.Color
import createcode.templatecode.elements.ElementCreator
import createcode.templatecode.elements.ImageCreator
import pcui.beans.Element
import pcui.main.PageMainViewModel
import pcui.previews.ElementPreview
import pcui.previews.ImagePreview

class ImageElement(
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
    val contentDescription: String, // 无障碍的播报内容
    val imageFrom: ImageFrom, // 图片的来源（本地图片还是远程图片）
    val image: String, // 图片,本地图片为图片的id，远程图片则为图片的url
    val filePath: String, // 本地图片的位置
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
        ImageCreator(this, space)

    override fun createElementPreview(viewModel: PageMainViewModel): ElementPreview<out Element> =
        ImagePreview(this, viewModel)

    override fun getElementName(): String = "图片"

    companion object {
        fun new(): ImageElement {
            return ImageElement(
                id = "image" + getBaseId(),
                contentDescription = "image",
                imageFrom = ImageFrom.LOCAL,
                image = "",
                filePath = ""
            )
        }
    }
}

enum class ImageFrom {
    URL, // 远程网络图片
    LOCAL // 本地图片，mipmap/drawable文件夹下的
}