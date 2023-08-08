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
    private val listChildPosition = mutableListOf<Triple<Element, Offset, Rect>>()

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
     * 添加一个位置信息
     */
    fun addOnePosition(item: Triple<Element, Offset, Rect>) {
        listChildPosition.add(item)
    }

    /**
     * 清除所有的位置信息
     */
    fun clearListPosition() {
        listChildPosition.clear()
    }

    /**
     * 搜索位置信息
     */
    fun findPosition(element: Element?): Triple<Element, Offset, Rect>? {
        return listChildPosition.find {
            it.first == element
        }
    }

    /**
     * 鼠标长按触发时初始化。
     */
    fun onTouchDown(ix: Float, iy: Float): Triple<Element, Offset, Rect>? {
        val x = ix - (contentPosition?.x ?: 0f)
        val y = iy - (contentPosition?.y ?: 0f)
        println("--------------------onTouchDown:(${x},${y})")

        downPosition = Offset(x, y)
        val contentPosition = this.contentPosition ?: return null
        val item = listChildPosition.find {
            it.third.contains(Offset(x + contentPosition.x, y + contentPosition.y))
        }
        dragSelectPositionY.value = 0f
        scanDragOnElement()
        dragDrownInfo = item
        return item
    }

    /**
     * touchDown的时候初始化dragEvent。
     */
    fun initDragEvent() {
        dragEvent.value = Offset(0f, 0f)
    }

    /**
     * touchMove的时候修改dragEvent
     */
    fun changeDragEvent(x: Int?, y: Int?) {
        dragEvent.value = Offset(
            (x ?: 0) - downPosition.x - (contentPosition?.x ?: 0f),
            (y ?: 0) - downPosition.y - (contentPosition?.y ?: 0f)
        )
    }

    /**
     * 按下拖动的时候,检测需要移动的位置。
     */
    fun scanDragOnElement() {
        val nowY = dragEvent.value.y + downPosition.y + (contentPosition?.y ?: 0f)

        for (i in 0 until listChildPosition.size) {
            val item1 = listChildPosition[i]
            if (i < (listChildPosition.size - 1)) {
                val item2 = listChildPosition[i + 1]
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
    fun doMoveElement(): Boolean {
        val downElement = dragDrownInfo?.first
        val movedElement = dragMovedInfo?.first
        return if (
            downElement != null
            && movedElement != null
            && downElement != movedElement
            && page.element is LayoutElement
            && downElement != page.element
            && movedElement != page.element
            && !isInMine(downElement, movedElement)
        ) {
            doRemove(page.element, downElement)
            doAdd(page.element, downElement, movedElement)
            println("-----------------------------------------------------------------------------------")
            page.element.foreach {
                println("elementId:${it.id}")
            }
            println("-----------------------------------------------------------------------------------")
            true
        } else {
            false
        }
    }

    // 判断需要移动的位置是否在自己内部
    private fun isInMine(selfElement: Element, movedElement: Element): Boolean {
        var isInMine = false
        selfElement.foreach {
            if (it == movedElement) {
                isInMine = true
            }
        }
        return isInMine
    }

    // 执行删除
    private fun doRemove(element: LayoutElement, removedElement: Element) {
        element.childs?.let { list ->
            for (i in list.indices) {
                val item = list[i]
                if (item == removedElement) {
                    list.removeAt(i)
                    return
                }
                if (item is LayoutElement) {
                    doRemove(item, removedElement)
                }
            }
        }
    }

    // 执行添加
    private fun doAdd(element: LayoutElement, addElement: Element, movedElement: Element): Boolean {
        element.childs?.let { list ->
            for (i in list.indices) {
                val item = list[i]
                if (item == movedElement) {
                    list.add(i, addElement)
                    return true
                }
                if (item is LayoutElement) {
                    doAdd(item, addElement, movedElement)
                }
            }
        }
        return false
    }
}