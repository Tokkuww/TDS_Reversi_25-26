package reversi

interface Game<S, A> {
    interface Player

    val players: Iterable<Player>

    var currentState: S

    fun setup(): S

    fun applyAction(state: S, action: A): S
}