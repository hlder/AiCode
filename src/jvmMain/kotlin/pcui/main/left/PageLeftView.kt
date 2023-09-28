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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import pcui.beans.Element
import pcui.beans.elements.ButtonElement
import pcui.beans.elements.ColumnElement
import pcui.beans.elements.DividerElement
import pcui.beans.elements.ImageElement
import pcui.beans.elements.LayoutElement
import pcui.beans.elements.RowElement
import pcui.beans.elements.SpaceElement
import pcui.beans.elements.TextElement
import pcui.beans.elements.TextFieldElement
import pcui.beans.foreach
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
        val scrollState = rememberScrollState()
        Column(
            modifier = Modifier.verticalScroll(scrollState).onGloballyPositioned {
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
            val isShowChildMenu = remember { mutableStateOf(false) }
            Box(
                modifier = Modifier.offset(x = (touchEvent?.x ?: 0).dp, y = (touchEvent?.y ?: 0).dp)
            ) {
                Column(
                    modifier = Modifier.background(Color(0xff3c3f41))
                        .width(100.dp)
                        .border(1.dp, color = Color(0xff515151))
                ) {
                    rightMenuItem(listOf("新建", "上移", "下移", "删除", "复制")) {
                        when (it) {
                            0 -> { // 显示新建的控件
                                isShowChildMenu.value = true
                            }

                            1 -> { // 上移
                                val nowElement = viewModel.nowSelectedElement.value
                                nowElement?.let {
                                    val parentElement = viewModel.getParentElement(nowElement)
                                    val index = parentElement?.childs?.indexOf(nowElement) ?: 0
                                    if (index > 0) {
                                        parentElement?.childs?.remove(nowElement)
                                        parentElement?.childs?.add(index - 1, nowElement)
                                    } else {
                                        val parentParentElement =
                                            viewModel.getParentElement(parentElement)
                                        parentElement?.childs?.remove(nowElement)
                                        val parentIndex =
                                            parentParentElement?.childs?.indexOf(parentElement as Element)
                                                ?: 0
                                        parentParentElement?.childs?.add(parentIndex, nowElement)
                                    }
                                    viewModel.movePositionVersion.value++
                                    PageOverLayer.hideOverPop()
                                }
                            }

                            2 -> { // 下移
                                val nowElement = viewModel.nowSelectedElement.value
                                nowElement?.let {
                                    val parentElement = viewModel.getParentElement(nowElement)
                                    val index = parentElement?.childs?.indexOf(nowElement) ?: 0
                                    if (index < (parentElement?.childs?.size ?: 0) - 1) {
                                        parentElement?.childs?.remove(nowElement)
                                        parentElement?.childs?.add(index + 1, nowElement)
                                    } else {
                                        val parentParentElement =
                                            viewModel.getParentElement(parentElement)
                                        parentElement?.childs?.remove(nowElement)
                                        val parentIndex =
                                            parentParentElement?.childs?.indexOf(parentElement as Element)
                                                ?: 0
                                        parentParentElement?.childs?.add(
                                            parentIndex + 1,
                                            nowElement
                                        )
                                    }
                                    viewModel.movePositionVersion.value++
                                    PageOverLayer.hideOverPop()
                                }
                            }

                            3 -> { // 删除
                                viewModel.deleteElement(viewModel.nowSelectedElement.value)
                                viewModel.movePositionVersion.value++
                                PageOverLayer.hideOverPop()
                            }

                            4 -> { // 复制
                                val nowElement = viewModel.nowSelectedElement.value
                                nowElement?.let {
                                    val parentElement = viewModel.getParentElement(nowElement)
                                    val index = parentElement?.childs?.indexOf(nowElement) ?: 0
                                    nowElement.copy()?.let { copyElement ->
                                        parentElement?.childs?.add(index + 1, copyElement)
                                        viewModel.movePositionVersion.value++
                                        PageOverLayer.hideOverPop()
                                    }
                                }
                            }
                        }
                    }
                }

                if (isShowChildMenu.value) {
                    Box(modifier = Modifier.offset(x = 90.dp)) {
                        Column(
                            modifier = Modifier.background(Color(0xff3c3f41))
                                .width(100.dp)
                                .border(1.dp, color = Color(0xff515151))
                        ) {
                            rightMenuItem(
                                listOf(
                                    "竖布局",
                                    "横布局",
                                    "文本",
                                    "输入框",
                                    "按钮",
                                    "图片",
                                    "占位",
                                    "分割线"
                                )
                            ) {
                                val layoutElement: LayoutElement? =
                                    if (viewModel.nowSelectedElement.value is LayoutElement) { // 如果是布局，则在它里面添加
                                        viewModel.nowSelectedElement.value as LayoutElement
                                    } else { // 不是布局，那么添加到该控件下面一个
                                        viewModel.getParentElement(viewModel.nowSelectedElement.value)
                                    }
                                val addIndex =
                                    if (layoutElement == viewModel.nowSelectedElement.value) {
                                        layoutElement?.childs?.size ?: 0
                                    } else {
                                        (layoutElement?.childs?.indexOf(viewModel.nowSelectedElement.value)
                                            ?: 0) + 1
                                    }
                                when (it) {
                                    0 -> layoutElement?.childs?.add(addIndex, ColumnElement.new())
                                    1 -> layoutElement?.childs?.add(addIndex, RowElement.new())
                                    2 -> layoutElement?.childs?.add(addIndex, TextElement.new())
                                    3 -> layoutElement?.childs?.add(
                                        addIndex,
                                        TextFieldElement.new()
                                    )

                                    4 -> layoutElement?.childs?.add(addIndex, ButtonElement.new())
                                    5 -> layoutElement?.childs?.add(addIndex, ImageElement.new())
                                    6 -> layoutElement?.childs?.add(addIndex, SpaceElement.new())
                                    7 -> layoutElement?.childs?.add(addIndex, DividerElement.new())
                                }
                                viewModel.movePositionVersion.value++
                                PageOverLayer.hideOverPop()
                            }
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
                    .clickable { onclick(index) }
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
