package createcode.templatecode

import createcode.util.ConstantValues.ITEM_SPACE
import pcui.beans.Element
import pcui.beans.elements.LayoutElement

class ElementCodeCreator(private val element: Element) {
    private val elementCreator = element.getCreator(ITEM_SPACE)

    /**
     * 创建element的代码
     */
    fun createUiCode(): String = elementCreator.createUiCode()

    /**
     * 创建元素相关的逻辑代码,逻辑代码是放在方法体的元素代码上面
     */
    fun createLogicCode(): String {
        val codeSb = StringBuffer()
        createAllElementLogicCode(element, codeSb)
        return codeSb.toString() + "\n"
    }

    private fun createAllElementLogicCode(element: Element, codeSb: StringBuffer) {
        if (element is LayoutElement) {
            element.childs?.forEach {
                createAllElementLogicCode(it, codeSb)
            }
        }
        val logicCodeList = element.getCreator(ITEM_SPACE).getLogicCode()
        if (logicCodeList.isNotEmpty()) {
            logicCodeList.forEach {
                codeSb.append("$ITEM_SPACE${it}\n")
            }
        }
    }

    /**
     * 创建element所需要的import的代码
     */
    fun createElementImportCode(): HashSet<String> = elementCreator.getImportCode()
}