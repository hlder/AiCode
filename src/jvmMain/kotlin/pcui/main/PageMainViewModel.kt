package pcui.main

import pcui.beans.Element
import pcui.beans.Page
import pcui.beans.TextWeight
import pcui.beans.elements.*
import java.awt.Color

class PageMainViewModel {
    val listPage: List<Page> = ArrayList<Page>().apply {
        add(
            Page(
                "HomePage",
                ColumnElement(
                    childs = listOf(
                        TextElement(
                            text = "aaaaa1",
                            textColor = Color.RED.rgb,
                            textSize = 20,
                            width = 10000,
                            height = 50,
                            backgroundColor = Color.BLACK.rgb,
                            textWeight = TextWeight.Bold
                        ),
                        DividerElement(
                            width = 10000,
                            height = 1,
                            dividerColor = Color.RED.rgb
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
                        TextElement(
                            text = "aaaaa2",
                            backgroundColor = Color.BLUE.rgb,
                            width = 100,
                            height = 10000
                        )
                    ),
                    backgroundColor = Color.YELLOW.rgb
                )
            )
        )
    }

}