import com.badlogic.gdx.tools.texturepacker.TexturePacker

fun main(args: Array<String>) {
    TexturePacker.process("android/images/src", "android/assets", "pack")
    TexturePacker.process("android/images/particles", "android/assets", "particles")
}
