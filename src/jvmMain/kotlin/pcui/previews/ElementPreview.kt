package pcui.previews

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import pcui.beans.Element
import pcui.beans.elements.LayoutElement
import pcui.main.PageMainViewModel

abstract class ElementPreview<T : Element>(val element: T, val viewModel: PageMainViewModel) {
    @Composable
    fun preview(modifier: Modifier) {
        val nowSelectedElement = remember { viewModel.nowSelectedElement }

        val clickModifier = Modifier.pointerInput(Unit) {
            detectTapGestures(
                onPress = {
                    viewModel.nowSelectedElement.value = element
                }
            )
        }

        val boxClickModifier = if (element is LayoutElement) {
            clickModifier
        } else {
            Modifier
        }

        Box(modifier = Modifier.then(boxClickModifier)) {
//            if (element is SpaceElement) {
//                val boxModifierWidth = if (element.width == null || element.width == 0) {
//                    Modifier.fillMaxWidth()
//                } else {
//                    Modifier.width((element.width ?: 0).dp)
//                }
//                val boxModifierHeight = if (element.height == null || element.height == 0) {
//                    Modifier.fillMaxHeight()
//                } else {
//                    Modifier.height((element.height ?: 0).dp)
//                }
//                Spacer(modifier = Modifier.then(boxModifierWidth).then(boxModifierHeight))
//            }
            if (nowSelectedElement.value == element) {
                previewImpl(Modifier.then(modifier))
                Spacer(modifier = Modifier.matchParentSize().background(color = Color(0x66000000)))
            } else {
                previewImpl(modifier)
            }
            if (element !is LayoutElement) {
                // 点击热区，遮住控件原有的点击事件
                Spacer(modifier = Modifier.then(clickModifier).matchParentSize())
            }
        }
    }

    @Composable
    abstract fun previewImpl(modifier: Modifier)
}