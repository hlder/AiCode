package createcode.templatecode.elements.propertys

import createcode.util.ConstantValues.ITEM_SPACE
import pcui.beans.Element
import pcui.beans.elements.ColumnElement
import pcui.beans.elements.LayoutElement
import pcui.beans.elements.RowElement

/**
 * Modifier属性的代码生成器
 */
class ModifierCreator(private val element: Element, private val space: String) {
    private val importSets: HashSet<String> = HashSet()
    private val listLogicCode = ArrayList<String>()
    private val modifierSb = StringBuffer()

    init {
        createCode()
    }

    fun getImportSets(): HashSet<String> = importSets

    fun getLogicCode(): MutableList<String> = listLogicCode

    fun getModifierCode(): String = if (modifierSb.isNotEmpty()) {
        importSets.add("import androidx.compose.ui.Modifier")
        "${space}modifier = Modifier${modifierSb},\n"
    } else {
        ""
    }

    /**
     * 生成代码
     */
    private fun createCode() {
        // 添加控件大小设置的代码
        addSizeCode()

        // 添加背景颜色的代码
        addBackgroundColorCode()

        // 添加权重的代码(运用于弹性布局)
        addWeightCode()

        // 添加padding的代码
        addPaddingCode()

        // 添加需要滚动的代码
        addScrollCode()
    }

    /**
     * 添加控件大小设置的代码
     */
    private fun addSizeCode() {
        element.width?.let {
            val widthStr = if (it >= Int.MAX_VALUE) {
                importSets.add("import androidx.compose.foundation.layout.fillMaxWidth")
                ".fillMaxWidth()"
            } else {
                importSets.add("import androidx.compose.ui.unit.dp")
                importSets.add("import androidx.compose.foundation.layout.width")
                ".width(${it}.dp)"
            }
            modifierSb.append("\n${space}${ITEM_SPACE}${widthStr}")
        }
        element.height?.let {
            val heightStr = if (it >= Int.MAX_VALUE) {
                importSets.add("import androidx.compose.foundation.layout.fillMaxHeight")
                ".fillMaxHeight()"
            } else {
                importSets.add("import androidx.compose.ui.unit.dp")
                importSets.add("import androidx.compose.foundation.layout.height")
                ".height(${it}.dp)"
            }
            modifierSb.append("\n${space}${ITEM_SPACE}${heightStr}")
        }
    }

    /**
     * 添加权重的代码(运用于弹性布局)
     */
    private fun addWeightCode() {
        element.weight?.let {
            modifierSb.append("\n${space}${ITEM_SPACE}.weight(${it}f)")
        }
    }

    /**
     * 添加需要滚动的代码
     */
    private fun addScrollCode() {
        if (element is LayoutElement && element.isNeedScroll == true) {
            val fieldName = "${element.id}ScrollState"
            if (element is ColumnElement) {
                modifierSb.append("\n${space}${ITEM_SPACE}.verticalScroll(${fieldName})")
            } else if (element is RowElement) {
                modifierSb.append("\n${space}${ITEM_SPACE}.horizontalScroll(${fieldName})")
            }

            listLogicCode.add("val $fieldName = rememberScrollState()")

            importSets.add("import androidx.compose.foundation.rememberScrollState")
            importSets.add("import androidx.compose.foundation.verticalScroll")
        }
    }

    /**
     * 添加背景颜色的代码
     */
    private fun addBackgroundColorCode() {
        element.backgroundColor?.let { color->
            importSets.add("import androidx.compose.foundation.background")
            importSets.add("import androidx.compose.ui.graphics.Color")
            importSets.add("import androidx.compose.ui.unit.dp")
            importSets.add("import androidx.compose.foundation.shape.RoundedCornerShape")
            val shapeSb = StringBuffer()
            element.backgroundRoundTopLeft?.let {
                shapeSb.append("topStart = ${it}.dp,")
            }
            element.backgroundRoundTopRight?.let {
                shapeSb.append("topEnd = ${it}.dp,")
            }
            element.backgroundRoundBottomLeft?.let {
                shapeSb.append("bottomStart = ${it}.dp,")
            }
            element.backgroundRoundBottomRight?.let {
                shapeSb.append("bottomEnd = ${it}.dp,")
            }

            val shapeStr = if (shapeSb.isNotEmpty()) {
                shapeSb.replace(shapeSb.length - 1, shapeSb.length, "")
                "RoundedCornerShape(${shapeSb.toString()})"
            }else{
                ""
            }
//            val shapeStr = element.backgroundRounded?.let { rounded ->
//                "RoundedCornerShape($rounded.dp)"
//            } ?: ""
            modifierSb.append("\n${space}${ITEM_SPACE}.background(color = ${getColorCodeStr(color)}, ${shapeStr})")
        }
    }

    /**
     * 添加padding的代码
     */
    private fun addPaddingCode() {
        val paddingSb = StringBuffer()
        element.paddingTop?.let {
            paddingSb.append("top = ${it}.dp,")
        }
        element.paddingBottom?.let {
            val paddingSpace = if (paddingSb.isNotEmpty()){" "}else{""}
            paddingSb.append("${paddingSpace}bottom = ${it}.dp,")
        }
        element.paddingStart?.let {
            val paddingSpace = if (paddingSb.isNotEmpty()){" "}else{""}
            paddingSb.append("${paddingSpace}start = ${it}.dp,")
        }
        element.paddingEnd?.let {
            val paddingSpace = if (paddingSb.isNotEmpty()){" "}else{""}
            paddingSb.append("${paddingSpace}end = ${it}.dp,")
        }
        if (paddingSb.isNotEmpty()) {
            paddingSb.replace(paddingSb.length - 1, paddingSb.length, "")
        }
        val paddingStr = paddingSb.toString()
        if (paddingStr.isNotEmpty()) {
            importSets.add("import androidx.compose.foundation.layout.padding")
            importSets.add("import androidx.compose.ui.unit.dp")
            modifierSb.append("\n${space}${ITEM_SPACE}.padding(${paddingStr})")
        }
    }
}