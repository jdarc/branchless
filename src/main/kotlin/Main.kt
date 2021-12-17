import kotlin.random.Random

fun main() {
    val rnd = Random(System.currentTimeMillis())
    val red = rnd.nextInt(0xFFFFFF)
    val grn = rnd.nextInt(0xFFFFFF)

    additiveTest(red, grn, branchless = false)
    additiveTest(red, grn, branchless = true)
}

private fun additiveTest(rgb1: Int, rgb2: Int, branchless: Boolean) {
    Rgb.branchless = branchless
    println("${hex(rgb1)} + ${hex(rgb2)} = ${hex(Rgb.add(rgb1, rgb2))} (branchless: ${Rgb.branchless})")
}

private fun hex(x: Int) = "0x${x.toString(16).uppercase()}"