package createcode.templatecode.elements

import createcode.util.ConstantValues.ITEM_SPACE
import createcode.util.toCodeString
import pcui.beans.elements.TextElement

class TextCreator(element: TextElement, space: String) : ElementCreator<TextElement>(element, space) {
    override fun createUiCode(): String {
        addImportCode("import androidx.compose.material.Text")
        return """
            Text(
                text = "${element.text}",
                %s
            )
        """.toCodeString(space).format(
            getFontSize(space + ITEM_SPACE) +
            getColor(element.textColor) +
            getFontWeight(space + ITEM_SPACE) +
            getTextAlign(space + ITEM_SPACE) +
            getModifier()
        )
    }

    private fun getTextAlign(space: String): String {
        return element.textAlign?.let {
            addImportCode("import androidx.compose.ui.text.style.TextAlign")
            "${space}textAlign = TextAlign.${it},\n"
        } ?: ""
    }

    private fun getFontWeight(space: String): String {
        return element.textWeight?.let {
            addImportCode("import androidx.compose.ui.text.font.FontWeight")
            "${space}fontWeight = FontWeight.${it},\n"
        } ?: ""
    }

    private fun getFontSize(space: String): String {
        return element.textSize?.let {
            addImportCode("import androidx.compose.ui.unit.sp")
            "${space}fontSize = ${it}.sp,\n"
        } ?: ""
    }
}