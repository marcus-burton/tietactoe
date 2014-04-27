package TicTacToe


case class TicTacToeGame(board: Vector[Vector[Char]] = SpecialBoardStates.Empty,
                         currentTurn: Char = 'X') {
  def takeTurn(row: Int, column: Int): TicTacToeGame = {
    if (validTurn(row, column)) {
      val updatedBoard = takeCurrentTurn(row, column)
      return copy(board = updatedBoard, currentTurn = alternateTurn)
    }
    this
  }

  private def validTurn(row: Int, column: Int): Boolean = turnInCorrectBounds(row, column) && cellNotTaken(row, column)

  private def turnInCorrectBounds(row: Int, column: Int): Boolean =
    (row == 0 || row == 1 || row == 2) && (column == 0 || column == 1 || column == 2)

  private def cellNotTaken(row: Int, column: Int): Boolean = board(row)(column) == ' '

  private def takeCurrentTurn(row: Int, column: Int) = board.updated(row, board(row).updated(column, currentTurn))

  private def alternateTurn = if (currentTurn == 'X') 'O' else 'X'
}

object SpecialBoardStates {
  val Empty = Vector.fill(3, 3) {
    ' '
  }
}
