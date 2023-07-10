package pcui.main.center

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import pcui.beans.Page
import androidx.compose.ui.text.style.TextAlign
import pcui.beans.elements.*

@Composable
fun PhonePreview(page: Page) {
//    val element = ColumnElement(
//        id = "actionItemLayout1",
//        weight = 1f,
//        align = LayoutAlignment.CENTER,
//        width = 10000,
//        height = 10000,
//        childs = listOf(
//            ImageElement(
//                id = "imageElement1",
//                image = "icon_home_waimai",
//                imageFrom = ImageFrom.LOCAL,
//                contentDescription = "外卖",
//                filePath = "G:\\temp\\icons\\icon_home_waimai.png",
//                width = 50,
//                height = 50
//            ),
//            TextElement(
//                id = "actionItemText1",
//                textColor = Color.Blue,
//                backgroundColor = Color.Yellow,
//                text = "外卖",
//                textSize = 14,
//                textAlign = TextAlign.Center,
//                weight = 1f
//            )
//        )
//    )
//    element.getPreview().preview(Modifier)
    page.element.getPreview().preview(Modifier)
}
