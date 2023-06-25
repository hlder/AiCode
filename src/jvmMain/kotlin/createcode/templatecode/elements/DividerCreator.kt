package createcode.templatecode.elements

import createcode.util.ConstantValues.ITEM_SPACE
import pcui.beans.elements.DividerElement

class DividerCreator(element: DividerElement) : ElementCreator<DividerElement>(element) {
    private val importSets = HashSet<String>()
    override fun createCode(space: String): String {
        importSets.add("import androidx.compose.material.Divider")
        return "${space}Divider(\n" +
                getColor(space + ITEM_SPACE, element.dividerColor) +
                getModifier(space + ITEM_SPACE) +
                "${space})\n"
    }

    override fun createImportCode(): HashSet<String> = importSets
}