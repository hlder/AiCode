package createcode.templatecode.elements

import androidx.compose.ui.graphics.Color
import createcode.templatecode.elements.propertys.ColorCreator
import createcode.templatecode.elements.propertys.ModifierCreator
import createcode.util.ConstantValues.ITEM_SPACE
import pcui.beans.Element
import java.util.LinkedList

/**
 * Element代码的创建者
 */
abstract class ElementCreator<T : Element>(val element: T, val space: String) {
    private val importSets = HashSet<String>()
    private val listLogicCode = LinkedList<String>()
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
        val newSet = hashSetOf<String>()
        importSets.forEach { newSet.add(it) }
        modifierCreator.getImportSets().forEach { newSet.add(it) }
        return newSet
    }

    /**
     * 获得逻辑代码
     */
    fun getLogicCode(): MutableList<String> {
        val list = mutableListOf<String>()
        list.addAll(listLogicCode)
        list.addAll(modifierCreator.getLogicCode())
        return list
    }

    protected fun addImportCode(importCode: String) {
        importSets.add(importCode)
    }

    protected fun addImportCode(imports: HashSet<String>) {
        importSets.addAll(imports)
    }

    protected fun addLogicCode(logicCode: String) {
        listLogicCode.add(logicCode)
    }

    protected fun addLogicCode(list: List<String>) {
        listLogicCode.addAll(list)
    }

    /**
     * 创建元素代码
     */
    abstract fun createUiCode(): String
}
