inline fun benchmark(iterations: Int, loops: Int, warmup: Int, setup: (Int) -> Unit = { }, callback: () -> Unit) {
    println("Benchmark: iterations = $iterations loops = $loops warmup = $warmup")

    val pad = { x: Int -> x.toString().padStart(2, '0') }

    var total = 0L
    for (loop in 0 until loops + warmup) {
        setup(loop)

        val start = System.currentTimeMillis()
        for (iter in 0 until iterations) callback()
        val duration = System.currentTimeMillis() - start

        if (loop < warmup) {
            println("Warm ${pad(loop + 1)} of ${pad(warmup)}: ${duration}ms")
            continue
        }

        total += duration
        println("Loop ${pad(loop + 1 - warmup)} of ${pad(loops)}: ${duration}ms")
    }

    println("Average: ${(total.toDouble() / loops.toDouble()).toInt()}ms")
}
