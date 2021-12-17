import kotlin.random.Random

fun main() {
    val rnd = Random(System.currentTimeMillis())
    val rgb1 = rnd.nextInt(0x888888)
    val rgb2 = rnd.nextInt(0x888888)

    additiveTest(rgb1, rgb2, branchless = false)
    additiveTest(rgb1, rgb2, branchless = true)

    bench(rgb1, rgb2, branchless = false)
    bench(rgb1, rgb2, branchless = true)
}

fun bench(rgb1: Int, rgb2: Int, branchless: Boolean) {
    Rgb.branchless = branchless
    println("----------------------------------------------")
    println(" Branchless: ${Rgb.branchless}")
    println("----------------------------------------------")

    var a = 1
    var result = 0x000000
    benchmark(iterations = 100000000) {
        a = 1 - a // possiblt prevent inlining?
        result = Rgb.add(rgb1 + a, rgb2 - a)
    }

    println(hex(result))
}

private fun additiveTest(rgb1: Int, rgb2: Int, branchless: Boolean) {
    Rgb.branchless = branchless
    println("${hex(rgb1)} + ${hex(rgb2)} = ${hex(Rgb.add(rgb1, rgb2))} (branchless: ${Rgb.branchless})")
}

private fun hex(x: Int) = "0x${x.toString(16).uppercase()}"