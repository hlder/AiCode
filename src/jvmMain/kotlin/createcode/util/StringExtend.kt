package createcode.util

fun String.toCodeString(space: String = ""): String {
    return this.trimIndent().lines().map {
        if (it.trim().startsWith("%s")) {
            it.trim()
        } else {
            "$space$it\n"
        }
    }.let { list ->
        val sb = StringBuffer()
        list.forEach {
            sb.append(it)
        }
        sb.toString()
    }
}