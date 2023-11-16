package be.swsb.coderetreat.game

import be.swsb.coderetreat.location.Coordinate

sealed class BattleShipGameInProgress(
    val playerOneField: Field,
    val playerTwoField: Field
) {
    abstract fun playerOneFire(coordinate: Coordinate): BattleShipGameInProgress
    abstract fun playerTwoFire(coordinate: Coordinate): BattleShipGameInProgress
}