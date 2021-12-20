package graphics

import kotlin.math.min
import kotlin.math.max

object Clamp {
    fun standard(x: Int) = if (x < 0) 0 else if (x > 255) 255 else x

    fun coerce(x: Int) = x.coerceIn(0, 255)

    fun minmax(x: Int) = max(0, min(255, x))

    fun branchless(x: Int) = x and x.shr(31).inv() or (255 - x).shr(31) and 255
}
