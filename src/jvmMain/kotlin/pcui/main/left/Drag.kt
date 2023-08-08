package pcui.main.left

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.geometry.toRect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Fill
import pcui.beans.Element

@Composable
fun ShowDrag(nowSelectedElement: Element, pageLeftViewModel:PageLeftViewModel){
    val canvasDrawItem = pageLeftViewModel.findPosition(nowSelectedElement)
//    println("=======================ShowDrag:${canvasDrawItem}  nowSelectedElement:${nowSelectedElement}")

    // canvas绘制框的位置改变
    val moveCanvasPosition = remember { pageLeftViewModel.dragEvent }
    val moveCanvasSelectedLinePositionY = remember { pageLeftViewModel.dragSelectPositionY }

    Canvas(modifier = Modifier.fillMaxSize()) {
        if (canvasDrawItem != null) {
            var position = canvasDrawItem.second
            val basePosition = pageLeftViewModel.contentPosition ?: Offset(0f, 0f)
            val rect = canvasDrawItem.third
            val movePosition = moveCanvasPosition.value

            val viewRect = this.size.toRect()
            var positionY = position.y - basePosition.y + movePosition.y
            if (positionY < 0) {
                positionY = 0f
            }
            position = Offset(0f, positionY)
            this.drawRect(
                color = dragColor,
                topLeft = position,
                size = Size(viewRect.width, rect.height),
                alpha = 1.0f,
                style = Fill,
                colorFilter = null,
                blendMode = DrawScope.DefaultBlendMode
            )
            this.drawLine(
                color = Color.Black,
                start = Offset(0f, moveCanvasSelectedLinePositionY.value),
                end = Offset(viewRect.width, moveCanvasSelectedLinePositionY.value),
            )
        }
    }
}