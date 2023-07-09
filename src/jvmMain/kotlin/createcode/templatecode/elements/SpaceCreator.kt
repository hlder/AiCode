package createcode.templatecode.elements

import createcode.util.toCodeString
import pcui.beans.elements.SpaceElement

class SpaceCreator(element: SpaceElement, space: String) : ElementCreator<SpaceElement>(element, space) {
    private val importSets = HashSet<String>()
    override fun createUiCode(): String {
        importSets.add("import androidx.compose.foundation.layout.Spacer")
        return """
            Spacer(
                %s
            )
        """.toCodeString(space).format(
            getModifier()
        )
    }

    override fun createLogicCode() = mutableListOf<String>()

    override fun createImportCode(): HashSet<String> = importSets
}