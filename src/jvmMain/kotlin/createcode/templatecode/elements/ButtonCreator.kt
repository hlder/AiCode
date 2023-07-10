package createcode.templatecode.elements

import createcode.util.ConstantValues.ITEM_SPACE
import createcode.util.toCodeString
import pcui.beans.elements.ButtonElement

class ButtonCreator(element: ButtonElement, space: String) : ElementCreator<ButtonElement>(element, space) {
    override fun createUiCode(): String {
        val textCreator = TextCreator(element, space + ITEM_SPACE)
        val textElementStr = textCreator.createUiCode()
        addImportCode(textCreator.getImportCode())
        addLogicCode(textCreator.getLogicCode())

        val (clickCode, imports) = element.buttonAction.createCode(space + ITEM_SPACE + ITEM_SPACE)
        addImportCode(imports)

        addImportCode("import androidx.compose.material.Button")
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
}