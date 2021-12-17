import kotlin.random.Random

fun main() {
    val rnd = Random(System.currentTimeMillis())
    val rgb1 = rnd.nextInt(0x888888)
    val rgb2 = rnd.nextInt(0x888888)

    Rgb.branchless = false
    log(rgb1, rgb2, Rgb.add(rgb1, rgb2))

    println()
    Rgb.branchless = false
    bench(rgb1, rgb2)

    println()
    Rgb.branchless = true
    bench(rgb1, rgb2)
}

private fun bench(rgb1: Int, rgb2: Int) {
    println("----------------------------------------------")
    println(" Is branchless: ${Rgb.branchless}")
    println("----------------------------------------------")

    var a = 1
    var result = 0x000000
    benchmark(iterations = 100000000) {
        a = 1 - a // possibly prevents inlining?
        result = Rgb.add(rgb1 + a, rgb2 - a)
    }

    log(rgb1, rgb2, result)
}

private fun log(rgb1: Int, rgb2: Int, x: Int) = 
    println("${hex(rgb1)} + ${hex(rgb2)} = ${hex(x)} (branchless: ${Rgb.branchless})")

private fun hex(x: Int) = "0x${x.toString(16).uppercase()}"