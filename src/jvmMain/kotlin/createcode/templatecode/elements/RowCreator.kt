package createcode.templatecode.elements

import pcui.beans.elements.RowElement

class RowCreator(element: RowElement) : ElementCreator<RowElement>(element) {
    override fun createCode(space: String): String {
        val childContent = StringBuffer()
        element.childs?.forEach {
            childContent.append(get(it).createCode("${space}    "))
        }
        return "${space}Row {\n" +
                "${childContent}\n" +
                "${space}}"
    }

    override fun createImportCode(): HashSet<String> {
        val set = hashSetOf("import androidx.compose.foundation.layout.Row")
        element.childs?.forEach { item ->
            get(item).getImportCode().forEach {
                set.add(it)
            }
        }
        return set
    }
}