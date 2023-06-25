package createcode.templatecode.elements

import createcode.templatecode.elements.propertys.ColorCreator
import createcode.templatecode.elements.propertys.ModifierCreator
import pcui.beans.Element
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

        /**
         * 获取ElementCreator
         */
        fun get(element: Element): ElementCreator<out Element> {
            return when (element) {
                // 文本Text
                is TextElement -> getFromCache(element) { TextCreator(element) }
                // 输入框
                is TextFieldElement -> getFromCache(element) { TextFieldCreator(element) }
                // 分割线
                is DividerElement -> getFromCache(element) { DividerCreator(element) }
                // 文本按钮
                is TextButtonElement -> getFromCache(element) { TextButtonCreator(element) }
                // 图片按钮
                is ButtonElement -> getFromCache(element) { ButtonCreator(element) }
                // Row
                is RowElement -> getFromCache(element) { RowCreator(element) }
                // Column
                is ColumnElement -> getFromCache(element) { ColumnCreator(element) }
                // Space
                is SpaceElement -> getFromCache(element) {SpaceCreator(element)}
                else -> { EmptyCreator(element) }
            }
        }
    }
}
