import org.junit.jupiter.api.*
import kotlin.test.assertEquals

internal class RgbTest {

    @Nested
    @DisplayName("branching clamp")
    inner class Branch {

        @BeforeEach
        fun `configure clamping mode`() {
            Rgb.branchless = false
        }

        @Test
        fun `clamp should return same value if within range 0 to 255`() {
            (0..255).forEach { assertEquals(it, Rgb.clamp(it)) }
        }

        @Test
        fun `clamp should return 0 if value is less than or equal to 0`() {
            (-25000..0).forEach { assertEquals(0, Rgb.clamp(it)) }
        }

        @Test
        fun `clamp should return 255 if value is greater than or equal to 255`() {
            (255..25000).forEach { assertEquals(255, Rgb.clamp(it)) }
        }
    }

    @Nested
    @DisplayName("branchless clamp")
    inner class Branchless {

        @BeforeEach
        fun `configure clamping mode`() {
            Rgb.branchless = true
        }

        @Test
        fun `clamp should return same value if within range 0 to 255`() {
            (0..255).forEach { assertEquals(it, Rgb.clamp(it)) }
        }

        @Test
        fun `clamp should return 0 if value is less than or equal to 0`() {
            (-25000..0).forEach { assertEquals(0, Rgb.clamp(it)) }
        }

        @Test
        fun `clamp should return 255 if value is greater than or equal to 255`() {
            (255..25000).forEach { assertEquals(255, Rgb.clamp(it)) }
        }
    }
}
