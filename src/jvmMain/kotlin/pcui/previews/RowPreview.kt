package pcui.previews

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import pcui.beans.elements.LayoutAlignment
import pcui.beans.elements.RowElement
import pcui.main.PageMainViewModel
import pcui.previews.propertys.ModifierPreview

class RowPreview(element: RowElement, viewModel: PageMainViewModel) :
    ElementPreview<RowElement>(element, viewModel) {
    @Composable
    override fun previewImpl(modifier: Modifier) {
        var horizontal: Arrangement.Horizontal = Arrangement.Start
        var vertical: Alignment.Vertical = Alignment.Top
        when (element.align) {
            LayoutAlignment.START -> {
                horizontal = Arrangement.Start
            }

            LayoutAlignment.END -> {
                horizontal = Arrangement.End
            }

            LayoutAlignment.TOP -> {
                vertical = Alignment.Top
            }

            LayoutAlignment.BOTTOM -> {
                vertical = Alignment.Bottom
            }

            LayoutAlignment.CENTER_HORIZONTAL -> {
                horizontal = Arrangement.Center
            }

            LayoutAlignment.CENTER_VERTICAL -> {
                vertical = Alignment.CenterVertically
            }

            LayoutAlignment.CENTER -> {
                horizontal = Arrangement.Center
                vertical = Alignment.CenterVertically
            }

            LayoutAlignment.TOP_START -> {
                horizontal = Arrangement.Start
                vertical = Alignment.Top
            }

            LayoutAlignment.TOP_END -> {
                horizontal = Arrangement.End
                vertical = Alignment.Top
            }

            LayoutAlignment.BOTTOM_START -> {
                horizontal = Arrangement.Start
                vertical = Alignment.Bottom
            }

            LayoutAlignment.BOTTOM_END -> {
                horizontal = Arrangement.End
                vertical = Alignment.Bottom
            }

            else -> {}
        }
        Row(
            modifier = ModifierPreview(element).getModifier().then(modifier),
            horizontalArrangement = horizontal,
            verticalAlignment = vertical,
        ) {
            element.childs?.forEach { item ->
                val weightModifier = item.weight?.let { Modifier.weight(it) } ?: Modifier
                val itemModifier = item.weight?.let { Modifier.fillMaxWidth() } ?: Modifier
                Box(modifier = Modifier.then(weightModifier)) {
                    item.getPreview(viewModel).preview(itemModifier)
                }
            }
        }
    }
}