package be.swsb.coderetreat.game

import be.swsb.coderetreat.location.Coordinate

class BattleShipGameInProgressPlayerTwoWins(
    playerOneField: Field,
    playerTwoField: Field
) : BattleShipGameInProgress(playerOneField, playerTwoField) {
    private val gameOverMessage = "Game over (player two wins)"

    override fun playerOneFire(coordinate: Coordinate): BattleShipGameInProgress {
        throw Error(gameOverMessage)
    }

    override fun playerTwoFire(coordinate: Coordinate): BattleShipGameInProgress {
        throw Error(gameOverMessage)
    }
}