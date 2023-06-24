package createcode.templatecode.elements

import pcui.beans.elements.ButtonElement

class ButtonCreator(element: ButtonElement) : ElementCreator<ButtonElement>(element) {
    override fun createCode(space: String): String {
        return "${space}Button(onClick = { /*TODO*/ }) {\n" +
                "$space    Text(text = \"button1\")\n" +
                "${space}}\n"
    }

    override fun createImportCode(): HashSet<String> {
        return hashSetOf("import androidx.compose.foundation.layout.Column")
    }
}