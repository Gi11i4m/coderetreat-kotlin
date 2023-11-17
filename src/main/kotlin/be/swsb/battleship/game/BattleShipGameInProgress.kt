package be.swsb.battleship.game

import be.swsb.battleship.location.Coordinate

sealed class BattleShipGameInProgress(
    val playerOneField: Field,
    val playerTwoField: Field
) {
    abstract fun playerOneFire(coordinate: Coordinate): BattleShipGameInProgress
    abstract fun playerTwoFire(coordinate: Coordinate): BattleShipGameInProgress
}