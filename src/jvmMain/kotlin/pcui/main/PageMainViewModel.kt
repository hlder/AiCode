package pcui.main

import pcui.beans.Element
import pcui.beans.Page
import pcui.beans.TextAlign
import pcui.beans.TextWeight
import pcui.beans.elements.*
import java.awt.Color

class PageMainViewModel {
    val listPage: List<Page> = ArrayList<Page>().apply {
        add(
            Page(
                "HomePage",
                ColumnElement(
                    backgroundColor = Color.BLACK.rgb,
                    childs = listOf(
                        TextElement(
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
                            height = 20
                        ),
                        DividerElement(
                            width = 10000,
                            height = 1,
                            dividerColor = Color.RED.rgb
                        ),
                        SpaceElement(
                            height = 20
                        ),
                        TextElement(
                            text = "aaaaa2",
                            backgroundColor = Color.BLUE.rgb
                        ),
                        ButtonElement(
                            text = "Button"
                        ),
                        TextButtonElement(
                            text = "textButton"
                        ),
                        RowElement(
                            width = 250,
                            height = 80,
                            backgroundColor = Color.GREEN.rgb,
                            childs = listOf(
                                TextElement(
                                    text = "text1"
                                ),
                                SpaceElement(
                                    width = 10
                                ),
                                TextElement(
                                    text = "text2",
                                    textColor = Color.RED.rgb
                                )
                            )
                        ),
                        TextElement(
                            text = "aaaaa2",
                            backgroundColor = Color.BLUE.rgb,
                            width = 100
                        ),
                        TextFieldElement(
                            text = "测试输入框",
                            textColor = Color.RED.rgb,
                            textSize = 20,
                            textWeight = TextWeight.Bold,
                        )
                    ),
                )
            )
        )
    }

}