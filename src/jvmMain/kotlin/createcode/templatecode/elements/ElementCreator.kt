package createcode.templatecode.elements

import androidx.compose.ui.graphics.Color
import createcode.templatecode.elements.propertys.ColorCreator
import createcode.templatecode.elements.propertys.ModifierCreator
import createcode.util.ConstantValues.ITEM_SPACE
import pcui.beans.Element

/**
 * Element代码的创建者
 */
abstract class ElementCreator<T : Element>(val element: T, val space: String) {
    private val importSets = HashSet<String>()
    private val modifierCreator: ModifierCreator = ModifierCreator(element, space + ITEM_SPACE)

    /**
     * 获取该element的modifier
     */
    protected fun getModifier(): String = modifierCreator.getModifierCode()

    /**
     * 获取颜色的代码
     */
    protected fun getColor(color: Color?): String {
        return color?.let {
            val (content, importSet) = ColorCreator().createCode(space + ITEM_SPACE, color)
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
        modifierCreator.getImportSets().forEach { newSet.add(it) }
        return newSet
    }

    /**
     * 获得逻辑代码
     */
    fun getLogicCode(): MutableList<String> {
        val list = createLogicCode()
        list.addAll(modifierCreator.getLogicCode())
        return list
    }

    /**
     * 创建元素代码
     */
    abstract fun createUiCode(): String

    /**
     * 创建元素相关的逻辑代码
     */
    protected abstract fun createLogicCode(): MutableList<String>

    /**
     * 创建该元素代码中需要的import代码
     */
    protected abstract fun createImportCode(): HashSet<String>
}
