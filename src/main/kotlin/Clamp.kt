interface Clamp {
    val isBranchless get() = false

    fun clamp(x: Int): Int

    class Standard : Clamp {
        override fun clamp(x: Int) = x.coerceIn(0, 255)
    }

    class Branchless : Clamp {
        override val isBranchless get() = true
        override fun clamp(x: Int) = x and x.shr(31).inv() or (255 - x).shr(31) and 255
    }
}

