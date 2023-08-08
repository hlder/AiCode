package pcui.main.left

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInRoot
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import pcui.beans.Element
import pcui.beans.elements.LayoutElement

@Composable
fun showLayers(
    nowSelectedElement: Element?,
    isNeedDrag: Boolean,
    pageLeftViewModel: PageLeftViewModel,
    element: Element,
    indentCount: Int
) {
    val viewText = "${element.getElementName()}"
    val borderModifier = if (element == nowSelectedElement) {
        Modifier.background(color = Color(0xff0d293e))
    } else {
        Modifier
    }
    Box(
        modifier = Modifier.then(borderModifier)
    ) {
        Row(
            modifier = Modifier
                .padding(start = (20 * indentCount).dp, top = 2.dp, bottom = 2.dp)
                .fillMaxWidth()
                .onGloballyPositioned {
                    val position = it.positionInRoot()
                    pageLeftViewModel.addOnePosition(
                        Triple(
                            element,
                            it.positionInRoot(),
                            Rect(
                                position.x,
                                position.y,
                                position.x + it.size.width,
                                position.y + it.size.height
                            )
                        )
                    )
                }
        ) {
            Text(
                text = viewText,
                modifier = Modifier,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                fontSize = 14.sp,
                color = textColor
            )
            Text(
                text = " (\"${element.id}\")",
                modifier = Modifier,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                fontSize = 14.sp,
                color =  Color(0x99bbbbbb)
            )
        }
    }
    // 如果是布局的话，则需要加载它的子元素
    if (element is LayoutElement) {
        element.childs?.forEach {
            showLayers(nowSelectedElement, isNeedDrag, pageLeftViewModel, it, indentCount + 1)
        }
    }
}