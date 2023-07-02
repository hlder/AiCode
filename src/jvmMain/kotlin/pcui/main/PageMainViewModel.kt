package pcui.main

import pcui.beans.Page
import pcui.beans.TextAlign
import pcui.beans.TextWeight
import pcui.beans.elements.*
import java.awt.Color

class PageMainViewModel {
    val listPage: List<Page> = ArrayList<Page>().apply {
        add(
            Page(
                pageName = "HomePage",
                title = "主页",
                element =  ColumnElement(
                    id = "column1",
                    backgroundColor = Color.ORANGE.rgb,
                    childs = listOf(
                        TextElement(
                            id = "text1",
                            text = "aaaaa1",
                            textColor = Color.RED.rgb,
                            textSize = 20,
                            width = 10000,
                            height = 50,
                            backgroundColor = Color.GRAY.rgb,
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
                            dividerColor = Color.RED.rgb
                        ),
                        SpaceElement(
                            id = "space2",
                            height = 20
                        ),
                        TextElement(
                            id = "text2",
                            text = "aaaaa2",
                            backgroundColor = Color.BLUE.rgb
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
                            backgroundColor = Color.GREEN.rgb,
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
                                    textColor = Color.RED.rgb
                                )
                            )
                        ),
                        TextElement(
                            id = "text3",
                            text = "aaaaa2",
                            backgroundColor = Color.BLUE.rgb,
                            width = 100
                        ),
                        TextFieldElement(
                            id = "textField1",
                            text = "测试输入框",
                            textColor = Color.RED.rgb,
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
                    backgroundColor = Color.BLUE.rgb,
                    width = 100
                )
            )
        )
    }

}