import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

internal class RgbTest {

    @Nested
    @DisplayName("clampBranching")
    inner class Branch {
        @Test
        fun `clamp should return same value if within range 0 to 255`() {
            (0..255).forEach { assertEquals(it, Rgb.clampBranching(it)) }
        }

        @Test
        fun `clamp should return 0 if value is less than or equal to 0`() {
            (-25000..0).forEach { assertEquals(0, Rgb.clampBranching(it)) }
        }

        @Test
        fun `clamp should return 255 if value is greater than or equal to 255`() {
            (255..25000).forEach { assertEquals(255, Rgb.clampBranching(it)) }
        }
    }


    @Nested
    @DisplayName("clampBranchless")
    inner class Branchless {
        @Test
        fun `clamp Branchless should return same value if within range 0 to 255`() {
            (0..255).forEach { assertEquals(it, Rgb.clampBranchless(it)) }
        }

        @Test
        fun `clamp should return 0 if value is less than or equal to 0`() {
            (-25000..0).forEach { assertEquals(0, Rgb.clampBranchless(it)) }
        }

        @Test
        fun `clamp should return 255 if value is greater than or equal to 255`() {
            (255..25000).forEach { assertEquals(255, Rgb.clampBranchless(it)) }
        }
    }
}