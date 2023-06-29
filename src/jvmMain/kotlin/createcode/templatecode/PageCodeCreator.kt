package createcode.templatecode

import pcui.beans.Page
import createcode.util.FileUtils
import createcode.util.toCodeString
import java.io.File

object PageCodeCreator {

    /**
     * 创建page的代码
     */
    fun createPageCodeAndFile(pageFilePath: String, page: Page, packageName: String) {
        val elementCodeCreator = ElementCodeCreator(page.element)
        val elementContent = elementCodeCreator.createElementCode()
        val elementNeedImport = elementCodeCreator.createElementImportCode()

        val (scaffoldContent, importCode) = createScaffoldCreator()
        importCode.forEach {
            elementNeedImport.add(it)
        }

        val elementNeedImportStr = StringBuffer()
        elementNeedImport.forEach {
            elementNeedImportStr.append("${it}\n")
        }

        val pageContent = """
            package ${packageName}.pages.${page.pageName.lowercase()}
            
            import androidx.compose.runtime.Composable
            %s
            
            @Composable
            fun ${page.pageName}() {
                %s
            }
            
            @Composable
            fun PageView() {
                %s
            }
        """.toCodeString("").format(
            elementNeedImportStr.toString(),
            scaffoldContent,
            elementContent
        )
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
        return "import ${packageName}.pages.${page.pageName.lowercase()}.${page.pageName}\n"
    }
}