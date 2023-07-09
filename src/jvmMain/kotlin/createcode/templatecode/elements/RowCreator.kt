package createcode.templatecode.elements

import createcode.util.ConstantValues.ITEM_SPACE
import createcode.util.toCodeString
import pcui.beans.elements.RowElement

class RowCreator(element: RowElement, space: String) : LayoutCreator<RowElement>(element, space) {
    override fun createUiCode(): String {
        val childContent = StringBuffer()
        element.childs?.forEach {
            val itemContent = it.getCreator(space + ITEM_SPACE).createUiCode()
            childContent.append(itemContent)
        }

        addImportCode("import androidx.compose.foundation.layout.Row")
        element.childs?.forEach { item ->
            item.getCreator(space + ITEM_SPACE).getImportCode().forEach {
                addImportCode(it)
            }
        }

        return """
            Row(
                %s
                %s
            ) {
                %s
            }
        """.toCodeString(space).format(
            getModifier(),
            getAlignCode(),
            childContent
        )
    }
}