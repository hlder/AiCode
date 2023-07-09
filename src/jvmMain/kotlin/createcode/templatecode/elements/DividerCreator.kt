package createcode.templatecode.elements

import createcode.util.toCodeString
import pcui.beans.elements.DividerElement

class DividerCreator(element: DividerElement, space: String) : ElementCreator<DividerElement>(element, space) {
    override fun createUiCode(): String {
        addImportCode("import androidx.compose.material.Divider")
        return """
            Divider(
                %s
            )
        """.toCodeString(space).format(
            getColor(element.dividerColor) + getModifier()
        )
    }
}