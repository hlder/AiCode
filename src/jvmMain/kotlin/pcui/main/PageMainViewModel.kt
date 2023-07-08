package pcui.main

import androidx.compose.ui.graphics.Color
import pcui.beans.Page
import pcui.beans.TextAlign
import pcui.beans.TextWeight
import pcui.beans.elements.*

class PageMainViewModel {
    val listPage: List<Page> = ArrayList<Page>().apply {
        add(
            Page(
                pageName = "HomePage",
                title = "主页",
                backgroundColor = Color(0xFFf4f4f4),
                element = RowElement(
                    id = "topActionLayout",
                    backgroundColor = Color.White,
                    paddingTop = 10,
                    paddingBottom = 10,
                    childs = listOf(
                        ColumnElement(
                            id = "actionItemLayout1",
                            weight = 1f,
                            align = LayoutAlignment.CENTER,
                            childs = listOf(
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
                                    text = "外卖"
                                )
                            )
                        ),
                        ColumnElement(
                            id = "actionItemLayout2",
                            weight = 1f,
                            align = LayoutAlignment.CENTER,
                            childs = listOf(
                                ImageElement(
                                    id = "imageElement2",
                                    image = "icon_home_maicai",
                                    imageFrom = ImageFrom.LOCAL,
                                    contentDescription = "美团买菜",
                                    filePath = "G:\\temp\\icons\\icon_home_maicai.png",
                                    width = 50,
                                    height = 50
                                ),
                                TextElement(
                                    id = "actionItemText2",
                                    textColor = Color(0xFF353535),
                                    text = "美团买菜"
                                )
                            )
                        ),
                        ColumnElement(
                            id = "actionItemLayout3",
                            weight = 1f,
                            align = LayoutAlignment.CENTER,
                            childs = listOf(
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
                                    text = "超市便利"
                                )
                            )
                        ),
                        ColumnElement(
                            id = "actionItemLayout4",
                            weight = 1f,
                            align = LayoutAlignment.CENTER,
                            childs = listOf(
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
                                    text = "品质百货"
                                )
                            )
                        ),
                        ColumnElement(
                            id = "actionItemLayout5",
                            weight = 1f,
                            align = LayoutAlignment.CENTER,
                            childs = listOf(
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
                                    textWeight = TextWeight.Bold
                                )
                            )
                        )
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
                    childs = listOf(
                        TextElement(
                            id = "text1",
                            text = "aaaaa1",
                            textColor = Color.Red,
                            textSize = 20,
                            width = 10000,
                            height = 50,
                            backgroundColor = Color.Gray,
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
                            childs = listOf(
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
                            text = "测试输入框",
                            textColor = Color.Red,
                            textSize = 20,
                            textWeight = TextWeight.Bold,
                        )
                    ),
                )
            )
        )
        add(
            Page(
                pageName = "TestPage",
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