package org.example


data class Position constructor( val row: Int , val col: Int )

/*
* This function is used to return the current position of Piece
* in a way that we can read it as an array index
 */
fun Position.sendPosition(height : Int , width: Int): Int {

    require( row in 0..width)
    require( col in 0..height)

    val truePos =  row * height + col

    return truePos

}
