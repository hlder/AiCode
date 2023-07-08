package createcode.copyres

import createcode.util.FileUtils
import pcui.beans.Page
import pcui.beans.elements.ImageElement
import pcui.beans.foreach

object CopyRes {
    fun copy(listPage: List<Page>, projectFilePath: String) {
        listPage.forEach { page ->
            page.element.foreach { item -> // 遍历整个element元素树
                if (item is ImageElement) {
                    FileUtils.copyFile(item.filePath, "${projectFilePath}\\app\\src\\main\\res\\mipmap-xxhdpi\\${item.image}.png")
                }
            }
        }
    }
}