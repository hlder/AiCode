package createcode.templatecode.elements

import createcode.util.ConstantValues.ITEM_SPACE
import createcode.util.toCodeString
import pcui.beans.elements.TextElement

class TextCreator(element: TextElement) : ElementCreator<TextElement>(element) {
    private val importSets = HashSet<String>()

    override fun createUiCode(space: String): String {
        importSets.add("import androidx.compose.material.Text")
        return """
            Text(
                text = "${element.text}",
                %s
            )
        """.toCodeString(space).format(
            getFontSize(space + ITEM_SPACE) +
            getColor(space + ITEM_SPACE, element.textColor) +
            getFontWeight(space + ITEM_SPACE) +
            getTextAlign(space + ITEM_SPACE) +
            getModifier(space + ITEM_SPACE)
        )
    }

    private fun getTextAlign(space: String): String {
        return element.textAlign?.let {
            importSets.add("import androidx.compose.ui.text.style.TextAlign")
            "${space}textAlign = TextAlign.${it},\n"
        } ?: ""
    }

    private fun getFontWeight(space: String): String {
        return element.textWeight?.let {
            importSets.add("import androidx.compose.ui.text.font.FontWeight")
            "${space}fontWeight = FontWeight.${it},\n"
        } ?: ""
    }

    private fun getFontSize(space: String): String {
        return element.textSize?.let {
            importSets.add("import androidx.compose.ui.unit.sp")
            "${space}fontSize = ${it}.sp,\n"
        } ?: ""
    }

    override fun createLogicCode(space: String): String = ""

    override fun createImportCode(): HashSet<String> = importSets
}