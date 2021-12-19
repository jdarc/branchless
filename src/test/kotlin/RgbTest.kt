import org.junit.jupiter.api.*
import kotlin.test.assertEquals

internal class RgbTest {

    @Nested
    @DisplayName("branching clamp")
    inner class Branch {

        @Test
        fun `clamp should return same value if within range 0 to 255`() {
            val branching = Clamp.Standard()
            (0..255).forEach { assertEquals(it, branching.clamp(it)) }
        }

        @Test
        fun `clamp should return 0 if value is less than or equal to 0`() {
            val branching = Clamp.Standard()
            (-25000..0).forEach { assertEquals(0, branching.clamp(it)) }
        }

        @Test
        fun `clamp should return 255 if value is greater than or equal to 255`() {
            val branching = Clamp.Standard()
            (255..25000).forEach { assertEquals(255, branching.clamp(it)) }
        }
    }

    @Nested
    @DisplayName("branchless clamp")
    inner class Branchless {

        @Test
        fun `clamp should return same value if within range 0 to 255`() {
            val branchless = Clamp.Branchless()
            (0..255).forEach { assertEquals(it, branchless.clamp(it)) }
        }

        @Test
        fun `clamp should return 0 if value is less than or equal to 0`() {
            val branchless = Clamp.Branchless()
            (-25000..0).forEach { assertEquals(0, branchless.clamp(it)) }
        }

        @Test
        fun `clamp should return 255 if value is greater than or equal to 255`() {
            val branchless = Clamp.Branchless()
            (255..25000).forEach { assertEquals(255, branchless.clamp(it)) }
        }
    }
}
