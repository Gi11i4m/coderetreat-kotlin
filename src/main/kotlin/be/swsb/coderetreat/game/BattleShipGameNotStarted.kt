package be.swsb.coderetreat.game

import be.swsb.coderetreat.location.Vector
import be.swsb.coderetreat.ships.Ship

class BattleShipGameNotStarted : BattleShipGame(Field(), Field()) {

    fun placePlayerOneShip(vector: Vector, ship: Ship): BattleShipGameNotStarted {
        val shipPlacement = ShipPlacement(vector, ship)
        verifyOverlapWithOtherShips(shipPlacement, this.playerOneField)
        this.playerOneField.add(shipPlacement)
        return this;
    }

    fun placePlayerTwoShip(vector: Vector, ship: Ship): BattleShipGameNotStarted {
        val shipPlacement = ShipPlacement(vector, ship)
        verifyOverlapWithOtherShips(shipPlacement, this.playerTwoField)
        this.playerTwoField.add(shipPlacement)
        return this
    }

    private fun verifyOverlapWithOtherShips(
        ship: ShipPlacement,
        field: Field
    ) {
        val overlappingShip = field.containsOverlappingShipFor(ship)
        if (overlappingShip != null) {
            throw Error("Tried to place overlapping ships (${ship.ship.javaClass.simpleName}, ${overlappingShip.ship.javaClass.simpleName})")
        }
    }

    fun start(): BattleShipGameInProgress {
        return BattleShipGameInProgress(this.playerOneField, this.playerTwoField)
    }
}