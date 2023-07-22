package pcui.previews

import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import pcui.beans.elements.DividerElement
import pcui.main.PageMainViewModel
import pcui.previews.propertys.ModifierPreview

class DividerPreview(element: DividerElement, viewModel: PageMainViewModel) : ElementPreview<DividerElement>(element, viewModel) {
    @Composable
    override fun preview(modifier: Modifier) {
        Divider(
            color = element.dividerColor ?: MaterialTheme.colors.onSurface.copy(alpha = 0.12f),
            modifier = modifier.then(ModifierPreview(element).getModifier())
        )
    }
}