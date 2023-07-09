package createcode.templatecode.elements

import createcode.util.ConstantValues.ITEM_SPACE
import createcode.util.toCodeString
import pcui.beans.elements.ButtonElement

class ButtonCreator(element: ButtonElement, space: String) : ElementCreator<ButtonElement>(element, space) {
    private val imports = hashSetOf<String>()
    override fun createUiCode(): String {
        val textElementStr = TextCreator(element, space + ITEM_SPACE).createUiCode()

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

    override fun createLogicCode() = mutableListOf<String>()

    override fun createImportCode(): HashSet<String> = imports
}