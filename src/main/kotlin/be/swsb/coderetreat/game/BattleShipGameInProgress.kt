package be.swsb.coderetreat.game

import be.swsb.coderetreat.location.Coordinate

class BattleShipGameInProgress(
    shipPlacementsPlayerOne: HashSet<ShipPlacement>,
    shipPlacementsPlayerTwo: HashSet<ShipPlacement>
) : BattleShipGame(shipPlacementsPlayerOne, shipPlacementsPlayerTwo) {
    var status = GameStatus.NEW_GAME

    fun playerOneFire(coordinate: Coordinate): BattleShipGameInProgress {
        TODO("Not yet implemented")
        return this
    }

    fun playerTwoFire(coordinate: Coordinate): BattleShipGameInProgress {
        TODO("Not yet implemented")
        return this
    }
}