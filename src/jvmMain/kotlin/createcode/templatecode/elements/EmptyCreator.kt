package createcode.templatecode.elements

import pcui.beans.Element

class EmptyCreator(element: Element) : ElementCreator<Element>(element) {
    override fun createCode(space: String): String {
        return ""
    }
    override fun createImportCode(): HashSet<String> {
        return hashSetOf()
    }
}