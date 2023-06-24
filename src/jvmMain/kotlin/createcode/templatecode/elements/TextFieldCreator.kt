package createcode.templatecode.elements

import pcui.beans.Element

class TextFieldCreator(element: Element) : ElementCreator(element) {
    override fun createCode(space: String): String {
        return "${space}TextField(value = \"${element.text}\", onValueChange = {})\n"
    }

    override fun createImportCode(): HashSet<String> {
        return hashSetOf("import androidx.compose.material.TextField")
    }
}