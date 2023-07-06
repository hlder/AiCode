package createcode.templatecode.elements

import pcui.beans.Element

class EmptyCreator(element: Element) : ElementCreator<Element>(element) {
    override fun createUiCode(space: String): String {
        return ""
    }
    override fun createImportCode(): HashSet<String> {
        return hashSetOf()
    }
}