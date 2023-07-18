package pcui.main.left

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import pcui.beans.Element
import pcui.beans.Page
import pcui.beans.elements.LayoutElement
import pcui.beans.foreach

class PageLeftViewModel(private val page: Page) {
    var contentPosition: Offset? = null
    val childPosition = mutableListOf<Triple<Element, Offset, Rect>>()

    // 拖动的event
    val dragEvent = mutableStateOf(Offset(0f, 0f))

    // 按下触发时的元素信息
    var dragDrownInfo: Triple<Element, Offset, Rect>? = null

    // 当前拖动位置的元素信息
    private var dragMovedInfo: Triple<Element, Offset, Rect>? = null

    // 需要移动到该元素的top，false则移动到该元素的bottom位置
    private var dragMoveTop = true

    // 拖拽的时候，需要易懂的位置
    val dragSelectPositionY = mutableStateOf(0f)

    var downPosition = Offset(0f, 0f)

    /**
     * 鼠标长按触发时初始化。
     */
    fun onTouchDown(x: Float, y: Float): Triple<Element, Offset, Rect>? {
        downPosition = Offset(x, y)
        val contentPosition = this.contentPosition ?: return null
        val item = childPosition.find {
            it.third.contains(Offset(x + contentPosition.x, y + contentPosition.y))
        }
        println("==============onTouchDown:(${x},${y})")
        dragSelectPositionY.value = 0f
        scanDragOnElement()
        dragDrownInfo = item
        return item
    }

    /**
     * 按下拖动的时候,检测需要移动的位置。
     */
    fun scanDragOnElement() {
        val nowY = dragEvent.value.y + downPosition.y + (contentPosition?.y ?: 0f)

        for (i in 0 until childPosition.size) {
            val item1 = childPosition[i]
            if (i < (childPosition.size - 1)) {
                val item2 = childPosition[i + 1]
                if (nowY >= item1.second.y && nowY < item2.second.y) {
                    dragSelectPositionY.value = item1.second.y - (contentPosition?.y ?: 0f)
                    dragMovedInfo = item1
                    dragMoveTop = true
                    return
                }
            } else { // 最后一个元素
                if (nowY > item1.second.y && nowY < (item1.second.y + item1.third.height)) {
                    dragSelectPositionY.value = item1.second.y - (contentPosition?.y ?: 0f)
                    dragMovedInfo = item1
                    dragMoveTop = true
                    return
                } else if (nowY > (item1.second.y + item1.third.height)) {
                    dragSelectPositionY.value = item1.second.y + item1.third.height - (contentPosition?.y ?: 0f)
                    dragMovedInfo = item1
                    dragMoveTop = false
                    return
                }
            }
        }
    }

    /**
     * 松开时，移动元素的位置
     */
    fun doMoveElement() {
        val downElement = dragDrownInfo?.first
        val movedElement = dragMovedInfo?.first
        if (
            downElement != null
            && movedElement != null
            && downElement != movedElement
            && page.element is LayoutElement
            && downElement != page.element
            && movedElement != page.element
        ) {
            doRemove(page.element, downElement)
            doAdd(page.element, downElement, movedElement)
            println("-----------------------------------------------------------------------------------")
            page.element.foreach {
                println("elementId:${it.id}")
            }
            println("-----------------------------------------------------------------------------------")
        }
    }

    private fun doRemove(element: LayoutElement, downElement: Element) {
        element.childs?.let { list ->
            for (i in list.indices) {
                val item = list[i]
                if (item == downElement) {
                    list.removeAt(i)
                    return
                }
                if (item is LayoutElement) {
                    doRemove(item, downElement)
                }
            }
        }
    }

    private fun doAdd(element: LayoutElement, downElement: Element, movedElement: Element): Boolean {
        element.childs?.let { list ->
            for (i in list.indices) {
                val item = list[i]
                if (item == movedElement) {
                    list.add(i, downElement)
                    return true
                }
                if (item is LayoutElement) {
                    doAdd(item, downElement, movedElement)
                }
            }
        }
        return false
    }
}