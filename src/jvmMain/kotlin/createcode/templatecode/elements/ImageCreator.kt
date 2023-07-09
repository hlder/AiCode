package createcode.templatecode.elements

import createcode.projectInfo
import createcode.util.toCodeString
import pcui.beans.elements.ImageElement
import pcui.beans.elements.ImageFrom

class ImageCreator(element: ImageElement, space: String) : ElementCreator<ImageElement>(element, space) {

    override fun createUiCode(): String {
        val codeStr = if (element.imageFrom == ImageFrom.LOCAL) {
            addImportCode("import androidx.compose.foundation.Image")
            addImportCode("import androidx.compose.ui.res.painterResource")
            addImportCode("import ${projectInfo.packageName}.R")
            """
                Image(
                    painter = painterResource(id = R.mipmap.${element.image}),
                    contentDescription = "${element.contentDescription}",
                    %s
                )
            """.toCodeString(space)
        } else {
            addImportCode("import com.bumptech.glide.integration.compose.GlideImage")
            """
                GlideImage(
                    model = "${element.image}",
                    contentDescription = "${element.contentDescription}",
                    %s
                )
            """.toCodeString(space)
        }
        return codeStr.format(
            getModifier()
        )
    }

    override fun createLogicCode() = mutableListOf<String>()
}