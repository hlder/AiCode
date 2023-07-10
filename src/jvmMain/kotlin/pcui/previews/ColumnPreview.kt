package pcui.previews

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import pcui.beans.elements.ColumnElement
import pcui.beans.elements.LayoutAlignment
import pcui.previews.propertys.ModifierPreview
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

class ColumnPreview(element: ColumnElement): ElementPreview<ColumnElement>(element) {
    @Composable
    override fun preview(modifier: Modifier) {
        var horizontalAlignment: Alignment.Horizontal = Alignment.Start
        var verticalArrangement: Arrangement.Vertical = Arrangement.Top
        when (element.align) {
            LayoutAlignment.START -> {
                horizontalAlignment = Alignment.Start
            }
            LayoutAlignment.END -> {
                horizontalAlignment = Alignment.End
            }
            LayoutAlignment.TOP -> {
                verticalArrangement = Arrangement.Top
            }
            LayoutAlignment.BOTTOM -> {
                verticalArrangement = Arrangement.Bottom
            }
            LayoutAlignment.CENTER_HORIZONTAL -> {
                horizontalAlignment = Alignment.CenterHorizontally
            }
            LayoutAlignment.CENTER_VERTICAL -> {
                verticalArrangement = Arrangement.Center
            }
            LayoutAlignment.CENTER -> {
                horizontalAlignment = Alignment.CenterHorizontally
                verticalArrangement = Arrangement.Center
            }
            LayoutAlignment.TOP_START -> {
                horizontalAlignment = Alignment.Start
                verticalArrangement = Arrangement.Top
            }
            LayoutAlignment.TOP_END -> {
                horizontalAlignment = Alignment.End
                verticalArrangement = Arrangement.Top
            }
            LayoutAlignment.BOTTOM_START -> {
                horizontalAlignment = Alignment.Start
                verticalArrangement = Arrangement.Bottom
            }
            LayoutAlignment.BOTTOM_END -> {
                horizontalAlignment = Alignment.End
                verticalArrangement = Arrangement.Bottom
            }
        }
        Column(
            modifier = ModifierPreview(element).getModifier().then(modifier),
            horizontalAlignment = horizontalAlignment,
            verticalArrangement = verticalArrangement,
        ) {
            element.childs?.forEach { item->
                val itemModifier = item.weight?.let { Modifier.weight(it) }?:Modifier
                item.getPreview().preview(itemModifier)
            }
        }
    }
}