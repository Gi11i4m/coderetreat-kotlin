package be.swsb.coderetreat.game

import be.swsb.coderetreat.location.Coordinate

class BattleShipGameInProgress(
    playerOneField: Field,
    playerTwoField: Field
) : BattleShipGame(playerOneField, playerTwoField) {
    var status = GameStatus.NEW_GAME

    fun playerOneFire(coordinate: Coordinate): BattleShipGameInProgress {
        if (setOf(GameStatus.PLAYER_TWO_HIT, GameStatus.PLAYER_ONE_MISSED).contains(status)) {
            throw Error("It is not player one's turn")
        }

        status = if (playerTwoField.receiveShot(coordinate)) {
            if (playerTwoField.areAllShipsDestroyed()) {
                GameStatus.PLAYER_ONE_WINS
            } else {
                GameStatus.PLAYER_ONE_HIT
            }
        } else {
            GameStatus.PLAYER_ONE_MISSED
        }
        println("\n" + playerTwoField.toString() + "\n")
        return this
    }

    fun playerTwoFire(coordinate: Coordinate): BattleShipGameInProgress {
        if (setOf(GameStatus.NEW_GAME, GameStatus.PLAYER_ONE_HIT, GameStatus.PLAYER_TWO_MISSED).contains(status)) {
            throw Error("It is not player two's turn")
        }

        status = if (playerOneField.receiveShot(coordinate)) {
            if (playerOneField.areAllShipsDestroyed()) {
                GameStatus.PLAYER_TWO_WINS
            } else {
                GameStatus.PLAYER_TWO_HIT
            }
        } else {
            GameStatus.PLAYER_TWO_MISSED
        }
        println("\n" + playerOneField.toString() + "\n")
        return this
    }
}