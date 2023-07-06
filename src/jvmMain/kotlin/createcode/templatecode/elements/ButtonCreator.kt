package createcode.templatecode.elements

import createcode.util.ConstantValues.ITEM_SPACE
import createcode.util.toCodeString
import pcui.beans.elements.ButtonElement

class ButtonCreator(element: ButtonElement) : ElementCreator<ButtonElement>(element) {
    private val imports = hashSetOf<String>()
    override fun createUiCode(space: String): String {
        val textElementStr = TextCreator(element).createUiCode(space + ITEM_SPACE)

        val (clickCode, imports) = element.buttonAction.createCode(space + ITEM_SPACE + ITEM_SPACE)
        imports.forEach { this.imports.add(it) }
        this.imports.add("import androidx.compose.material.Button")
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

    override fun createImportCode(): HashSet<String> = imports
}