package org.example

//Define consts for Board
const val MAX_BOARDSIZE = 16// Maximum size for the Board
const val DEFAULT_BOARDSIZE = 8

data class Board constructor(

    val height: Int, //Board Height
    val width: Int, //Board Width
    val piece : List<Pieces?> //List of Pieces that are currently active in the board

)




