package pcui.beans

//data class Element(
//    val type: ElementType, // 类型:TextView,TextField,Column,Row,Divider,TextButton,Button
//    val width: Int? = null, // 大于等于9999表示充满，其他则直接设置
//    val height: Int? = null, // 大于等于9999表示充满，其他则直接设置
//    val paddingTop: Int? = null,
//    val paddingBottom: Int? = null,
//    val paddingStart: Int? = null,
//    val paddingEnd: Int? = null,
//    val backgroundColor: Int? = null, // 背景颜色
//    val text: String? = null, // 文本内容
//    val textColor: Int? = null, //字体颜色
//    val textSize: Int? = null, // 字体大小，dp
//    val textWeight: TextWeight? = null, // 字体的粗细
//    val childs: List<Element>? = null, // 如果是row，column则会有子元素
//    val dividerColor: Int? = null, // 分割线的颜色
//)

open class Element(
    val type: ElementType, // 类型:TextView,TextField,Column,Row,Divider,TextButton,Button
    val width: Int?, // 大于等于9999表示充满，其他则直接设置
    val height: Int?, // 大于等于9999表示充满，其他则直接设置
    val paddingTop: Int?,
    val paddingBottom: Int?,
    val paddingStart: Int?,
    val paddingEnd: Int? ,
    val backgroundColor: Int?, // 背景颜色
)

enum class ElementType {
    TEXT, TEXT_FIELD, COLUMN, ROW, DIVIDER, TEXT_BUTTON, BUTTON
}

enum class TextWeight {
    W100, W200, W300, W400, W500, W600, W700, W800, W900, Thin, ExtraLight, Light, Normal, Medium, SemiBold, Bold, ExtraBold, Black
}