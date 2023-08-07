package pcui.previews

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import pcui.beans.elements.ColumnElement
import pcui.beans.elements.LayoutAlignment
import pcui.previews.propertys.ModifierPreview
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import pcui.main.PageMainViewModel

class ColumnPreview(element: ColumnElement, viewModel: PageMainViewModel) :
    ElementPreview<ColumnElement>(element, viewModel) {
    @Composable
    override fun previewImpl(modifier: Modifier) {
        var horizontal: Alignment.Horizontal = Alignment.Start
        var vertical: Arrangement.Vertical = Arrangement.Top
        when (element.align) {
            LayoutAlignment.START -> {
                horizontal = Alignment.Start
            }

            LayoutAlignment.END -> {
                horizontal = Alignment.End
            }

            LayoutAlignment.TOP -> {
                vertical = Arrangement.Top
            }

            LayoutAlignment.BOTTOM -> {
                vertical = Arrangement.Bottom
            }

            LayoutAlignment.CENTER_HORIZONTAL -> {
                horizontal = Alignment.CenterHorizontally
            }

            LayoutAlignment.CENTER_VERTICAL -> {
                vertical = Arrangement.Center
            }

            LayoutAlignment.CENTER -> {
                horizontal = Alignment.CenterHorizontally
                vertical = Arrangement.Center
            }

            LayoutAlignment.TOP_START -> {
                horizontal = Alignment.Start
                vertical = Arrangement.Top
            }

            LayoutAlignment.TOP_END -> {
                horizontal = Alignment.End
                vertical = Arrangement.Top
            }

            LayoutAlignment.BOTTOM_START -> {
                horizontal = Alignment.Start
                vertical = Arrangement.Bottom
            }

            LayoutAlignment.BOTTOM_END -> {
                horizontal = Alignment.End
                vertical = Arrangement.Bottom
            }
        }
        Column(
            modifier = ModifierPreview(element).getModifier().then(modifier),
            horizontalAlignment = horizontal,
            verticalArrangement = vertical,
        ) {
            element.childs?.forEach { item ->
                val weightModifier = item.weight?.let { Modifier.weight(it) } ?: Modifier
                val itemModifier = item.weight?.let { Modifier.fillMaxHeight() } ?: Modifier
                Box(modifier = Modifier.then(weightModifier)) {
                    item.getPreview(viewModel).preview(itemModifier)
                }
            }
        }
    }
}