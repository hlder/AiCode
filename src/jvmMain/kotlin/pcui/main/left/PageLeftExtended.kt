package pcui.main.left

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.PointerEvent
import androidx.compose.ui.input.pointer.PointerEventType
import androidx.compose.ui.input.pointer.onPointerEvent

@OptIn(ExperimentalComposeUiApi::class)
fun Modifier.touchListener(
    onTouchDown: (PointerEvent) -> Unit,
    onTouchMove: (PointerEvent) -> Unit,
    onTouchUp: (PointerEvent) -> Unit
): Modifier {
    return this.onPointerEvent(
        // 移动
        eventType = PointerEventType.Press,
        onEvent = {
            onTouchDown(it)
        },
    ).onPointerEvent(
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