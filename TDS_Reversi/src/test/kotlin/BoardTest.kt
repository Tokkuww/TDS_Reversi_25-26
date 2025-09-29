import org.example.Board
import org.example.Pieces
import kotlin.test.Test
import kotlin.test.assertFailsWith


class BoardTest() {

    /*
    * Simple test that verifies if the Board Class
    * is being initialized as pretended
     */

    @Test
   fun `Create a Square Board`() {
       Board(9 , 9 , listOf<Pieces?>(null , null))
    }
}