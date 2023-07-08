package createcode.templatecode.elements.propertys

import androidx.compose.ui.graphics.Color
import java.util.*
import kotlin.collections.HashSet

class ColorCreator {
    fun createCode(space: String, color: Color): Pair<String, HashSet<String>> {
        return Pair(
            "${space}color = ${getColorCodeStr(color)},\n",
            hashSetOf("import androidx.compose.ui.graphics.Color")
        )
    }
}

fun getColorCodeStr(color: Color): String {
    return when (color) {
        Color.Black -> {
            "Color.Black"
        }
        Color.DarkGray -> {
            "Color.DarkGray"
        }
        Color.Gray -> {
            "Color.Gray"
        }
        Color.LightGray -> {
            "Color.LightGray"
        }
        Color.White -> {
            "Color.White"
        }
        Color.Red -> {
            "Color.Red"
        }
        Color.Green -> {
            "Color.Green"
        }
        Color.Blue -> {
            "Color.Blue"
        }
        Color.Yellow -> {
            "Color.Yellow"
        }
        Color.Cyan -> {
            "Color.Cyan"
        }
        Color.Magenta -> {
            "Color.Magenta"
        }
        Color.Transparent -> {
            "Color.Transparent"
        }
        Color.Unspecified -> {
            "Color.Unspecified"
        }
        else -> {
            color.toColorString()
        }
    }
}

private fun Color.toColorString():String {
    val alpha = (this.alpha * 255).toInt()
    val red = (this.red * 255).toInt()
    val green = (this.green * 255).toInt()
    val blue = (this.blue * 255).toInt()
    return "Color(0x${Integer.toHexString(alpha).toHexFilledUpString()}${Integer.toHexString(red).toHexFilledUpString()}${Integer.toHexString(green).toHexFilledUpString()}${Integer.toHexString(blue).toHexFilledUpString()})"
}

private fun String.toHexFilledUpString(): String {
    return if (length == 1) {
        "0${this}".uppercase(Locale.getDefault())
    } else {
        this.uppercase(Locale.getDefault())
    }
}
