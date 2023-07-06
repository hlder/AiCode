package createcode.templatecode.elements

import createcode.util.ConstantValues.ITEM_SPACE
import createcode.util.toCodeString
import pcui.beans.elements.TextFieldElement

class TextFieldCreator(element: TextFieldElement) : ElementCreator<TextFieldElement>(element) {
    private val importSets = HashSet<String>()

    override fun createUiCode(space: String): String {
        importSets.add("import com.aicode.widgets.HintTextFiled")
        importSets.add("import androidx.compose.ui.text.TextStyle")
        return """
            HintTextFiled(
                textStyle = TextStyle(
                    %s
                ),
                value = "${element.text}", 
                onValueChange = {}
            )
        """.toCodeString(space).format(
            getFontSize(space + ITEM_SPACE + ITEM_SPACE) +
            getColor(space + ITEM_SPACE + ITEM_SPACE, element.textColor) +
            getFontWeight(space + ITEM_SPACE + ITEM_SPACE) +
            getTextAlign(space + ITEM_SPACE + ITEM_SPACE)
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

    override fun createImportCode(): HashSet<String> = importSets
}