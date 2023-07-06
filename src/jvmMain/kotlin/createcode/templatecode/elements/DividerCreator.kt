package createcode.templatecode.elements

import createcode.util.ConstantValues.ITEM_SPACE
import createcode.util.toCodeString
import pcui.beans.elements.DividerElement

class DividerCreator(element: DividerElement) : ElementCreator<DividerElement>(element) {
    private val importSets = HashSet<String>()
    override fun createUiCode(space: String): String {
        importSets.add("import androidx.compose.material.Divider")
        return """
            Divider(
                %s
            )
        """.toCodeString(space).format(
            getColor(space + ITEM_SPACE, element.dividerColor) + getModifier(space + ITEM_SPACE)
        )
    }

    override fun createImportCode(): HashSet<String> = importSets
}