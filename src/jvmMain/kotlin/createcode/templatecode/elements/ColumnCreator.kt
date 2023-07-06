package createcode.templatecode.elements

import createcode.util.ConstantValues.ITEM_SPACE
import createcode.util.toCodeString
import pcui.beans.elements.ColumnElement

class ColumnCreator(element: ColumnElement) : ElementCreator<ColumnElement>(element) {
    override fun createUiCode(space: String): String {
        val childContent = StringBuffer()
        element.childs?.forEach {
            childContent.append(it.getCreator().createUiCode("$space    "))
        }
        return """
            Column(
                %s
            ) {
                %s
            }
        """.toCodeString(space).format(
            getModifier(space + ITEM_SPACE),
            childContent
        )
    }

    override fun createImportCode(): HashSet<String> {
        val set = hashSetOf("import androidx.compose.foundation.layout.Column")
        element.childs?.forEach { item ->
            item.getCreator().getImportCode().forEach {
                set.add(it)
            }
        }
        return set
    }
}