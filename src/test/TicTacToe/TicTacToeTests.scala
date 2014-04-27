package TicTacToe

import org.scalatest._

class TicTacToeTest extends FlatSpec with Matchers {

  "Tic Tac Toe" should "start with an empty board" in {
    val emptyBoard = Array.fill(3, 3) {
      ' '
    }
    TicTacToeGame().board should be(emptyBoard)
  }

  it should "ensure X goes first" in {
    var ticTacToeGame = TicTacToeGame()

    ticTacToeGame.currentTurn should be('X')
    ticTacToeGame = ticTacToeGame.takeTurn(0, 0)
    ticTacToeGame.board(0)(0) should be('X')
  }

  it should "ensure O goes after X" in {
    var ticTacToeGame = TicTacToeGame()
    ticTacToeGame = ticTacToeGame.takeTurn(0, 0)

    ticTacToeGame.currentTurn should be('O')
    ticTacToeGame = ticTacToeGame.takeTurn(0, 1)
    ticTacToeGame.board(0)(1) should be('O')
  }

  it should "ensure X goes after O" in {
    var ticTacToeGame = TicTacToeGame()
    ticTacToeGame = ticTacToeGame.takeTurn(0, 0)
    ticTacToeGame = ticTacToeGame.takeTurn(0, 1)

    ticTacToeGame.currentTurn should be('X')
    ticTacToeGame = ticTacToeGame.takeTurn(0, 2)
    ticTacToeGame.board(0)(2) should be('X')
  }

  it should "not let a player take a cell already taken" in {
    val ticTacToeGame = TicTacToeGame().takeTurn(0, 0).takeTurn(0, 0)
    ticTacToeGame.currentTurn should be('O')
  }

}

class TicTacToeRobustnessTest extends FlatSpec with Matchers {

}


class ExampleTicTacToeGameTest extends FlatSpec with Matchers {
  it should "alternate taking turns between X and O with X going first" in {
    var ticTacToeGame = TicTacToeGame()

    ticTacToeGame.currentTurn should be('X')
    ticTacToeGame = ticTacToeGame.takeTurn(0, 0)
    ticTacToeGame.board(0)(0) should be('X')

    ticTacToeGame.currentTurn should be('O')
    ticTacToeGame = ticTacToeGame.takeTurn(0, 1)
    ticTacToeGame.board(0)(1) should be('O')

    ticTacToeGame.currentTurn should be('X')
    ticTacToeGame = ticTacToeGame.takeTurn(0, 2)
    ticTacToeGame.board(0)(2) should be('X')

    ticTacToeGame.currentTurn should be('O')
    ticTacToeGame = ticTacToeGame.takeTurn(1, 1)
    ticTacToeGame.board(1)(1) should be('O')
  }
}




