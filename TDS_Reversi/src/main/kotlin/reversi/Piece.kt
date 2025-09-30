package reversi

interface Piece<T> {
    val position: Position
    val type: T
}