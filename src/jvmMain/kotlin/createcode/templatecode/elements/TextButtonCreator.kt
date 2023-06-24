package createcode.templatecode.elements

import pcui.beans.elements.TextButtonElement

class TextButtonCreator(element: TextButtonElement) : ElementCreator<TextButtonElement>(element) {
    override fun createCode(space: String): String {
        return "${space}TextButton(onClick = { /*TODO*/ }) {\n" +
                "$space    Text(text = \"${element.text}\")\n" +
                "${space}}\n"
    }

    override fun createImportCode(): HashSet<String> {
        return hashSetOf("import androidx.compose.material.TextButton")
    }
}