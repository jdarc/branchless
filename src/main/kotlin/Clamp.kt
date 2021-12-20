interface Clamp {
    val isBranchless: Boolean
    fun clamp(x: Int): Int

    private class StandardClamp : Clamp {
        override val isBranchless = false
        override fun clamp(x: Int) = x.coerceIn(0, 255)
    }

    private class BranchlessClamp : Clamp {
        override val isBranchless = true

        // return (x & ~(x >> 31) | 255 - x >> 31) & 255;
        override fun clamp(x: Int) = x and x.shr(31).inv() or (255 - x).shr(31) and 255
    }

    companion object {
        val Standard: Clamp = StandardClamp()
        val Branchless: Clamp = BranchlessClamp()
    }
}
