package TicTacToe

import org.scalatest._
import org.scalatest.prop.GeneratorDrivenPropertyChecks
import org.scalacheck.Gen

class TicTacToeTests extends FlatSpec with Matchers with GeneratorDrivenPropertyChecks {

  val EmptyBoard = Array.fill(3, 3) {
    ' '
  }
  val ValidRows, ValidColumns = Gen.choose(0, 2)
  val ValidTiles = Gen.oneOf('X', 'O')

  "Basic game mechanics" should "start with an empty board" in {
    TicTacToeGame().board should be(EmptyBoard)
  }

  it should "ensure X goes first" in {
    var ticTacToeGame = TicTacToeGame()

    ticTacToeGame.currentTurn should be('X')
    ticTacToeGame = ticTacToeGame.takeTurn(0, 0)
    ticTacToeGame.board(0)(0) should be('X')
  }

  it should "ensure O goes after X" in {
    var ticTacToeGame = TicTacToeGame().takeTurn(0, 0)

    ticTacToeGame.currentTurn should be('O')
    ticTacToeGame = ticTacToeGame.takeTurn(0, 1)
    ticTacToeGame.board(0)(1) should be('O')
  }

  it should "ensure X goes after O" in {
    var ticTacToeGame = TicTacToeGame().takeTurn(0, 0).takeTurn(0, 1)

    ticTacToeGame.currentTurn should be('X')
    ticTacToeGame = ticTacToeGame.takeTurn(0, 2)
    ticTacToeGame.board(0)(2) should be('X')
  }

  it should "ensure placement is allowed anywhere in the 3 by 3 grid" in {
    forAll((ValidRows, "row"), (ValidColumns, "column")) {
      (row, column) => {
        var ticTacToeGame = TicTacToeGame().takeTurn(row, column)
        ticTacToeGame.board(row)(column) should be('X')

        val nextTurn = ((row + 1) % 3, (column + 1) % 3) // Consistent next placement ensures all O tested
        ticTacToeGame = ticTacToeGame.takeTurn(nextTurn._1, nextTurn._2)
        ticTacToeGame.board(nextTurn._1)(nextTurn._2) should be('O')
      }
    }
  }

  "Winning a game" should "have no winner for a new game" in {
    TicTacToeGame().winner should be(' ')
  }

  "Game robustness" should "not let a player take a cell already taken" in {
    forAll((ValidRows, "row"), (ValidColumns, "column")) {
      (row, column) => {
        val ticTacToeGame = TicTacToeGame().takeTurn(row, column).takeTurn(row, column)
        ticTacToeGame.board(row)(column) should be('X')
        ticTacToeGame.currentTurn should be('O')
      }
    }
  }

  it should "not let a player take a turn outside of the 3 by 3 grid" in {
    forAll("row", "column") {
      (row: Int, column: Int) =>
        whenever(row < 0 || row >= 3 || column < 0 || column >= 3) {
          TicTacToeGame().takeTurn(row, column).board should be(EmptyBoard)
        }
    }
  }
}

class ExampleTicTacToeEndToEndGameTest extends FlatSpec with Matchers {
  it should "play a full example game" in {
    var ticTacToeGame = TicTacToeGame()

    ticTacToeGame.currentTurn should be('X')
    ticTacToeGame = ticTacToeGame.takeTurn(0, 0)
    ticTacToeGame.board(0)(0) should be('X')

    ticTacToeGame.currentTurn should be('O')
    ticTacToeGame = ticTacToeGame.takeTurn(1, 0)
    ticTacToeGame.board(1)(0) should be('O')

    ticTacToeGame.currentTurn should be('X')
    ticTacToeGame = ticTacToeGame.takeTurn(0, 2)
    ticTacToeGame.board(0)(2) should be('X')

    ticTacToeGame.currentTurn should be('O')
    ticTacToeGame = ticTacToeGame.takeTurn(1, 1)
    ticTacToeGame.board(1)(1) should be('O')
  }
}
