package pcui.beans

data class Page(
    val pageName: String, // 页面名字，路由的名字也角这个。
    val element: Element, // 页面元素

    val title: String, // 标题
    val screenType: ScreenType = ScreenType.ACTION, // 屏幕的类型（全屏(无状态栏)，有标题，没标题有状态栏）
)

enum class ScreenType {
    ACTION, NO_ACTION, FULL
}