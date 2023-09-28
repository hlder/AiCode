package pcui.beans

import androidx.compose.ui.graphics.Color
import createcode.templatecode.elements.ElementCreator
import pcui.beans.elements.ColumnElement
import pcui.beans.elements.ImageElement
import pcui.beans.elements.ImageFrom
import pcui.beans.elements.LayoutAlignment
import pcui.beans.elements.LayoutElement
import pcui.beans.elements.TextElement
import pcui.main.PageMainViewModel
import pcui.previews.ElementPreview
import java.text.SimpleDateFormat
import java.util.Calendar
import kotlin.reflect.KMutableProperty1
import kotlin.reflect.full.companionObject
import kotlin.reflect.full.createInstance
import kotlin.reflect.full.declaredFunctions
import kotlin.reflect.full.declaredMemberExtensionFunctions
import kotlin.reflect.full.declaredMemberFunctions
import kotlin.reflect.full.functions
import kotlin.reflect.full.memberExtensionFunctions
import kotlin.reflect.full.memberFunctions
import kotlin.reflect.full.memberProperties
import kotlin.reflect.full.staticFunctions
import kotlin.reflect.jvm.isAccessible
import kotlin.reflect.jvm.javaField

abstract class Element(
    var id: String, // element的id
    var width: Int?, // 大于等于Int.MAX_VALUE表示充满，其他则直接设置
    var height: Int?, // 大于等于Int.MAX_VALUE表示充满，其他则直接设置
    var paddingTop: Int?,
    var paddingBottom: Int?,
    var paddingStart: Int?,
    var paddingEnd: Int?,
    var backgroundColor: Color?, // 背景颜色
    var backgroundRoundTopLeft: Int?, // 背景的圆角左上角
    var backgroundRoundTopRight: Int?, // 背景的圆角右上角
    var backgroundRoundBottomLeft: Int?, // 背景的圆角左下角
    var backgroundRoundBottomRight: Int?, // 背景的圆角右下角
    var weight: Float?, // 权重，可以设置平均分配
) {
    private var elementCreator: ElementCreator<out Element>? = null

    /**
     * 获取creator
     */
    fun getCreator(space: String): ElementCreator<out Element> {
        if (elementCreator == null) {
            elementCreator = createElementCreator(space)
        }
        return elementCreator as ElementCreator<out Element>
    }

    private var elementPreview: ElementPreview<out Element>? = null

    /**
     * 获取预览
     */
    fun getPreview(viewModel: PageMainViewModel): ElementPreview<out Element> {
        if (elementPreview == null) {
            elementPreview = createElementPreview(viewModel)

        }
        return elementPreview as ElementPreview<out Element>
    }

    /**
     * 模板方法，创建creator
     */
    protected abstract fun createElementCreator(space: String): ElementCreator<out Element>

    protected abstract fun createElementPreview(viewModel: PageMainViewModel): ElementPreview<out Element>

    /**
     * 获取元素的名称
     */
    abstract fun getElementName(): String

    fun copy(): Element? {
        val newFun = this::class.companionObject?.functions?.find { it.name == "new" }
        newFun?.let { _ ->
            val newObj: Element =
                newFun.call(this::class.companionObject?.objectInstance) as Element
            val parentProperties = this::class.memberProperties
            parentProperties.forEach { property ->
                if ((property.name == "childs") && (this is LayoutElement)) {
                    this.childs.forEach {
                        it.copy()?.let { itemElement ->
                            (newObj as LayoutElement).childs.add(itemElement)
                        }
                    }
                } else if (property.name != "id") {
                    property.isAccessible = true
                    val javaField = property.javaField
                    javaField?.set(newObj, javaField.get(this))
                }
            }
            return newObj
        }
        return null
    }

    companion object {
        private var index = 1

        /**
         * 获取id的公共部分
         */
        fun getBaseId(): String {
            return SimpleDateFormat("yyyyMMddHHmmss").format(Calendar.getInstance().timeInMillis) + (index++)
        }
    }
}

/**
 * 遍历整个element元素树
 */
fun Element.foreach(block: (element: Element) -> Unit) {
    if (this is LayoutElement) {
        this.childs.forEach {
            it.foreach(block)
        }
    }
    block(this)
}

/**
 * 获取持有它的元素
 */
fun Element.getParent(rootElement: Element): LayoutElement? {
    if (rootElement is LayoutElement) {
        var layoutElement: LayoutElement? = null
        rootElement.childs.forEach {
            if (it == this) {
                layoutElement = rootElement
                return rootElement
            }
            if (it is LayoutElement && layoutElement == null) {
                layoutElement = getParent(it)
            }
        }
        return layoutElement
    } else {
        return null
    }
}

fun main() {
    val c1 = ColumnElement(
        id = "actionItemLayout1",
        weight = 1f,
        align = LayoutAlignment.CENTER,
        childs = mutableListOf(
            ImageElement(
                id = "imageElement1",
                image = "icon_home_waimai",
                imageFrom = ImageFrom.LOCAL,
                contentDescription = "外卖",
                filePath = "G:\\temp\\icons\\icon_home_waimai.png",
                width = 50,
                height = 50
            ),
            TextElement(
                id = "actionItemText1",
                textColor = Color(0xFF353535),
                text = "外卖",
                textSize = 14,
                paddingTop = 5
            )
        )
    )
    val c2 = c1.copy()

    println("============c1:$c1")
    println("============c2:$c2")
    println("============c:${c1.id}  ${c2?.id}")
}