package createcode.templatecode.elements

import createcode.util.ConstantValues.ITEM_SPACE
import createcode.util.toCodeString
import pcui.beans.elements.ButtonElement

class ButtonCreator(element: ButtonElement) : ElementCreator<ButtonElement>(element) {
    override fun createCode(space: String): String {
        val textElementStr = TextCreator(element).createCode(space + ITEM_SPACE)
        val clickCode = ""
        return """
            Button(
                onClick = { 
                    %s
                }
            ) {
                %s
            }
        """.toCodeString(space).format(
            clickCode,
            textElementStr
        )
    }

    override fun createImportCode(): HashSet<String> {
        return hashSetOf("import androidx.compose.material.Button")
    }
}