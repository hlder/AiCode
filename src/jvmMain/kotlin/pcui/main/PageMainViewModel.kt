package pcui.main

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import pcui.beans.Page
import androidx.compose.ui.text.style.TextAlign
import pcui.beans.Element
import pcui.beans.elements.*

class PageMainViewModel {
    // 元素移动位置的版本号
    val movePositionVersion = mutableStateOf(0)
    // 当前选择的element
    val nowSelectedElement = mutableStateOf<Element?>(null)
    // page的列表
    val listPage: List<Page> = ArrayList<Page>().apply {
        add(
            Page(
                pageName = "HomePage",
                title = "主页",
                backgroundColor = Color(0xFFf4f4f4),
                element = ColumnElement(
                    id = "homeContentLayout",
                    paddingStart = 10,
                    paddingEnd = 10,
                    isNeedScroll = true,
                    childs = mutableListOf(
                        SpaceElement(
                            id = "home_space1",
                            height = 10
                        ),
                        ColumnElement(
                            id = "homeTopLayout1",
                            backgroundColor = Color.White,
                            backgroundRounded = 10,
                            paddingTop = 10,
                            paddingBottom = 10,
                            childs = mutableListOf(
                                TextElement(
                                    id = "top1Text",
                                    text = "美食百货，随叫随到",
                                    paddingStart = 20,
                                    textColor = Color(0xFF333333),
                                    textWeight = TextWeight.Bold,
                                    textSize = 14
                                ),
                                RowElement(
                                    id = "topActionLayout",
                                    paddingTop = 10,
                                    paddingBottom = 10,
                                    childs = mutableListOf(
                                        ColumnElement(
                                            id = "actionItemLayout1",
                                            weight = 1f,
                                            align = LayoutAlignment.CENTER,
                                            childs = mutableListOf(
                                                ImageElement(
                                                    id = "imageElement1",
                                                    image = "icon_home_waimai",
                                                    imageFrom = ImageFrom.LOCAL,
                                                    contentDescription = "外卖",
                                                    filePath = "G:\\temp\\icons\\icon_home_waimai.png",
                                                    width = 50,
                                                    height = 50
                                                ),
                                                TextElement(
                                                    id = "actionItemText1",
                                                    textColor = Color(0xFF353535),
                                                    text = "外卖",
                                                    textSize = 14,
                                                    paddingTop = 5
                                                )
                                            )
                                        ),
                                        ColumnElement(
                                            id = "actionItemLayout2",
                                            weight = 1f,
                                            align = LayoutAlignment.CENTER,
                                            childs = mutableListOf(
                                                ImageElement(
                                                    id = "imageElement2",
                                                    image = "icon_home_maicai",
                                                    imageFrom = ImageFrom.LOCAL,
                                                    contentDescription = "美团买菜",
                                                    filePath = "G:\\temp\\icons\\icon_home_maicai.png",
                                                    width = 50,
                                                    height = 50,
                                                ),
                                                TextElement(
                                                    id = "actionItemText2",
                                                    textColor = Color(0xFF353535),
                                                    text = "美团买菜",
                                                    textSize = 14,
                                                    paddingTop = 5,
                                                )
                                            )
                                        ),
                                        ColumnElement(
                                            id = "actionItemLayout3",
                                            weight = 1f,
                                            align = LayoutAlignment.CENTER,
                                            childs = mutableListOf(
                                                ImageElement(
                                                    id = "imageElement3",
                                                    image = "icon_home_chaoshi",
                                                    imageFrom = ImageFrom.LOCAL,
                                                    contentDescription = "超市便利",
                                                    filePath = "G:\\temp\\icons\\icon_home_chaoshi.png",
                                                    width = 50,
                                                    height = 50
                                                ),
                                                TextElement(
                                                    id = "actionItemText3",
                                                    textColor = Color(0xFF353535),
                                                    text = "超市便利",
                                                    textSize = 14,
                                                    paddingTop = 5
                                                )
                                            )
                                        ),
                                        ColumnElement(
                                            id = "actionItemLayout4",
                                            weight = 1f,
                                            align = LayoutAlignment.CENTER,
                                            childs = mutableListOf(
                                                ImageElement(
                                                    id = "imageElement4",
                                                    image = "icon_home_baihuo",
                                                    imageFrom = ImageFrom.LOCAL,
                                                    contentDescription = "品质百货",
                                                    filePath = "G:\\temp\\icons\\icon_home_baihuo.png",
                                                    width = 50,
                                                    height = 50
                                                ),
                                                TextElement(
                                                    id = "actionItemText4",
                                                    textColor = Color(0xFF353535),
                                                    text = "品质百货",
                                                    textSize = 14,
                                                    paddingTop = 5
                                                )
                                            )
                                        ),
                                        ColumnElement(
                                            id = "actionItemLayout5",
                                            weight = 1f,
                                            align = LayoutAlignment.CENTER,
                                            childs = mutableListOf(
                                                ImageElement(
                                                    id = "imageElement5",
                                                    image = "icon_home_buy_yao",
                                                    imageFrom = ImageFrom.LOCAL,
                                                    contentDescription = "看病买药",
                                                    filePath = "G:\\temp\\icons\\icon_home_buy_yao.png",
                                                    width = 50,
                                                    height = 50
                                                ),
                                                TextElement(
                                                    id = "actionItemText5",
                                                    text = "看病买药",
                                                    textColor = Color(0xFF353535),
                                                    textSize = 14,
                                                    paddingTop = 5
                                                )
                                            )
                                        )
                                    )
                                )
                            )
                        ),
                    )
                )
            )
        )
        add(
            Page(
                pageName = "TestPage",
                title = "测试",
                element =  ColumnElement(
                    id = "column1",
                    backgroundColor = Color.Yellow,
                    width = Int.MAX_VALUE,
                    height = Int.MAX_VALUE,
                    childs = mutableListOf(
                        TextElement(
                            id = "text1",
                            text = "aaaaa1",
                            textColor = Color.Red,
                            textSize = 20,
                            width = 10000,
                            height = 50,
                            backgroundColor = Color.Cyan,
                            textWeight = TextWeight.Bold,
                            textAlign = TextAlign.Center
                        ),
                        SpaceElement(
                            id = "space1",
                            height = 20
                        ),
                        DividerElement(
                            id = "divider1",
                            width = 10000,
                            height = 1,
                            dividerColor = Color.Red
                        ),
                        SpaceElement(
                            id = "space2",
                            height = 20
                        ),
                        TextElement(
                            id = "text2",
                            text = "aaaaa2",
                            backgroundColor = Color.Blue
                        ),
                        ButtonElement(
                            id = "button1",
                            text = "跳转TestPage页面",
                            buttonAction = ButtonActionSkipPage(Action("TestPage"))
                        ),
                        TextButtonElement(
                            id = "textButton1",
                            text = "textButton"
                        ),
                        RowElement(
                            id = "row1",
                            width = 250,
                            height = 80,
                            backgroundColor = Color.Green,
                            childs = mutableListOf(
                                TextElement(
                                    id = "text5",
                                    text = "text1"
                                ),
                                SpaceElement(
                                    id = "space3",
                                    width = 10
                                ),
                                TextElement(
                                    id = "text6",
                                    text = "text2",
                                    textColor = Color.Red
                                )
                            )
                        ),
                        TextElement(
                            id = "text3",
                            text = "aaaaa2",
                            backgroundColor = Color.Blue,
                            width = 100
                        ),
                        TextFieldElement(
                            id = "textField1",
                            text = "",
                            hintText = "测试输入框",
                            textColor = Color.Red,
                            paddingTop = 10,
                            paddingBottom = 10,
                            paddingStart = 10,
                            paddingEnd = 10,
                            width = Int.MAX_VALUE,
                            backgroundColor = Color.White,
                            textWeight = TextWeight.Bold,
                        )
                    ),
                )
            )
        )
        add(
            Page(
                pageName = "TestPage2",
                title = "测试页",
                element = TextElement(
                    id = "text4",
                    text = "TestPage",
                    backgroundColor = Color.Blue,
                    width = 100
                )
            )
        )
    }
}