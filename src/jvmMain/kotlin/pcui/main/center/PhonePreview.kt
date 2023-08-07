package pcui.main.center

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import pcui.beans.Page
import pcui.main.PageMainViewModel

@Composable
fun PhonePreview(page: Page, viewModel: PageMainViewModel) {
    page.element.getPreview(viewModel).preview(Modifier)
}
