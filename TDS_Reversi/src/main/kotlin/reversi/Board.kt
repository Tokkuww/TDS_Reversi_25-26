package reversi

interface Board {
    val height: Int
    val width: Int

    fun Position.isValid(): Boolean
}

