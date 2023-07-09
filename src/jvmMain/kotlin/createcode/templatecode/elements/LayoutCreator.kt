package createcode.templatecode.elements

import createcode.util.ConstantValues.ITEM_SPACE
import pcui.beans.elements.ColumnElement
import pcui.beans.elements.LayoutAlignment
import pcui.beans.elements.LayoutElement
import pcui.beans.elements.RowElement

abstract class LayoutCreator<T : LayoutElement>(element: T, space: String) : ElementCreator<T>(element, space) {
    /**
     * 获取对齐方式的代码
     */
    protected fun getAlignCode():String {
        val alignCodeSb = StringBuffer()
        if (element.align != null) {
            addImportCode("import androidx.compose.ui.Alignment")
            addImportCode("import androidx.compose.foundation.layout.Arrangement")

            val horizontalField = when (element) {
                is ColumnElement -> "horizontalAlignment"
                is RowElement -> "horizontalArrangement"
                else -> ""
            }
            val verticalField = when (element) {
                is ColumnElement -> "verticalArrangement"
                is RowElement -> "verticalAlignment"
                else -> ""
            }

            when (element.align) {
                LayoutAlignment.START -> {
                    alignCodeSb.append("${space + ITEM_SPACE}${horizontalField} = Alignment.Start,\n")
                }
                LayoutAlignment.END -> {
                    alignCodeSb.append("${space + ITEM_SPACE}${horizontalField} = Alignment.End,\n")
                }
                LayoutAlignment.TOP -> {
                    alignCodeSb.append("${space + ITEM_SPACE}${verticalField} = Arrangement.Top,\n")
                }
                LayoutAlignment.BOTTOM -> {
                    alignCodeSb.append("${space + ITEM_SPACE}${verticalField} = Arrangement.BOTTOM,\n")
                }
                LayoutAlignment.CENTER_HORIZONTAL -> {
                    alignCodeSb.append("${space + ITEM_SPACE}${horizontalField} = Alignment.CenterHorizontally,\n")
                }
                LayoutAlignment.CENTER_VERTICAL -> {
                    alignCodeSb.append("${space + ITEM_SPACE}${verticalField} = Arrangement.Center,\n")
                }
                LayoutAlignment.CENTER -> {
                    alignCodeSb.append("${space + ITEM_SPACE}${horizontalField} = Alignment.CenterHorizontally,\n")
                    alignCodeSb.append("${space + ITEM_SPACE}${verticalField} = Arrangement.Center,\n")
                }
                LayoutAlignment.TOP_START -> {
                    alignCodeSb.append("${space + ITEM_SPACE}${horizontalField} = Alignment.Start,\n")
                    alignCodeSb.append("${space + ITEM_SPACE}${verticalField} = Arrangement.Top,\n")
                }
                LayoutAlignment.TOP_END -> {
                    alignCodeSb.append("${space + ITEM_SPACE}${horizontalField} = Alignment.End,\n")
                    alignCodeSb.append("${space + ITEM_SPACE}${verticalField} = Arrangement.Top,\n")
                }
                LayoutAlignment.BOTTOM_START -> {
                    alignCodeSb.append("${space + ITEM_SPACE}${horizontalField} = Alignment.Start,\n")
                    alignCodeSb.append("${space + ITEM_SPACE}${verticalField} = Arrangement.Bottom,\n")
                }
                LayoutAlignment.BOTTOM_END -> {
                    alignCodeSb.append("${space + ITEM_SPACE}${horizontalField} = Alignment.End,\n")
                    alignCodeSb.append("${space + ITEM_SPACE}${verticalField} = Arrangement.Bottom,\n")
                }
            }
        }
        return alignCodeSb.toString()
    }
}