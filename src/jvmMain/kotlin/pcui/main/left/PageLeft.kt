@file:OptIn(ExperimentalComposeUiApi::class)

package pcui.main.left

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
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
import androidx.compose.ui.layout.boundsInParent
import androidx.compose.ui.layout.boundsInRoot
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInRoot
import androidx.compose.ui.unit.dp
import pcui.beans.Element
import pcui.beans.elements.LayoutElement
import pcui.main.PageMainViewModel
import java.awt.event.MouseEvent.MOUSE_DRAGGED

@Composable
fun PageLeft(viewModel: PageMainViewModel) {
    val pageLeftViewModel = remember { PageLeftViewModel() }
    val page = viewModel.listPage[1]

    val isNeedDrag = remember { mutableStateOf(false) }

    val canvasDrawItem = remember { mutableStateOf<Triple<Element, Offset, Rect>?>(null) }

    // canvas绘制框的位置改变
    val moveCanvasPosition = remember { mutableStateOf(Offset(0f, 0f)) }
    Column(
        modifier = Modifier
            .onGloballyPositioned {
                pageLeftViewModel.contentPosition = it.positionInRoot()
            }
            .onPointerEvent(
                eventType = PointerEventType.Move,
                onEvent = {
                    val event = it.awtEventOrNull
                    if (MOUSE_DRAGGED == event?.id && isNeedDrag.value) {
                        println("================base:(${pageLeftViewModel.contentPosition?.x},${pageLeftViewModel.contentPosition?.y}) " +
                                "now:(${event.x},${event.y}) touchDown:(${pageLeftViewModel.downPosition.x},${pageLeftViewModel.downPosition.y})")
                        canvasDrawItem.value?.let {
                            moveCanvasPosition.value = Offset(
                                event.x - pageLeftViewModel.downPosition.x - (pageLeftViewModel.contentPosition?.x ?: 0f),
                                event.y - pageLeftViewModel.downPosition.y - (pageLeftViewModel.contentPosition?.y ?: 0f)
                            )
                        }
                    }
                },
            ).onPointerEvent(
                eventType = PointerEventType.Release,
                onEvent = {
                    val event = it.awtEventOrNull
                    isNeedDrag.value = false
                },
            )
            .pointerInput(Unit) {
                detectTapGestures(
                    onPress = {
                        isNeedDrag.value = false
                        moveCanvasPosition.value = Offset(0f,0f)
                    }, onLongPress = {
                        val item = pageLeftViewModel.onTouchDown(it.x, it.y)
                        item?.let {
                            canvasDrawItem.value = item
                            isNeedDrag.value = true
                        }
                    }
                )
            }
    ) {
        pageLeftViewModel.childPosition.clear()
        bl(pageLeftViewModel, page.element, 0)
    }

    Canvas(modifier = Modifier.fillMaxSize()) {
        if (isNeedDrag.value && canvasDrawItem != null) {
            var position = canvasDrawItem.value?.second
            val basePosition = pageLeftViewModel.contentPosition ?: Offset(0f, 0f)
            val rect = canvasDrawItem.value?.third
            val movePosition = moveCanvasPosition.value
            if (position != null && rect != null) {
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
            }
        }
    }
}

@Composable
fun bl(pageLeftViewModel: PageLeftViewModel, element: Element, indentCount: Int) {
    val viewText = "${element.javaClass.simpleName.replace("Element", "")}（id=\"${element.id}\"）"
    val color = if (element is LayoutElement) {
        Color.Red
    } else {
        Color.Green
    }
    Row(
        modifier = Modifier.background(color = color).padding(start = (20 * indentCount).dp)
            .fillMaxWidth()
            .onGloballyPositioned {
                val position = it.positionInRoot()
                pageLeftViewModel.childPosition.add(
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
                println("================it.size:${it.positionInRoot()} text:${viewText}  boundsInRoot:${it.boundsInParent()}")
            }
    ) {
        Text(viewText, modifier = Modifier)
    }
    if (element is LayoutElement) {
        element.childs?.forEach {
            bl(pageLeftViewModel, it, indentCount + 1)
        }
    }
}