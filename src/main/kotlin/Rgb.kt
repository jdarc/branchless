object Rgb {

    var mode: Clamp = Clamp.Standard()

    fun red(rgb: Int) = rgb shr 0x10 and 0xFF

    fun grn(rgb: Int) = rgb shr 0x08 and 0xFF

    fun blu(rgb: Int) = rgb and 0xFF

    fun pack(r: Int, g: Int, b: Int) = mode.clamp(r) shl 0x10 or (mode.clamp(g) shl 0x08) or mode.clamp(b)

    fun add(x: Int, y: Int) = pack(red(x) + red(y), grn(x) + grn(y), blu(x) + blu(y))
}
