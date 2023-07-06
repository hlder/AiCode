package createcode.templatecode.elements

import createcode.templatecode.elements.propertys.ColorCreator
import createcode.templatecode.elements.propertys.ModifierCreator
import pcui.beans.Element

/**
 * Element代码的创建者
 */
abstract class ElementCreator<T : Element>(val element: T) {
    private val importSets = HashSet<String>()

    /**
     * 获取该element的modifier
     */
    fun getModifier(space: String): String {
        val (contentStr, importSets) = ModifierCreator(element).createCode(space)
        importSets.forEach { this.importSets.add(it) }
        return contentStr
    }

    /**
     * 获取颜色的代码
     */
    fun getColor(space: String, color: Int?): String {
        return color?.let {
            val (content, importSet) = ColorCreator().createCode(space, color)
            importSet.forEach { this.importSets.add(it) }
            content
        } ?: ""
    }

    /**
     * 获取import的代码
     */
    fun getImportCode(): HashSet<String> {
        val newSet = createImportCode()
        importSets.forEach { newSet.add(it) }
        return newSet
    }

    /**
     * 创建元素代码
     */
    abstract fun createUiCode(space: String): String

    /**
     * 创建元素相关的逻辑代码
     */
    abstract fun createLogicCode(space: String): String

    /**
     * 创建该元素代码中需要的import代码
     */
    protected abstract fun createImportCode(): HashSet<String>
}
