package createcode.util

/**
 * 字符串转为代码字符串
 */
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

/**
 * list转为代码字符串
 */
fun List<String>.toCodeString(): String {
    val sb = StringBuffer()
    this.forEach {
        sb.append(it + "\n")
    }
    return sb.toString()
}