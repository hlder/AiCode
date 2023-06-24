package createcode.templatecode.elements

import pcui.beans.elements.DividerElement

class DividerCreator(element: DividerElement) : ElementCreator<DividerElement>(element) {
    override fun createCode(space: String): String {
        return "${space}Divider()\n"
    }

    override fun createImportCode(): HashSet<String> {
        return hashSetOf("import androidx.compose.material.Divider")
    }
}