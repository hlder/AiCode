package createcode

import createcode.copyres.CopyRes
import createcode.templatecode.MainActivityCodeCreator
import createcode.util.FileUtils
import createcode.util.createFile
import pcui.beans.Page
import pcui.main.PageMainViewModel

fun main() {
    val viewModel = PageMainViewModel()
    testCreateAndroidProject(viewModel.listPage)
}

class ProjectInfo {
//    val filePath = "/Users/admin/Documents/temp" // mac电脑的地址
    val filePath = "F:\\temp" // windows电脑的地址
    val appName = "TestAiCodeProject"
    val packageName = "com.test.testaicode"
}

var projectInfo: ProjectInfo = ProjectInfo()

private fun testCreateAndroidProject(listPage: List<Page>) {
    val filePath = projectInfo.filePath
    val appName = projectInfo.appName
    val packageName = projectInfo.packageName
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
    // 复制图片等资源
    CopyRes.copy(listPage, "${filePath}\\${appName}")
}
