package createcode.templatecode.elements

import createcode.util.ConstantValues
import pcui.beans.elements.SpaceElement

class SpaceCreator(element: SpaceElement) : ElementCreator<SpaceElement>(element) {
    private val importSets = HashSet<String>()
    override fun createCode(space: String): String {
        importSets.add("import androidx.compose.foundation.layout.Spacer")
        return "${space}Spacer(\n" +
                getModifier(space + ConstantValues.ITEM_SPACE) +
                "${space})\n"
    }

    override fun createImportCode(): HashSet<String> = importSets
}