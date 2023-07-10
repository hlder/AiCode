package pcui.previews

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import pcui.beans.elements.ImageElement
import androidx.compose.foundation.Image
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.res.loadImageBitmap
import pcui.previews.propertys.ModifierPreview
import java.io.FileInputStream

class ImagePreview(element: ImageElement) : ElementPreview<ImageElement>(element) {
    @Composable
    override fun preview(modifier: Modifier) {
        val bitmap: ImageBitmap = remember { loadImageBitmap(FileInputStream(element.filePath)) }
        Image(
            painter = BitmapPainter(bitmap),
            contentDescription = element.contentDescription,
            modifier = modifier.then(ModifierPreview(element).getModifier())
        )
    }
}
