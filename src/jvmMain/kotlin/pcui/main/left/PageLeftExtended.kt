package pcui.main.left

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.PointerEvent
import androidx.compose.ui.input.pointer.PointerEventType
import androidx.compose.ui.input.pointer.onPointerEvent

@OptIn(ExperimentalComposeUiApi::class)
fun Modifier.touchListener(
    onTouchDown: ((PointerEvent) -> Unit)? = null,
    onTouchMove: ((PointerEvent) -> Unit)? = null,
    onTouchUp: ((PointerEvent) -> Unit)? = null
): Modifier {
    return this.onPointerEvent(
        // 移动
        eventType = PointerEventType.Press,
        onEvent = {
            onTouchDown?.invoke(it)
        },
    ).onPointerEvent(
        // 移动
        eventType = PointerEventType.Move,
        onEvent = {
            onTouchMove?.invoke(it)
        },
    ).onPointerEvent(
        // 拿开
        eventType = PointerEventType.Release,
        onEvent = {
            onTouchUp?.invoke(it)
        },
    )
}