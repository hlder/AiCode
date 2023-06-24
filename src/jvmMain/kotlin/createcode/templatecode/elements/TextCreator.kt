package createcode.templatecode.elements

import pcui.beans.elements.TextElement

class TextCreator(element: TextElement) : ElementCreator<TextElement>(element) {
    private val importSets = HashSet<String>()

    override fun createCode(space: String): String {
        importSets.add("import androidx.compose.material.Text")
        return "${space}Text(\n" +
                "${space}${ITEM_SPACE}text = \"${element.text}\",\n" +
                getFontSize(space + ITEM_SPACE) +
                getFontColor(space + ITEM_SPACE) +
                getFontWeight(space + ITEM_SPACE) +
                getModifier(space + ITEM_SPACE) +
                "${space})\n"
    }

    private fun getFontWeight(space: String): String {
        return element.textWeight?.let {
            importSets.add("import androidx.compose.ui.text.font.FontWeight")
            "${space}fontWeight = FontWeight.${it},\n"
        }?:""
    }

    private fun getFontSize(space: String): String {
        return element.textSize?.let {
            importSets.add("import androidx.compose.ui.unit.sp")
            "${space}fontSize = ${it}.sp,\n"
        } ?: ""
    }

    private fun getFontColor(space: String): String {
        return element.textColor?.let {
            importSets.add("import androidx.compose.ui.graphics.Color")
            "${space}color = Color(${it}),\n"
        } ?: ""
    }

    override fun createImportCode(): HashSet<String> {
        return importSets
    }

    companion object {
        private const val ITEM_SPACE = "    "
    }
}