package createcode.templatecode.elements

import createcode.templatecode.elements.propertys.ModifierCreator
import pcui.beans.Element
import pcui.beans.ElementType

/**
 * Element代码的创建者
 */
abstract class ElementCreator(val element: Element) {
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
    abstract fun createCode(space: String): String

    /**
     * 创建该元素代码中需要的import代码
     */
    protected abstract fun createImportCode(): HashSet<String>

    companion object {
        // 缓存creator,同一个element对象只能有一个creator
        private val creatorCacheMap = HashMap<Element, ElementCreator>()
        private fun getFromCache(element: Element, creator: () -> ElementCreator): ElementCreator {
            return creatorCacheMap[element] ?: creator().apply { creatorCacheMap[element] = this }
        }
        fun get(element: Element): ElementCreator {
            return when (element.type) {
                // 文本Text
                ElementType.TEXT -> getFromCache(element) { TextCreator(element) }
                // 输入框
                ElementType.TEXT_FIELD -> getFromCache(element) { TextFieldCreator(element) }
                // 分割线
                ElementType.DIVIDER -> getFromCache(element) { DividerCreator(element) }
                // 文本按钮
                ElementType.TEXT_BUTTON -> getFromCache(element) { TextButtonCreator(element) }
                // 图片按钮
                ElementType.BUTTON -> getFromCache(element) { ButtonCreator(element) }
                // Row
                ElementType.ROW -> getFromCache(element) { RowCreator(element) }
                // Column
                ElementType.COLUMN -> getFromCache(element) { ColumnCreator(element) }
            }
        }
    }
}