package createcode.templatecode

import createcode.templatecode.elements.ElementCreator
import pcui.beans.Element

class ElementCodeCreator(element: Element) {
    private val elementCreator = ElementCreator.get(element)

    /**
     * 创建element的代码
     */
    fun createElementCode(): String {
        return elementCreator.createCode("    ")
    }

    /**
     * 创建element所需要的import的代码
     */
    fun createElementImportCode(): String {
        val sb = StringBuffer()
        elementCreator.getImportCode().forEach { sb.append("${it}\n") }
        return sb.toString()
    }
}