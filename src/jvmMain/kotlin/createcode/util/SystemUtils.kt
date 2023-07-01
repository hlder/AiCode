package createcode.util

val osName = System.getProperty("os.name")

fun isWindows():Boolean{
    return osName.startsWith("Windows")
}