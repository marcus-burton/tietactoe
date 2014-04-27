package TicTacToe

import org.scalatest._

class TicTacToeTests extends FlatSpec with Matchers {

  "Basic game mechanics" should "start with an empty board" in {
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

  "Game robustness" should "not let a player take a cell already taken" in {
    val ticTacToeGame = TicTacToeGame().takeTurn(0, 0).takeTurn(0, 0)
    ticTacToeGame.currentTurn should be('O')
  }

  it should "not let a player take a turn outside of the 3 by 3 grid" in {
    val emptyBoard = Array.fill(3, 3) {
      ' '
    }

    TicTacToeGame().takeTurn(-1, 0).board should be(emptyBoard)
    TicTacToeGame().takeTurn(0, -1).board should be(emptyBoard)
    TicTacToeGame().takeTurn(3, 0).board should be(emptyBoard)
    TicTacToeGame().takeTurn(0, 3).board should be(emptyBoard)
  }
}

class ExampleTicTacToeEndToEndGameTest extends FlatSpec with Matchers {
  it should "play a full example game" in {
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




