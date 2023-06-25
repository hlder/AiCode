package createcode.templatecode.elements.propertys

class ColorCreator() {
    fun createCode(space: String, color: Int): Pair<String, HashSet<String>> {
        return Pair("${space}color = Color(${color}),\n", hashSetOf("import androidx.compose.ui.graphics.Color"))
    }
}