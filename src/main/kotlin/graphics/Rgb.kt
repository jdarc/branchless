package graphics

object Rgb {

    fun red(rgb: Int) = 255 and rgb.shr(16)
    fun grn(rgb: Int) = 255 and rgb.shr(8)
    fun blu(rgb: Int) = 255 and rgb

    inline fun add(x: Int, y: Int, clamp: (Int) -> Int = Clamp::standard): Int {
        val red = clamp(red(x) + red(y))
        val grn = clamp(grn(x) + grn(y))
        val blu = clamp(blu(x) + blu(y))
        return red.shl(16) or grn.shl(8) or blu
    }

    inline fun sub(x: Int, y: Int, clamp: (Int) -> Int = Clamp::standard): Int {
        val red = clamp(red(x) - red(y))
        val grn = clamp(grn(x) - grn(y))
        val blu = clamp(blu(x) - blu(y))
        return red.shl(16) or grn.shl(8) or blu
    }
}
