package createcode.templatecode

import pcui.beans.Element

class ElementCodeCreator(element: Element) {
    private val elementCreator = element.getCreator()

    /**
     * 创建element的代码
     */
    fun createElementCode(): String {
        return elementCreator.createCode("    ")
    }

    /**
     * 创建element所需要的import的代码
     */
    fun createElementImportCode(): HashSet<String> {
        return elementCreator.getImportCode()
    }
}