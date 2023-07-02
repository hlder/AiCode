package createcode.templatecode

import createcode.util.ConstantValues.ITEM_SPACE
import createcode.util.toCodeString

fun createScaffoldCreator(): Pair<String, HashSet<String>> {
    val content = """
        Scaffold (
            topBar = {
                TopAppBar { Text(text = "aaaaaaa") }
            },
            bottomBar = {
                BottomAppBar { Text(text = "bottomAppBar") }
            },
            drawerContent = {
                Text("Drawer title", modifier = Modifier.padding(16.dp))
                Divider()
            },
            floatingActionButton = {
                ExtendedFloatingActionButton(
                    text = { Text("Show snackbar") },
                    onClick = {
                    }
                )
            }
        ){
            Box(modifier = Modifier.padding(it)){
                PageView(navController)
            }
        }
    """.toCodeString(ITEM_SPACE)

    val importCode = hashSetOf(
        "import androidx.compose.material.BottomAppBar",
        "import androidx.compose.material.ExtendedFloatingActionButton",
        "import androidx.compose.material.Scaffold",
        "import androidx.compose.material.TopAppBar",
        "import androidx.compose.foundation.layout.Box",
        "import androidx.compose.foundation.layout.padding",
        "import androidx.compose.material.Divider"
    )
    return Pair(content, importCode)
}