package createcode

import createcode.templatecode.MainActivityCodeCreator
import createcode.util.FileUtils
import createcode.util.createFile
import pcui.beans.Page
import pcui.main.PageMainViewModel

fun main() {
    val viewModel = PageMainViewModel()
    testCreateAndroidProject(viewModel.listPage)
}

private fun testCreateAndroidProject(listPage: List<Page>) {
    val filePath = "/Users/admin/Documents/temp"
    val appName = "TestAiCodeProject"
    val packageName = "com.test.testaicode"
    // 1.复制模板代码
    FileUtils.copyTemplateCode(filePath, appName, packageName)
    // 2.修改包名
    val appBuildGradleFile = createFile("${filePath}\\${appName}\\app\\build.gradle.kts")
    val manifestFile = createFile("${filePath}\\${appName}\\app\\src\\main\\AndroidManifest.xml")
    FileUtils.replaceFileContent(appBuildGradleFile, "com.aicode.templatepackage", packageName)
    FileUtils.replaceFileContent(manifestFile, "com.aicode.templatepackage", packageName)
    // 3.创建MainActivity
    val activityFilePath = "${filePath}\\${appName}\\app\\src\\main\\java\\${packageName.replace(".", "\\")}\\MainActivity.kt"
    MainActivityCodeCreator.createCode(createFile(activityFilePath), packageName, listPage)
}
