package createcode

import createcode.templatecode.MainActivityCodeCreator
import createcode.util.FileUtils
import pcui.beans.Page
import pcui.main.PageMainViewModel
import java.io.File

fun main() {
    val viewModel = PageMainViewModel()
    testCreateAndroidProject(viewModel.listPage)
}

private fun testCreateAndroidProject(listPage: List<Page>) {
    val filePath = "F:\\temp"
    val appName = "TestAiCodeProject"
    val packageName = "com.test.testaicode"
    // 1.复制模板代码
    FileUtils.copyTemplateCode(filePath, appName, packageName)
    // 2.修改包名
    val appBuildGradleFile = File("${filePath}\\${appName}\\app\\build.gradle.kts")
    val manifestFile = File("${filePath}\\${appName}\\app\\src\\main\\AndroidManifest.xml")
    FileUtils.replaceFileContent(appBuildGradleFile, "com.aicode.templatepackage", packageName)
    FileUtils.replaceFileContent(manifestFile, "com.aicode.templatepackage", packageName)
    // 3.创建MainActivity
    val activityFilePath = "${filePath}\\${appName}\\app\\src\\main\\java\\${packageName.replace(".", "\\")}\\MainActivity.kt"
    MainActivityCodeCreator.createCode(File(activityFilePath), packageName, listPage)
}
