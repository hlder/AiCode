package createcode.templatecode.elements

import createcode.util.toCodeString
import pcui.beans.elements.DividerElement

class DividerCreator(element: DividerElement, space: String) : ElementCreator<DividerElement>(element, space) {
    private val importSets = HashSet<String>()
    override fun createUiCode(): String {
        importSets.add("import androidx.compose.material.Divider")
        return """
            Divider(
                %s
            )
        """.toCodeString(space).format(
            getColor(element.dividerColor) + getModifier()
        )
    }

    override fun createLogicCode() = mutableListOf<String>()

    override fun createImportCode(): HashSet<String> = importSets
}