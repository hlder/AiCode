package createcode.templatecode

import pcui.beans.Page
import createcode.util.FileUtils
import createcode.util.createFile
import createcode.util.toCodeString

object PageCodeCreator {

    /**
     * 创建page的代码
     */
    fun createPageCodeAndFile(pageFilePath: String, page: Page, packageName: String) {
        val elementCodeCreator = ElementCodeCreator(page.element)
        // ui代码
        val elementUiCode = elementCodeCreator.createUiCode()
        // 逻辑代码
        val elementLogicCode = elementCodeCreator.createLogicCode()
        // import代码
        val elementNeedImport = elementCodeCreator.createElementImportCode()

        val (scaffoldContent, importCode) = createScaffoldCreator(page)
        importCode.forEach {
            elementNeedImport.add(it)
        }
        elementNeedImport.add("import androidx.navigation.NavHostController")

        // import代码
        val elementNeedImportStr = StringBuffer()
        elementNeedImport.forEach {
            elementNeedImportStr.append("${it}\n")
        }

        val pageContent = """
            package ${packageName}.pages.${page.pageName.lowercase()}
            
            import androidx.compose.runtime.Composable
            %s
            
            @Composable
            fun ${page.pageName}(navController: NavHostController) {
                %s
            }
            
            @Composable
            fun PageView(navController: NavHostController) {
                %s
                %s
            }
        """.toCodeString("").format(
            elementNeedImportStr.toString(),
            scaffoldContent,
            elementLogicCode,
            elementUiCode
        )
        val file = createFile("${pageFilePath}\\${page.pageName}.kt")
        FileUtils.insertToFile(file, pageContent)
    }

    /**
     * 创建路由的代码
     */
    fun createRouterCode(page: Page): String {
        return "            composable(\"${page.pageName}\") { ${page.pageName}(navController) }"
    }

    /**
     * 创建import内容
     */
    fun createImportCode(page: Page, packageName: String): String {
        return "import ${packageName}.pages.${page.pageName.lowercase()}.${page.pageName}\n"
    }
}