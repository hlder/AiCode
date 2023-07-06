package createcode.templatecode

import createcode.util.ConstantValues.ITEM_SPACE
import pcui.beans.Element
import pcui.beans.elements.LayoutElement

class ElementCodeCreator(private val element: Element) {
    private val elementCreator = element.getCreator()

    /**
     * 创建element的代码
     */
    fun createUiCode(): String {
        return elementCreator.createUiCode(ITEM_SPACE)
    }

    /**
     * 创建元素相关的逻辑代码,逻辑代码是放在方法体的元素代码上面
     */
    fun createLogicCode(): String {
        val codeSb = StringBuffer()
        createAllElementLogicCode(element, codeSb)
        return codeSb.toString()
    }

    private fun createAllElementLogicCode(element: Element, codeSb: StringBuffer) {
        if (element is LayoutElement) {
            element.childs?.forEach {
                createAllElementLogicCode(it, codeSb)
            }
        }
        val itemLine = element.getCreator().createLogicCode(ITEM_SPACE)
        if (itemLine.isNotEmpty()) {
            codeSb.append(itemLine + "\n")
        }
    }

    /**
     * 创建element所需要的import的代码
     */
    fun createElementImportCode(): HashSet<String> {
        return elementCreator.getImportCode()
    }
}