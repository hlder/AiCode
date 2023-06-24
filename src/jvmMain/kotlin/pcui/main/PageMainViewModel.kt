package pcui.main

import pcui.beans.Element
import pcui.beans.ElementType
import pcui.beans.Page
import pcui.beans.TextWeight
import java.awt.Color

class PageMainViewModel {
    val listPage: List<Page> = ArrayList<Page>().apply {
        add(
            Page(
                "HomePage",
                Element(
                    ElementType.COLUMN,
                    childs = listOf(
                        Element(
                            ElementType.TEXT,
                            text = "aaaaa1",
                            textColor = Color.RED.rgb,
                            textSize = 20,
                            width = 10000,
                            height = 50,
                            backgroundColor = Color.BLACK.rgb,
                            textWeight = TextWeight.Bold
                        ),
                        Element(
                            ElementType.DIVIDER,
                            width = 10000,
                            height = 1,
                            dividerColor = Color.RED.rgb
                        ),
                        Element(
                            ElementType.TEXT,
                            text = "aaaaa2",
                            backgroundColor = Color.BLUE.rgb
                        ),
                        Element(
                            ElementType.TEXT_BUTTON,
                            text = "textButton"
                        ),
                        Element(
                            ElementType.TEXT,
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