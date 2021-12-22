import graphics.Clamp
import graphics.Rgb
import kotlin.math.absoluteValue
import kotlin.random.Random

const val ANSI_RED = "\u001B[31m"
const val ANSI_RESET = "\u001B[0m"

fun main() {
    val rnd = Random(System.currentTimeMillis())
    val rgb1 = rnd.nextInt(0x888888)
    val rgb2 = rnd.nextInt(0xFFFFFF)
    runBenchmarks(rgb1, rgb2, Rgb.add(rgb1, rgb2), Rgb.sub(rgb1, rgb2))
}

private fun runBenchmarks(rgb1: Int, rgb2: Int, addExpect: Int, subExpect: Int) {
    run(rgb1, rgb2, addExpect, subExpect, Clamp::standard, "Standard")
    run(rgb1, rgb2, addExpect, subExpect, Clamp::coerce, "Coerce")
    run(rgb1, rgb2, addExpect, subExpect, Clamp::minmax, "Minmax")
    run(rgb1, rgb2, addExpect, subExpect, Clamp::branchless, "Branchless")
}

private inline fun run(rgb1: Int, rgb2: Int, addExpect: Int, subExpect: Int, op: (Int) -> Int, title: String) {
    println("-".repeat(32))
    println("$title Clamp")
    println("-".repeat(32))

    val iterations = 400000000
    val loops = 16
    val warmup = 4

    var add = 0
    var sub = 0
    benchmark(iterations, loops, warmup, { add = loops + warmup - it - 1; sub = add }) {
        add = add or Rgb.add(rgb1, rgb2, op)
        sub = sub or Rgb.sub(rgb1, rgb2, op)
    }

    if (add != addExpect) println(ANSI_RED)
    println("Result: $title Rgb.add(${hex(rgb1)}, ${hex(rgb2)}) = ${hex(add)} (${hex(addExpect)} is expected) $ANSI_RESET")

    if (sub != subExpect) println(ANSI_RED)
    println("Result: $title Rgb.sub(${hex(rgb1)}, ${hex(rgb2)}) = ${hex(sub)} (${hex(subExpect)} is expected) $ANSI_RESET")

    println()
}

private fun hex(x: Int) = "0x${x.absoluteValue.toString(16).padStart(6, '0').uppercase()}"
