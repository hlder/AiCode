@file:OptIn(ExperimentalFoundationApi::class)

package pcui.main.left

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.mouseClickable
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.awt.awtEventOrNull
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.isSecondaryPressed
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInRoot
import pcui.main.PageMainViewModel

import java.awt.event.MouseEvent.MOUSE_DRAGGED

// 拖动时候的拖动条的颜色
val dragColor = Color(0x880059ff)

// 文本的颜色
val textColor: Color = Color(0xffbbbbbb)

@Composable
fun PageLeft(viewModel: PageMainViewModel) {
    val nowSelectPage = remember { viewModel.nowSelectPage }.value
    val selectedPage = viewModel.listPage[nowSelectPage]
    val pageLeftViewModel = remember { PageLeftViewModel(selectedPage) }

    val isNeedDrag = remember { mutableStateOf(false) }

    val nowSelectedElement = remember { viewModel.nowSelectedElement }
    Column(
        modifier = Modifier.onGloballyPositioned {
            pageLeftViewModel.contentPosition = it.positionInRoot()
        }.touchListener(
            onTouchDown = { // touchDown
                val event = it.awtEventOrNull
                isNeedDrag.value = false
                pageLeftViewModel.initDragEvent()

                val item = pageLeftViewModel.onTouchDown(event?.x?.toFloat()?:0f, event?.y?.toFloat()?:0f)
                item?.let {
                    viewModel.nowSelectedElement.value = item.first
                    isNeedDrag.value = true
                }
            },
            onTouchMove = { // touchMove
                val event = it.awtEventOrNull
                if (MOUSE_DRAGGED == event?.id && isNeedDrag.value) {
                    pageLeftViewModel.changeDragEvent(event.x, event.y)
                    pageLeftViewModel.scanDragOnElement()
                }
            },
            onTouchUp = { // touchUp
                if (pageLeftViewModel.doMoveElement()) {
                    viewModel.movePositionVersion.value++
                }
                isNeedDrag.value = false
            }
        ).mouseClickable {
            if (this.buttons.isSecondaryPressed) { // 点击了鼠标右键
                println("==============点击右键，显示菜单")
            }
        }
    ) {
        // 显示所有层级
        showLayers(
            nowSelectedElement.value,
            isNeedDrag.value,
            pageLeftViewModel,
            selectedPage.element,
            0
        )
    }

    // 是否拖动，拖动的时候显示touch的框框
    if (isNeedDrag.value) {
        nowSelectedElement.value?.let {
            ShowDrag(it, pageLeftViewModel)
        }
    }

    // 清除位置
    pageLeftViewModel.clearListPosition()
}