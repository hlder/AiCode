package createcode.templatecode.elements

import createcode.util.toCodeString
import pcui.beans.elements.TextButtonElement

class TextButtonCreator(element: TextButtonElement) : ElementCreator<TextButtonElement>(element) {
    override fun createUiCode(space: String): String {
        return """
            TextButton(onClick = { /*TODO*/ }) {
                Text(text = "${element.text}")
            }
        """.toCodeString(space)
    }

    override fun createImportCode(): HashSet<String> {
        return hashSetOf("import androidx.compose.material.TextButton")
    }
}