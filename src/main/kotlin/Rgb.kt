object Rgb {

    var branchless = false

    fun red(rgb: Int) = rgb shr 0x10 and 0xFF

    fun grn(rgb: Int) = rgb shr 0x08 and 0xFF

    fun blu(rgb: Int) = rgb and 0xFF

    fun pack(r: Int, g: Int, b: Int) = clamp(r) shl 0x10 or (clamp(g) shl 0x08) or clamp(b)

    fun add(x: Int, y: Int) = pack(red(x) + red(y), grn(x) + grn(y), blu(x) + blu(y))

    fun clamp(x: Int) = if (branchless) clampBranchless(x) else clampBranching(x)

    // We could have simply used x.coerceIn(0, 255), either way let the compiler do the magic.
    fun clampBranching(x: Int) = when {
        x < 0 -> 0
        x > 255 -> 255
        else -> x
    }

    // How do you fancy maintaining code like this?
    fun clampBranchless(x: Int): Int {
        val low = 0xFF - x.ushr(31) * 0xFF
        return x.and(low) or (0 - x.and(0xFFFF00)).ushr(31) * low
    }
}
