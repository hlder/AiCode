package createcode.templatecode.elements

import createcode.util.toCodeString
import pcui.beans.elements.TextButtonElement

class TextButtonCreator(element: TextButtonElement, space: String) : ElementCreator<TextButtonElement>(element, space) {
    override fun createUiCode(): String {
        return """
            TextButton(onClick = { /*TODO*/ }) {
                Text(text = "${element.text}")
            }
        """.toCodeString(space)
    }

    override fun createLogicCode() = mutableListOf<String>()

    override fun createImportCode(): HashSet<String> {
        return hashSetOf("import androidx.compose.material.TextButton")
    }
}