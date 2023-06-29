package createcode.templatecode.elements

import createcode.util.toCodeString
import pcui.beans.elements.ButtonElement

class ButtonCreator(element: ButtonElement) : ElementCreator<ButtonElement>(element) {
    override fun createCode(space: String): String {
        return """
            Button(onClick = { /*TODO*/ }) {
                Text(text = "button1")
            }
        """.toCodeString(space)
    }

    override fun createImportCode(): HashSet<String> {
        return hashSetOf("import androidx.compose.material.Button")
    }
}