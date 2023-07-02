package createcode.util

import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.io.FileWriter

fun createFile(filePath: String): File {
    println("=======isWindows:${isWindows()} filePath:$filePath")
    return if (isWindows()) {
        File(filePath)
    } else {
        File(filePath.replace("\\", "/"))
    }
}

object FileUtils {
    /**
     * 复制模板代码
     */
    fun copyTemplateCode(filePath: String, appName: String, packageName: String) {
        val fromFileDir = createFile("./androidCodeTemplate")
        copyFile(fromFileDir, "${filePath}\\${appName}")
    }

    /**
     * 递归复制文件
     */
    private fun copyFile(fromFile: File, toFilePath: String) {
        if (fromFile.isDirectory) {
            fromFile.listFiles()?.forEach {
//                println("----------from:${fromFile.absolutePath}")
                val str = if (isWindows()) {
                    fromFile.absolutePath.split("\\") // window电脑是正斜杠
                }else{
                    fromFile.absolutePath.split("/") // mac电脑室反斜杠
                }
                copyFile(it, toFilePath + "\\" + str[str.size - 1])
            }
        } else {
            // 复制的路径，删除androidCodeTemplate文件夹
            val filePath = toFilePath.replace("androidCodeTemplate", "") + "\\${fromFile.name}"
//            println("===========filePath")
            createFile(filePath).let {
                if (!it.exists()) {
                    fromFile.copyTo(createFile(filePath))
                }
            }
        }
    }

    fun replaceFileContent(file: File, oldStr: String, newStr: String) {
        val sb = StringBuffer()
        val br = BufferedReader(FileReader(file))
        var line = br.readLine()
        while (line != null) {
            sb.append("${line.replace(oldStr, newStr)}\n")
            line = br.readLine()
        }
        br.close()

        val fileWriter = FileWriter(file)
        fileWriter.write(sb.toString())
        fileWriter.close()
    }

    fun insertToFile(file: File, content: String) {
        if (!file.parentFile.exists()) {
            file.parentFile.mkdirs()
        }
        if (!file.exists()) {
            file.createNewFile()
        }
        val fileWriter = FileWriter(file)
        fileWriter.write(content)
        fileWriter.close()
    }
}