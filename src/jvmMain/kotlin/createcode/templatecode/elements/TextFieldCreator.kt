package createcode.templatecode.elements

import createcode.util.ConstantValues.ITEM_SPACE
import createcode.util.toCodeString
import pcui.beans.elements.TextFieldElement

class TextFieldCreator(element: TextFieldElement, space: String) : ElementCreator<TextFieldElement>(element, space) {
    private val logicCodeList = mutableListOf<String>()

    override fun createUiCode(): String {
        addImportCode("import com.aicode.widgets.HintTextFiled")
        addImportCode("import androidx.compose.ui.text.TextStyle")
        addImportCode("import androidx.compose.runtime.mutableStateOf")
        addImportCode("import androidx.compose.runtime.remember")

        val valueFiledName = "${element.id}Text"

        logicCodeList.add("val $valueFiledName = remember { mutableStateOf(\"${element.text}\") }")

        return """
            HintTextFiled(
                textStyle = TextStyle(
                    %s
                ),
                value = ${valueFiledName}.value, 
                onValueChange = {
                    ${valueFiledName}.value = it
                }
            )
        """.toCodeString(space).format(
            getFontSize(space + ITEM_SPACE + ITEM_SPACE) +
            getColor(element.textColor) +
            getFontWeight(space + ITEM_SPACE + ITEM_SPACE) +
            getTextAlign(space + ITEM_SPACE + ITEM_SPACE)
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

    override fun createLogicCode() = logicCodeList
}