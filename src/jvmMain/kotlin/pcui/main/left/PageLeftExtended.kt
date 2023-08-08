package pcui.main.left

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.pointer.PointerEvent
import androidx.compose.ui.input.pointer.PointerEventType
import androidx.compose.ui.input.pointer.onPointerEvent
import androidx.compose.ui.input.pointer.pointerInput

@OptIn(ExperimentalComposeUiApi::class)
fun Modifier.touchListener(
    onTouchDown: (offset: Offset) -> Unit,
    onTouchMove: (PointerEvent) -> Unit,
    onTouchUp: (PointerEvent) -> Unit
): Modifier {
    return this.pointerInput(Unit) { // 按下
        detectTapGestures(
            onPress = {
                onTouchDown(it)
            }
        )
    }.onPointerEvent(
        // 移动
        eventType = PointerEventType.Move,
        onEvent = {
            onTouchMove(it)
        },
    ).onPointerEvent(
        // 拿开
        eventType = PointerEventType.Release,
        onEvent = {
            onTouchUp(it)
        },
    )
}