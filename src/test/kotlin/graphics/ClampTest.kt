package graphics

import org.junit.jupiter.api.*
import kotlin.test.assertEquals

internal class ClampTest {

    @Test
    fun `clamp should return same value if within range 0 to 255`() {
        OPS.forEach { op -> (0..255).forEach { assertEquals(it, op(it)) } }
    }

    @Test
    fun `clamp should return 0 if value is less than or equal to 0`() {
        OPS.forEach { op -> (-MAX..0).forEach { assertEquals(0, op(it)) } }
    }

    @Test
    fun `clamp should return 255 if value is greater than or equal to 255`() {
        OPS.forEach { op -> (255..MAX).forEach { assertEquals(255, op(it)) } }
    }

    private companion object {
        private const val MAX = 65536

        private val OPS = arrayOf(Clamp::standard, Clamp::coerce, Clamp::minmax, Clamp::branchless)
    }
}
