import kotlin.random.Random

fun main() {
    val rnd = Random(System.currentTimeMillis())
    val rgb1 = rnd.nextInt(0x888888)
    val rgb2 = rnd.nextInt(0x888888)

    log(rgb1, rgb2, Rgb.add(rgb1, rgb2))

    println()
    bench(rgb1, rgb2)

    println()
    Rgb.mode = Clamp.Branchless()
    bench(rgb1, rgb2)
}

private fun bench(rgb1: Int, rgb2: Int) {
    println("----------------------------------------------")
    println(" Is branchless: ${Rgb.mode.isBranchless}")
    println("----------------------------------------------")

    var a = 1
    var result = 0x000000
    benchmark(iterations = 200000000, warmup = 5, loops = 20) {
        result = Rgb.add(rgb1 + a, rgb2 - a)
        a = 1 - a // possibly prevents inlining and other smart compiler stuff?
    }

    log(rgb1, rgb2, result)
}

private fun log(rgb1: Int, rgb2: Int, x: Int) = 
    println("${hex(rgb1)} + ${hex(rgb2)} = ${hex(x)} (branchless: ${Rgb.mode.isBranchless})")

private fun hex(x: Int) = "0x${x.toString(16).uppercase()}"