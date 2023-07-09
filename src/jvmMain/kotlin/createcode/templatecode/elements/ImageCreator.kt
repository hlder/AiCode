package createcode.templatecode.elements

import createcode.projectInfo
import createcode.util.ConstantValues.ITEM_SPACE
import createcode.util.toCodeString
import pcui.beans.elements.ImageElement
import pcui.beans.elements.ImageFrom

class ImageCreator(element: ImageElement) : ElementCreator<ImageElement>(element) {
    private val importSet = hashSetOf<String>()

    override fun createUiCode(space: String): String {
        val codeStr = if (element.imageFrom == ImageFrom.LOCAL) {
            importSet.add("import androidx.compose.foundation.Image")
            importSet.add("import androidx.compose.ui.res.painterResource")
            importSet.add("import ${projectInfo.packageName}.R")
            """
                Image(
                    painter = painterResource(id = R.mipmap.${element.image}),
                    contentDescription = "${element.contentDescription}",
                    %s
                )
            """.toCodeString(space)
        } else {
            importSet.add("import com.bumptech.glide.integration.compose.GlideImage")
            """
                GlideImage(
                    model = "${element.image}",
                    contentDescription = "${element.contentDescription}",
                    %s
                )
            """.toCodeString(space)
        }
        return codeStr.format(
            getModifier(space + ITEM_SPACE)
        )
    }

    override fun createLogicCode(space: String): String {
        return ""
    }

    override fun createImportCode(): HashSet<String> {
        return importSet
    }
}