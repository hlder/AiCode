package pcui.main.center

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import pcui.beans.Element
import pcui.beans.ElementType
import pcui.beans.elements.*
import pcui.main.PageMainViewModel

@Composable
fun PageCenter(viewModel: PageMainViewModel) {
    Row() {
        Box(modifier = Modifier.fillMaxWidth().weight(1f)) { }
        Column(modifier = Modifier.width(480.dp).fillMaxHeight().background(color = Color.Black)) {
            viewModel.listPage.forEach {
                itemElement(it.element)
            }
        }
        Box(modifier = Modifier.fillMaxWidth().weight(1f)) { }
    }
}

@Composable
private fun itemElement(element: Element) {
    when (element) {
        is TextElement -> { // 文本Text
            Text(
                element.text ?: "",
                modifier = getBaseElementModifier(element),
                color = element.textColor?.let { Color(it) } ?: Color.Black,
                fontSize = (element.textSize ?: 14).sp
            )
        }
        is TextFieldElement -> {
            TextField(element.text ?: "", {})
        }
        is DividerElement -> {
            Divider(
                modifier = getBaseElementModifier(element).width(1.dp).fillMaxHeight(),
                color = Color(element.dividerColor ?: 0x00000000)
            )
        }
        is TextButtonElement -> {
            TextButton(
                modifier = getBaseElementModifier(element),
                onClick = { }
            ) {
                Text(
                    element.text ?: "",
                    color = element.textColor?.let { Color(it) } ?: Color.Black,
                    fontSize = (element.textSize ?: 14).sp
                )
            }
        }
        is RowElement -> {
            Row(modifier = getBaseElementModifier(element)) {
                element.childs?.let { childs ->
                    childs.forEach {
                        itemElement(it)
                    }
                }
            }
        }
        is ColumnElement -> {
            Column(modifier = getBaseElementModifier(element)) {
                element.childs?.let { childs ->
                    childs.forEach {
                        itemElement(it)
                    }
                }
            }
        }
    }
}

private fun getBaseElementModifier(element: Element): Modifier {
    var modifier = Modifier.padding(
        start = element.paddingStart?.dp?:0.dp,
        top = element.paddingTop?.dp?:0.dp,
        end = element.paddingEnd?.dp?:0.dp,
        bottom = element.paddingBottom?.dp?:0.dp
    )
    element.width?.let {
        modifier = if (element.width >= 9999) {
            modifier.fillMaxWidth()
        } else {
            modifier.width(element.width.dp)
        }
    }
    element.height?.let {
        modifier = if (element.height >= 9999) {
            modifier.fillMaxHeight()
        } else {
            modifier.height(element.height.dp)
        }
    }
    return element.backgroundColor?.let { modifier.background(color = Color(it)) }?:modifier
}
