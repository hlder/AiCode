package createcode.templatecode.elements

import createcode.util.ConstantValues.ITEM_SPACE
import pcui.beans.elements.ColumnElement

class ColumnCreator(element: ColumnElement) : ElementCreator<ColumnElement>(element) {
    override fun createCode(space: String): String {
        val childContent = StringBuffer()
        element.childs?.forEach {
            childContent.append(it.getCreator().createCode("$space    "))
        }
        return "${space}Column(\n" +
                getModifier(space + ITEM_SPACE) +
                "${space}) {\n" +
                childContent +
                "${space}}"
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