package createcode.templatecode.elements

import pcui.beans.elements.TextFieldElement

class TextFieldCreator(element: TextFieldElement) : ElementCreator<TextFieldElement>(element) {
    override fun createCode(space: String): String {
        return "${space}TextField(value = \"${element.text}\", onValueChange = {})\n"
    }

    override fun createImportCode(): HashSet<String> {
        return hashSetOf("import androidx.compose.material.TextField")
    }
}