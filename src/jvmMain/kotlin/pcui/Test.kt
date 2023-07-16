@file:OptIn(ExperimentalComposeUiApi::class)

package pcui

import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.PointerIconDefaults
import androidx.compose.ui.input.pointer.pointerHoverIcon
import java.awt.Cursor

@Composable
fun Test() {
    Button(
        onClick = {},
        modifier = Modifier.pointerHoverIcon(AwtCursor(Cursor(Cursor.HAND_CURSOR))),
//        modifier = Modifier.pointerHoverIcon(PointerIconDefaults.Hand, true),
    ){
        Text("aaaaaaaaaaaaaaaa")
    }
}

class AwtCursor(val cursor: Cursor) : PointerIcon {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as AwtCursor
        if (cursor != other.cursor) return false
        return true
    }

    override fun hashCode(): Int {
        return cursor.hashCode()
    }

    override fun toString(): String {
        return "AwtCursor(cursor=$cursor)"
    }
}