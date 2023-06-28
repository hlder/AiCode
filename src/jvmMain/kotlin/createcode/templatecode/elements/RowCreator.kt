package createcode.templatecode.elements

import createcode.util.ConstantValues.ITEM_SPACE
import pcui.beans.elements.RowElement

class RowCreator(element: RowElement) : ElementCreator<RowElement>(element) {
    override fun createCode(space: String): String {
        val childContent = StringBuffer()
        element.childs?.forEach {
            val itemContent = it.getCreator().createCode(space + ITEM_SPACE)
            childContent.append(itemContent)
        }
        return "${space}Row(\n" +
                getModifier(space + ITEM_SPACE) +
                "${space}) {\n" +
                childContent +
                "${space}}\n"
    }

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