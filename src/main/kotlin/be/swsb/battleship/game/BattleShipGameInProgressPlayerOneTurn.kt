package be.swsb.battleship.game

import be.swsb.battleship.location.Coordinate

class BattleShipGameInProgressPlayerOneTurn(
    playerOneField: Field,
    playerTwoField: Field
) : BattleShipGameInProgress(playerOneField, playerTwoField) {
    override fun playerOneFire(coordinate: Coordinate): BattleShipGameInProgress {
        if (playerTwoField.receiveShot(coordinate)) {
            if (playerTwoField.areAllShipsDestroyed()) {
                return BattleShipGameInProgressPlayerOneWins(playerOneField, playerTwoField)
            }
            return BattleShipGameInProgressPlayerOneTurn(playerOneField, playerTwoField)
        }
        return BattleShipGameInProgressPlayerTwoTurn(playerOneField, playerTwoField)
    }

    override fun playerTwoFire(coordinate: Coordinate): BattleShipGameInProgress {
        throw Error("It is not player two's turn")
    }
}