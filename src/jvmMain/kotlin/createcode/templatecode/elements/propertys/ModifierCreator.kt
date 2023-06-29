package createcode.templatecode.elements.propertys

import createcode.util.ConstantValues.ITEM_SPACE
import pcui.beans.Element

/**
 * Modifier属性的代码生成器
 */
class ModifierCreator(private val element: Element) {
    /**
     * 生成代码
     */
    fun createCode(space: String): Pair<String, HashSet<String>> {
        val importSets: HashSet<String> = HashSet()
        val modifierSb = StringBuffer()
        element.width?.let {
            val widthStr = if (it >= 9999) {
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
            val heightStr = if (it >= 9999) {
                importSets.add("import androidx.compose.foundation.layout.fillMaxHeight")
                ".fillMaxHeight()"
            } else {
                importSets.add("import androidx.compose.ui.unit.dp")
                importSets.add("import androidx.compose.foundation.layout.height")
                ".height(${it}.dp)"
            }
            modifierSb.append("\n${space}${ITEM_SPACE}${heightStr}")
        }
        element.backgroundColor?.let {
            importSets.add("import androidx.compose.foundation.background")
            importSets.add("import androidx.compose.ui.graphics.Color")
            modifierSb.append("\n${space}${ITEM_SPACE}.background(color = Color(${it}))")
        }

        val paddingSb = StringBuffer()
        element.paddingTop?.let {
            paddingSb.append("top = ${it}.dp,")
        }
        element.paddingBottom?.let {
            paddingSb.append("bottom = ${it}.dp")
        }
        element.paddingStart?.let {
            paddingSb.append("start = ${it}.dp")
        }
        element.paddingEnd?.let {
            paddingSb.append("end = ${it}.dp")
        }
        val paddingStr = paddingSb.toString()
        if (paddingStr.isNotEmpty()) {
            importSets.add("import androidx.compose.foundation.layout.padding")
            importSets.add("import androidx.compose.ui.unit.dp")
            modifierSb.append("\n${space}${ITEM_SPACE}.padding(${paddingStr})")
        }
        val contentStr = if (modifierSb.isNotEmpty()) {
            importSets.add("import androidx.compose.ui.Modifier")
            "${space}modifier = Modifier${modifierSb},\n"
        } else {
            ""
        }
        return Pair(contentStr, importSets)
    }
}