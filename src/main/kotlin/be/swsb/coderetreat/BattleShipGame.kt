package be.swsb.coderetreat

import be.swsb.coderetreat.GameStatus.NEW_GAME
import be.swsb.coderetreat.location.Coordinate
import be.swsb.coderetreat.location.Vector
import be.swsb.coderetreat.ships.Ship

/**
 * x is lower bounds horizontal axis
 * y is left bounds vertical axis
 */
class BattleShipGame(val status: GameStatus = NEW_GAME) {

    private val playerOneShipPlacements: HashSet<ShipPlacement> = HashSet()
    private val playerTwoShipPlacements: HashSet<ShipPlacement> = HashSet()

    fun placePlayerOneShip(vector: Vector, ship: Ship): BattleShipGame {
        val shipPlacement = ShipPlacement(vector, ship)
        verifyOverlapWithOtherShips(shipPlacement, playerOneShipPlacements)
        playerOneShipPlacements.add(shipPlacement)
        return this;
    }

    fun placePlayerTwoShip(vector: Vector, ship: Ship): BattleShipGame {
        val shipPlacement = ShipPlacement(vector, ship)
        verifyOverlapWithOtherShips(shipPlacement, playerTwoShipPlacements)
        playerTwoShipPlacements.add(shipPlacement)
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

    fun playerOneFire(coordinate: Coordinate): BattleShipGame {
        TODO("Not yet implemented")
        return this
    }

    fun playerTwoFire(coordinate: Coordinate): BattleShipGame {
        TODO("Not yet implemented")
        return this
    }
}