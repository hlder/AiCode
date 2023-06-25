package pcui.beans

open class Element(
    val width: Int?, // 大于等于9999表示充满，其他则直接设置
    val height: Int?, // 大于等于9999表示充满，其他则直接设置
    val paddingTop: Int?,
    val paddingBottom: Int?,
    val paddingStart: Int?,
    val paddingEnd: Int? ,
    val backgroundColor: Int?, // 背景颜色
)

enum class TextWeight {
    W100, W200, W300, W400, W500, W600, W700, W800, W900, Thin, ExtraLight, Light, Normal, Medium, SemiBold, Bold, ExtraBold, Black
}

enum class TextAlign{
    Left, Right, Center, Start, End
}