@file:OptIn(ExperimentalFoundationApi::class)

package pcui.main.left

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.mouseClickable
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.awt.awtEventOrNull
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.isSecondaryPressed
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInRoot
import androidx.compose.ui.unit.dp
import pcui.main.PageMainViewModel
import pcui.main.PageOverLayer
import java.awt.event.MouseEvent

class PageLeftView(private val viewModel: PageMainViewModel) {
    private var touchEvent: MouseEvent? = null
    private var x = 0f
    private var y = 0f
    private var width = 0
    private var height = 0

    @Composable
    fun onView() {
        val nowSelectPageIndex = remember { viewModel.nowSelectPageIndex }.value
        val selectedPage = viewModel.listPage[nowSelectPageIndex]
        val pageLeftViewModel = remember { PageLeftViewModel(selectedPage) }

        val isNeedDrag = remember { mutableStateOf(false) }

        val nowSelectedElement = remember { viewModel.nowSelectedElement }
        Column(
            modifier = Modifier.onGloballyPositioned {
                pageLeftViewModel.contentPosition = it.positionInRoot()
            }.touchListener(
                onTouchDown = { // touchDown
                    touchEvent = it.awtEventOrNull
                    val event = it.awtEventOrNull
                    isNeedDrag.value = false
                    pageLeftViewModel.initDragEvent()

                    val item = pageLeftViewModel.onTouchDown(
                        event?.x?.toFloat() ?: 0f,
                        event?.y?.toFloat() ?: 0f
                    )
                    item?.let {
                        viewModel.nowSelectedElement.value = item.first
                        isNeedDrag.value = true
                    }
                },
                onTouchMove = { // touchMove
                    if (!it.buttons.isSecondaryPressed) {
                        val event = it.awtEventOrNull
                        if (MouseEvent.MOUSE_DRAGGED == event?.id && isNeedDrag.value) {
                            pageLeftViewModel.changeDragEvent(event.x, event.y)
                            pageLeftViewModel.scanDragOnElement()
                        }
                    }
                },
                onTouchUp = { // touchUp
                    println("===================touchEvent:(${touchEvent?.x},${touchEvent?.y})")
                    if (pageLeftViewModel.doMoveElement()) {
                        viewModel.movePositionVersion.value++
                    }
                    isNeedDrag.value = false
                }
            ).mouseClickable {
                if (this.buttons.isSecondaryPressed && isNeedDrag.value) { // 点击了鼠标右键
                    println("==============点击右键，显示菜单")
                    showRightMenu()
                }
            }.onGloballyPositioned {
                x = it.positionInRoot().x
                y = it.positionInRoot().y
                width = it.size.width
                height = it.size.height
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

    private fun showRightMenu() {
        PageOverLayer.showOverPop {
            Box(
                modifier = Modifier.offset(x = (touchEvent?.x ?: 0).dp, y = (touchEvent?.y ?: 0).dp)
            ) {
                Column(
                    modifier = Modifier.background(Color(0xff3c3f41))
                        .width(100.dp)
                        .border(1.dp, color = Color(0xff515151))
                ) {
                    rightMenuItem(listOf("新建","上移","下移","删除")) {

                    }
                }

                Box(modifier = Modifier.offset(x = 90.dp)) {
                    Column(
                        modifier = Modifier.background(Color(0xff3c3f41))
                            .width(100.dp)
                            .border(1.dp, color = Color(0xff515151))
                    ) {
                        rightMenuItem(listOf("竖布局","横布局","文本","输入框","按钮","图片","占位", "分割线")) {

                        }
                    }
                }

            }
        }
    }

    @Composable
    private fun rightMenuItem(
        list: List<String>,
        onclick: (Int) -> Unit
    ) {
        for ((index, item) in list.withIndex()) {
            Row(
                modifier = Modifier
                    .clickable { onclick(index) }.touchListener(
                        onTouchMove = {
                            println("========================it:${item}")
                        }
                    )
            ) {
                Text(
                    text = item,
                    modifier = Modifier.padding(
                        top = 5.dp,
                        bottom = 5.dp,
                        start = 10.dp,
                        end = 10.dp
                    ).weight(1f),
                    color = Color.White
                )
            }
        }
    }
}
