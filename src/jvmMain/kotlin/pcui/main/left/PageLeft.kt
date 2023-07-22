package pcui.main.left

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.awt.awtEventOrNull
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.geometry.toRect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope.Companion.DefaultBlendMode
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.input.pointer.*
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInRoot
import androidx.compose.ui.unit.dp
import pcui.beans.Element
import pcui.beans.elements.LayoutElement
import pcui.main.PageMainViewModel
import java.awt.event.MouseEvent.MOUSE_DRAGGED

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun PageLeft(viewModel: PageMainViewModel) {
    val page = viewModel.listPage[1]
    val pageLeftViewModel = remember { PageLeftViewModel(page) }

    val isNeedDrag = remember { mutableStateOf(false) }

    val nowSelectedElement = remember { viewModel.nowSelectedElement }

    val canvasDrawItem = pageLeftViewModel.findPosition(nowSelectedElement.value)
//    val canvasDrawItem = remember { mutableStateOf<Triple<Element, Offset, Rect>?>(null) }

    // canvas绘制框的位置改变
    val moveCanvasPosition = remember { pageLeftViewModel.dragEvent }
    val moveCanvasSelectedLinePositionY = remember { pageLeftViewModel.dragSelectPositionY }
    Column(
        modifier = Modifier.onGloballyPositioned {
            pageLeftViewModel.contentPosition = it.positionInRoot()
        }.onPointerEvent(
            eventType = PointerEventType.Move,
            onEvent = {
                val event = it.awtEventOrNull
                if (MOUSE_DRAGGED == event?.id && isNeedDrag.value) {
                    moveCanvasPosition.value = Offset(
                        event.x - pageLeftViewModel.downPosition.x - (pageLeftViewModel.contentPosition?.x
                            ?: 0f),
                        event.y - pageLeftViewModel.downPosition.y - (pageLeftViewModel.contentPosition?.y
                            ?: 0f)
                    )
                    pageLeftViewModel.scanDragOnElement()
                }
            },
        ).onPointerEvent(
            eventType = PointerEventType.Release,
            onEvent = {
                val event = it.awtEventOrNull
                if (pageLeftViewModel.doMoveElement()) {
                    viewModel.movePositionVersion.value++
                }
                isNeedDrag.value = false
            },
        ).pointerInput(Unit) {
            detectTapGestures(
                onPress = {
                    isNeedDrag.value = false
                    moveCanvasPosition.value = Offset(0f, 0f)

                    val item = pageLeftViewModel.onTouchDown(it.x, it.y)
                    item?.let {
                        viewModel.nowSelectedElement.value = item.first
                        isNeedDrag.value = true
                    }
                }
            )
        }
    ) {
        pageLeftViewModel.clearListPosition()

        showLayers(nowSelectedElement.value, isNeedDrag.value, pageLeftViewModel, page.element, 0)
    }

    Canvas(modifier = Modifier.fillMaxSize()) {
        if (isNeedDrag.value && canvasDrawItem != null) {
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
                color = Color(0x999d9d9d),
                topLeft = position,
                size = Size(viewRect.width, rect.height),
                alpha = 1.0f,
                style = Fill,
                colorFilter = null,
                blendMode = DefaultBlendMode
            )
            this.drawLine(
                color = Color.Black,
                start = Offset(0f, moveCanvasSelectedLinePositionY.value),
                end = Offset(viewRect.width, moveCanvasSelectedLinePositionY.value),
            )
        }
    }
}

@Composable
fun showLayers(
    nowSelectedElement: Element?,
    isNeedDrag: Boolean,
    pageLeftViewModel: PageLeftViewModel,
    element: Element,
    indentCount: Int
) {
    val viewText = "${element.javaClass.simpleName.replace("Element", "")}（id=\"${element.id}\"）"
    var color = if (element is LayoutElement) {
        Color.Red
    } else {
        Color.Green
    }

    if (isNeedDrag && pageLeftViewModel.dragDrownInfo?.first == element) {
        color = Color.Blue
    }
    val borderModifier = if (element == nowSelectedElement) {
        Modifier.border(border = BorderStroke(1.dp, Color.Blue), shape = MaterialTheme.shapes.medium)
    } else {
        Modifier
    }
    Box(
        modifier = Modifier.then(borderModifier)
    ) {
        Row(
            modifier = Modifier.background(color = color).padding(start = (20 * indentCount).dp)
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
//                    println("================it.size:${it.positionInRoot()} text:${viewText}  boundsInRoot:${it.boundsInParent()}")
                }
        ) {
            Text(viewText, modifier = Modifier)
        }
    }
    if (element is LayoutElement) {
        element.childs?.forEach {
            showLayers(nowSelectedElement, isNeedDrag, pageLeftViewModel, it, indentCount + 1)
        }
    }
}