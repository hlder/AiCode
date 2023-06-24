package createcode.templatecode.elements

import pcui.beans.Element

class DividerCreator(element: Element) : ElementCreator(element) {
    override fun createCode(space: String): String {
        return "${space}Divider()\n"
    }

    override fun createImportCode(): HashSet<String> {
        return hashSetOf("import androidx.compose.material.Divider")
    }
}