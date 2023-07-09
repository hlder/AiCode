package createcode.templatecode.elements

import pcui.beans.Element

class EmptyCreator(element: Element, space: String) : ElementCreator<Element>(element, space) {
    override fun createUiCode(): String {
        return ""
    }

    override fun createLogicCode() = mutableListOf<String>()

    override fun createImportCode(): HashSet<String> {
        return hashSetOf()
    }
}