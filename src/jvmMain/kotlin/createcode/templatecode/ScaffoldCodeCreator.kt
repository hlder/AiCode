package createcode.templatecode

import createcode.templatecode.elements.propertys.getColorCodeStr
import createcode.util.ConstantValues.ITEM_SPACE
import createcode.util.toCodeString
import pcui.beans.Page
import pcui.beans.ScreenType

fun createScaffoldCreator(page: Page): Pair<String, HashSet<String>> {
    val pageBackgroundColor = page.backgroundColor?.let {
        getColorCodeStr(it)
    } ?: "Color.White"
    val content = """
        Scaffold (
            backgroundColor = ${pageBackgroundColor},
            %s
            %s
            %s
            %s
        ){
            Box(modifier = Modifier.padding(it)){
                PageView(navController)
            }
        }
    """.toCodeString(ITEM_SPACE).format(
        createTopActionBarCode(page),
        createBottomBarCode(page),
        createFloatingActionButtonCode(page),
        createDrawerContentCode(page),
    )

    val importCode = hashSetOf(
        "import androidx.compose.material.BottomAppBar",
        "import androidx.compose.material.ExtendedFloatingActionButton",
        "import androidx.compose.material.Scaffold",
        "import androidx.compose.material.TopAppBar",
        "import androidx.compose.foundation.layout.Box",
        "import androidx.compose.foundation.layout.padding",
        "import androidx.compose.material.Divider",
        "import androidx.compose.ui.Modifier",
        "import androidx.compose.ui.unit.dp",
        "import androidx.compose.ui.unit.sp"
    )
    return Pair(content, importCode)
}

/**
 * 创建actionbar的代码
 */
private fun createTopActionBarCode(page: Page): String {
    return if (page.screenType == ScreenType.ACTION) {
        """
            topBar = {
                TopAppBar { 
                    Text(
                        text = "${page.title}",
                        modifier = Modifier.padding(start = 10.dp),
                    )
                }
            },
        """.toCodeString(ITEM_SPACE + ITEM_SPACE)
    } else {
        ""
    }
}

/**
 * 创建bottomBar的代码
 */
private fun createBottomBarCode(page: Page): String {
//    return """
//        bottomBar = {
//            BottomAppBar { Text(text = "bottomAppBar") }
//        },
//    """.toCodeString(ITEM_SPACE + ITEM_SPACE)
    return ""
}

/**
 * 右下角的悬浮控件
 */
private fun createFloatingActionButtonCode(page: Page): String {
//    return """
//        floatingActionButton = {
//            ExtendedFloatingActionButton(
//                text = { Text("Show snackbar") },
//                onClick = {
//                }
//            )
//        },
//    """.toCodeString(ITEM_SPACE + ITEM_SPACE)
    return ""
}

/**
 * 抽屉代码
 */
private fun createDrawerContentCode(page: Page): String {
//    return """
//        drawerContent = {
//            Text("Drawer title", modifier = Modifier.padding(16.dp))
//            Divider()
//        },
//    """.toCodeString(ITEM_SPACE + ITEM_SPACE)
    return ""
}