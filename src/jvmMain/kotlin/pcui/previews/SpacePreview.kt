package pcui.previews

import androidx.compose.foundation.layout.Spacer
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import pcui.beans.elements.SpaceElement
import pcui.main.PageMainViewModel
import pcui.previews.propertys.ModifierPreview

class SpacePreview(element: SpaceElement, viewModel: PageMainViewModel) : ElementPreview<SpaceElement>(element, viewModel) {
    @Composable
    override fun preview(modifier: Modifier) {
        Spacer(
            modifier = modifier.then(ModifierPreview(element).getModifier())
        )
    }
}