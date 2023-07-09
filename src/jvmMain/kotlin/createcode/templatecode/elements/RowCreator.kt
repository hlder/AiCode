package createcode.templatecode.elements

import createcode.util.ConstantValues.ITEM_SPACE
import createcode.util.toCodeString
import pcui.beans.elements.RowElement

class RowCreator(element: RowElement, space: String) : ElementCreator<RowElement>(element, space) {
    override fun createUiCode(): String {
        val childContent = StringBuffer()
        element.childs?.forEach {
            val itemContent = it.getCreator(space + ITEM_SPACE).createUiCode()
            childContent.append(itemContent)
        }
        return """
            Row(
                %s
            ) {
                %s
            }
        """.toCodeString(space).format(
            getModifier(),
            childContent
        )
    }

    override fun createLogicCode() = mutableListOf<String>()

    override fun createImportCode(): HashSet<String> {
        val set = hashSetOf("import androidx.compose.foundation.layout.Row")
        element.childs?.forEach { item ->
            item.getCreator(space + ITEM_SPACE).getImportCode().forEach {
                set.add(it)
            }
        }
        return set
    }
}