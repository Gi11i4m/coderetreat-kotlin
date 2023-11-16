package be.swsb.coderetreat.game

import be.swsb.coderetreat.location.Coordinate

class BattleShipGameInProgressPlayerTwoTurn(
    playerOneField: Field,
    playerTwoField: Field
) : BattleShipGameInProgress(playerOneField, playerTwoField) {
    override fun playerOneFire(coordinate: Coordinate): BattleShipGameInProgress {
        throw Error("It is not player one's turn")
    }

    override fun playerTwoFire(coordinate: Coordinate): BattleShipGameInProgress {
        if (playerOneField.receiveShot(coordinate)) {
            if (playerOneField.areAllShipsDestroyed()) {
                return BattleShipGameInProgressPlayerTwoWins(playerOneField, playerTwoField)
            }
            return BattleShipGameInProgressPlayerTwoTurn(playerOneField, playerTwoField)
        }
        return BattleShipGameInProgressPlayerOneTurn(playerOneField, playerTwoField)
    }
}