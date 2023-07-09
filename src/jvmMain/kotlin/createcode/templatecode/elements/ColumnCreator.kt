package createcode.templatecode.elements

import createcode.util.ConstantValues.ITEM_SPACE
import createcode.util.toCodeString
import pcui.beans.elements.ColumnElement

class ColumnCreator(element: ColumnElement, space: String) : LayoutCreator<ColumnElement>(element, space) {
    override fun createUiCode(): String {
        val childContent = StringBuffer()
        element.childs?.forEach {
            childContent.append(it.getCreator("$space${ITEM_SPACE}").createUiCode())
        }
        addImportCode("import androidx.compose.foundation.layout.Column")
        element.childs?.forEach { item ->
            item.getCreator(space + ITEM_SPACE).getImportCode().forEach {
                addImportCode(it)
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
            getAlignCode(),
            childContent
        )
    }
}