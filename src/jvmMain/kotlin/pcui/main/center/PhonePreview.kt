package pcui.main.center

import androidx.compose.runtime.Composable
import pcui.beans.Page

@Composable
fun PhonePreview(page: Page) {
    page.element.getPreview().preview()
}
