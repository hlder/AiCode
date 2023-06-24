import java.io.File

fun main() {
    val file1 = File("./androidCodeTemplate")
    println("==========file1:${file1.absoluteFile}")
    file1.listFiles()?.forEach {
        println("============item:${it.absoluteFile}")
    }

//    val file = File("F:\\temp\\a.txt")
//    file.writeText("aaaaaaaaaaaaaaabbbbbbbbbbbbb")
//    file.copyTo(File("F:\\temp\\b.txt"),true)
//    println("=================main")
}