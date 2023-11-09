package be.swsb.coderetreat.game

import be.swsb.coderetreat.location.Vector
import be.swsb.coderetreat.ships.Ship

/**
 * x is lower bounds horizontal axis
 * y is left bounds vertical axis
 */
class BattleShipGameNotStarted : BattleShipGame(HashSet(), HashSet()) {

    fun placePlayerOneShip(vector: Vector, ship: Ship): BattleShipGameNotStarted {
        val shipPlacement = ShipPlacement(vector, ship)
        verifyOverlapWithOtherShips(shipPlacement, this.shipPlacementsPlayerOne)
        this.shipPlacementsPlayerOne.add(shipPlacement)
        return this;
    }

    fun placePlayerTwoShip(vector: Vector, ship: Ship): BattleShipGameNotStarted {
        val shipPlacement = ShipPlacement(vector, ship)
        verifyOverlapWithOtherShips(shipPlacement, this.shipPlacementsPlayerTwo)
        this.shipPlacementsPlayerTwo.add(shipPlacement)
        return this
    }

    private fun verifyOverlapWithOtherShips(
        shipPlacement: ShipPlacement,
        otherShipPlacements: Set<ShipPlacement>
    ) {
        if (otherShipPlacements.any { shipPlacement.overlaps(it) }) {
            throw Error("Tried to place overlapping ships")
        }
    }

    fun start(): BattleShipGameInProgress {
        return BattleShipGameInProgress(this.shipPlacementsPlayerOne, this.shipPlacementsPlayerTwo)
    }
}