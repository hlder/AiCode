package createcode.templatecode.elements

import createcode.util.ConstantValues.ITEM_SPACE
import createcode.util.toCodeString
import pcui.beans.elements.ColumnElement
import pcui.beans.elements.LayoutAlignment

class ColumnCreator(element: ColumnElement, space: String) : ElementCreator<ColumnElement>(element, space) {
    private val importSet = hashSetOf<String>()
    override fun createUiCode(): String {
        val childContent = StringBuffer()
        element.childs?.forEach {
            childContent.append(it.getCreator("$space${ITEM_SPACE}").createUiCode())
        }

        val alignCodeSb = StringBuffer()
        if (element.align != null) {
            importSet.add("import androidx.compose.ui.Alignment")
            importSet.add("import androidx.compose.foundation.layout.Arrangement")

            when (element.align) {
                LayoutAlignment.START -> {
                    alignCodeSb.append("${space + ITEM_SPACE}horizontalAlignment = Alignment.Start,\n")
                }
                LayoutAlignment.END -> {
                    alignCodeSb.append("${space + ITEM_SPACE}horizontalAlignment = Alignment.End,\n")
                }
                LayoutAlignment.TOP -> {
                    alignCodeSb.append("${space + ITEM_SPACE}verticalArrangement = Arrangement.Top,\n")
                }
                LayoutAlignment.BOTTOM -> {
                    alignCodeSb.append("${space + ITEM_SPACE}verticalArrangement = Arrangement.BOTTOM,\n")
                }
                LayoutAlignment.CENTER_HORIZONTAL -> {
                    alignCodeSb.append("${space + ITEM_SPACE}horizontalAlignment = Alignment.CenterHorizontally,\n")
                }
                LayoutAlignment.CENTER_VERTICAL -> {
                    alignCodeSb.append("${space + ITEM_SPACE}verticalArrangement = Arrangement.Center,\n")
                }
                LayoutAlignment.CENTER -> {
                    alignCodeSb.append("${space + ITEM_SPACE}horizontalAlignment = Alignment.CenterHorizontally,\n")
                    alignCodeSb.append("${space + ITEM_SPACE}verticalArrangement = Arrangement.Center,\n")
                }
                LayoutAlignment.TOP_START -> {
                    alignCodeSb.append("${space + ITEM_SPACE}horizontalAlignment = Alignment.Start,\n")
                    alignCodeSb.append("${space + ITEM_SPACE}verticalArrangement = Arrangement.Top,\n")
                }
                LayoutAlignment.TOP_END -> {
                    alignCodeSb.append("${space + ITEM_SPACE}horizontalAlignment = Alignment.End,\n")
                    alignCodeSb.append("${space + ITEM_SPACE}verticalArrangement = Arrangement.Top,\n")
                }
                LayoutAlignment.BOTTOM_START -> {
                    alignCodeSb.append("${space + ITEM_SPACE}horizontalAlignment = Alignment.Start,\n")
                    alignCodeSb.append("${space + ITEM_SPACE}verticalArrangement = Arrangement.Bottom,\n")
                }
                LayoutAlignment.BOTTOM_END -> {
                    alignCodeSb.append("${space + ITEM_SPACE}horizontalAlignment = Alignment.End,\n")
                    alignCodeSb.append("${space + ITEM_SPACE}verticalArrangement = Arrangement.Bottom,\n")
                }
            }
        }

        return """
            Column(
                %s
                %s
            ) {
                %s
            }
        """.toCodeString(space).format(
            getModifier(),
            alignCodeSb.toString(),
            childContent
        )
    }

    override fun createLogicCode() = mutableListOf<String>()

    override fun createImportCode(): HashSet<String> {
        importSet.add("import androidx.compose.foundation.layout.Column")
        element.childs?.forEach { item ->
            item.getCreator(space + ITEM_SPACE).getImportCode().forEach {
                importSet.add(it)
            }
        }
        return importSet
    }
}