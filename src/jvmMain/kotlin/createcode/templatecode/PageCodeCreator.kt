package createcode.templatecode

import pcui.beans.Page
import createcode.util.FileUtils
import java.io.File

object PageCodeCreator {

    /**
     * 创建page的代码
     */
    fun createPageCodeAndFile(pageFilePath: String, page: Page, packageName: String) {
        val elementCodeCreator = ElementCodeCreator(page.element)
        val elementContent = elementCodeCreator.createElementCode()
        val elementNeedImport = elementCodeCreator.createElementImportCode()
        val pageContent = "package ${packageName}.pages\n" +
                "\n" +
                "import androidx.compose.runtime.Composable\n" +
                elementNeedImport +
                "\n" +
                "@Composable\n" +
                "fun ${page.pageName}() {\n" +
                "${elementContent}\n" +
                "}\n"

        val file = File("${pageFilePath}\\${page.pageName}.kt")
        FileUtils.insertToFile(file, pageContent)
    }

    /**
     * 创建路由的代码
     */
    fun createRouterCode(page: Page): String {
        return "            composable(\"${page.pageName}\") { ${page.pageName}() }"
    }

    /**
     * 创建import内容
     */
    fun createImportCode(page: Page, packageName: String): String {
        return "import ${packageName}.pages.${page.pageName}\n"
    }
}