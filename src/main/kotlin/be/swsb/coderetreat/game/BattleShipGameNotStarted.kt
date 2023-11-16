package be.swsb.coderetreat.game

import be.swsb.coderetreat.location.Vector
import be.swsb.coderetreat.ships.Ship

class BattleShipGameNotStarted : BattleShipGame(Field(), Field()) {

    fun placePlayerOneShip(vector: Vector, ship: Ship): BattleShipGameNotStarted {
        this.playerOneField.add(ShipPlacement(vector, ship))
        return this
    }

    fun placePlayerTwoShip(vector: Vector, ship: Ship): BattleShipGameNotStarted {
        this.playerTwoField.add(ShipPlacement(vector, ship))
        return this
    }

    fun start(): BattleShipGameInProgressPlayerOneTurn {
        this.playerOneField.verifyAllShipsPresent()
        this.playerTwoField.verifyAllShipsPresent()
        return BattleShipGameInProgressPlayerOneTurn(this.playerOneField, this.playerTwoField)
    }
}
