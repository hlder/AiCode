package pcui.main.right

fun Int.toColorHexString(): String {
    return Integer.toHexString(this).run {
        if (length == 1) {
            "0${this}"
        } else {
            this
        }
    }
}

fun String.toColorInt(): Int {
    return Integer.parseInt(this, 16)
}