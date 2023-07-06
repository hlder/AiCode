package createcode.templatecode.elements

import createcode.util.ConstantValues
import createcode.util.toCodeString
import pcui.beans.elements.SpaceElement

class SpaceCreator(element: SpaceElement) : ElementCreator<SpaceElement>(element) {
    private val importSets = HashSet<String>()
    override fun createUiCode(space: String): String {
        importSets.add("import androidx.compose.foundation.layout.Spacer")
        return """
            Spacer(
                %s
            )
        """.toCodeString(space).format(
            getModifier(space + ConstantValues.ITEM_SPACE)
        )
    }

    override fun createLogicCode(space: String): String = ""

    override fun createImportCode(): HashSet<String> = importSets
}