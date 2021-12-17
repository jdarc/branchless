object Rgb {

    var branchless = false

    fun red(rgb: Int) = rgb shr 0x10 and 0xFF

    fun grn(rgb: Int) = rgb shr 0x08 and 0xFF

    fun blu(rgb: Int) = rgb and 0xFF

    fun pack(r: Int, g: Int, b: Int) = clamp(r) shl 0x10 or (clamp(g) shl 0x08) or clamp(b)

    fun add(x: Int, y: Int) = pack(red(x) + red(y), grn(x) + grn(y), blu(x) + blu(y))

    fun clamp(x: Int) = when {
        branchless -> x and x.shr(31).inv() or (255 - x).shr(31) and 255
        else -> x.coerceIn(0, 255)
    }
}
