package reversi

interface BoardGame<A> {
    val board: Board

    val legalMoves: Set<A>
}