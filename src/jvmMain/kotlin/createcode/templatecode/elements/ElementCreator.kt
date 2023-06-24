package createcode.templatecode.elements

import createcode.templatecode.elements.propertys.ModifierCreator
import pcui.beans.Element
import pcui.beans.ElementType
import pcui.beans.elements.*

/**
 * Element代码的创建者
 */
abstract class ElementCreator<T:Element>(val element: T) {
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
        private val creatorCacheMap = HashMap<Element, ElementCreator<out Element>>()
        private fun getFromCache(
            element: Element,
            creator: () -> ElementCreator<out Element>
        ): ElementCreator<out Element> {
            val cache = creatorCacheMap[element]
            if (cache != null) {
                return cache
            }
            return creator().apply {
                creatorCacheMap[element] = this
            }
        }

        fun get(element: Element): ElementCreator<out Element> {
            return when (element.type) {
                // 文本Text
                ElementType.TEXT -> getFromCache(element) { TextCreator(element as TextElement) }
                // 输入框
                ElementType.TEXT_FIELD -> getFromCache(element) { TextFieldCreator(element as TextFieldElement) }
                // 分割线
                ElementType.DIVIDER -> getFromCache(element) { DividerCreator(element as DividerElement) }
                // 文本按钮
                ElementType.TEXT_BUTTON -> getFromCache(element) { TextButtonCreator(element as TextButtonElement) }
                // 图片按钮
                ElementType.BUTTON -> getFromCache(element) { ButtonCreator(element as ButtonElement) }
                // Row
                ElementType.ROW -> getFromCache(element) { RowCreator(element as RowElement) }
                // Column
                ElementType.COLUMN -> getFromCache(element) { ColumnCreator(element as ColumnElement) }
            }
        }
    }
}
