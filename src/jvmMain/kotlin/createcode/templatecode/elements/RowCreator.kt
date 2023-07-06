package createcode.templatecode.elements

import createcode.util.ConstantValues.ITEM_SPACE
import createcode.util.toCodeString
import pcui.beans.elements.RowElement

class RowCreator(element: RowElement) : ElementCreator<RowElement>(element) {
    override fun createUiCode(space: String): String {
        val childContent = StringBuffer()
        element.childs?.forEach {
            val itemContent = it.getCreator().createUiCode(space + ITEM_SPACE)
            childContent.append(itemContent)
        }
        return """
            Row(
                %s
            ) {
                %s
            }
        """.toCodeString(space).format(
            getModifier(space + ITEM_SPACE),
            childContent
        )
    }

    override fun createLogicCode(space: String): String = ""

    override fun createImportCode(): HashSet<String> {
        val set = hashSetOf("import androidx.compose.foundation.layout.Row")
        element.childs?.forEach { item ->
            item.getCreator().getImportCode().forEach {
                set.add(it)
            }
        }
        return set
    }
}