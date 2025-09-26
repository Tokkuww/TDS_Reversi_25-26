package org.example

//Define consts for Board
const val MAX_BOARDSIZE = 8// Maximum size for the Board

data class Board constructor(

    val height: Int = MAX_BOARDSIZE, //Board Height
    val width: Int = MAX_BOARDSIZE, //Board Width
    val piece : List<Pieces?> //List of Pieces that are currently active in the board

)
