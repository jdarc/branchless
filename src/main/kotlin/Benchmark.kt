// based on https://gist.github.com/olegcherr/b62a09aba1bff643a049
inline fun benchmark(iterations: Int = 100000, loops: Int = 10, warmup: Int = 2, callback: () -> Unit) {

    val results = mutableListOf<Long>()
    var totalTime = 0L
    var t = 0

    println("Benchmark: iterations=${iterations} loops=${loops} warmup=${warmup}")

    while (++t <= loops + warmup) {
        val startTime = System.currentTimeMillis()

        var i = 0
        while (i++ < iterations) {
            callback()
        }

        if (t <= warmup) {
            println("Warming: $t of $warmup")
            continue
        }

        val time = System.currentTimeMillis() - startTime
        println("Loop ${t - warmup} of ${loops}: ${time}ms")

        results.add(time)
        totalTime += time
    }

    results.sort()

    val average = totalTime / loops
    val median = results[results.size / 2]

    println("Results: average=${average}ms median=${median}ms")
}
