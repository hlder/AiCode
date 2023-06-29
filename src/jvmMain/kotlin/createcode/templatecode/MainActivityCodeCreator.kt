package createcode.templatecode

import pcui.beans.Page
import createcode.util.FileUtils
import java.io.File

object MainActivityCodeCreator {
    fun createCode(file: File, packageName: String, listPage: List<Page>) {
        val startPageName = listPage[0].pageName
        val navigateCodes = StringBuffer()
        val navigateImportCodes = StringBuffer()
        listPage.forEach {
            PageCodeCreator.createPageCodeAndFile("${file.parentFile.absolutePath}\\pages\\${it.pageName.lowercase()}", it, packageName)
            // 路由的代码
            navigateCodes.append(PageCodeCreator.createRouterCode(it)+"\n")
            // import内容的代码
            navigateImportCodes.append(PageCodeCreator.createImportCode(it,packageName))
        }

        val content = "package ${packageName}\n" +
                "\n" +
                "import androidx.appcompat.app.AppCompatActivity\n" +
                "import android.os.Bundle\n" +
                "import androidx.compose.foundation.layout.consumeWindowInsets\n" +
                "import androidx.compose.runtime.Composable\n" +
                "import androidx.compose.ui.platform.ComposeView\n" +
                "import androidx.navigation.compose.NavHost\n" +
                "import androidx.navigation.compose.composable\n" +
                "import androidx.navigation.compose.rememberNavController\n" +
                "import com.google.accompanist.themeadapter.material.MdcTheme\n" +
                navigateImportCodes +
                "\n" +
                "class MainActivity : AppCompatActivity() {\n" +
                "    override fun onCreate(savedInstanceState: Bundle?) {\n" +
                "        super.onCreate(savedInstanceState)\n" +
                "\n" +
                "        setContentView(ComposeView(this).apply {\n" +
                "            consumeWindowInsets = false\n" +
                "            setContent {\n" +
                "                MainApp()\n" +
                "            }\n" +
                "        })\n" +
                "    }\n" +
                "}\n" +
                "\n" +
                "@Composable\n" +
                "fun MainApp() {\n" +
                "    val navController = rememberNavController()\n" +
                "    MdcTheme {\n" +
                "        NavHost(navController = navController, startDestination = \"${startPageName}\") {\n" +
                navigateCodes +
                "        }\n" +
                "    }\n" +
                "}\n"
        FileUtils.insertToFile(file, content)
    }
}