package reversi

interface AdversarialGame<S, A>: Game<S, A> {
    interface Team {
        val members: Iterable<Game.Player>
        val name: String
    }

    val teams: Iterable<Team>

    val currentTurn: Int

    fun isOver(): Boolean

    fun getResult(state: S): Map<Int, Int>
}