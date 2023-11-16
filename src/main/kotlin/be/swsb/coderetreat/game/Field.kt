package be.swsb.coderetreat.game

import be.swsb.coderetreat.location.Coordinate
import be.swsb.coderetreat.ships.Ship
import kotlin.reflect.KClass

class Field {
    /**
     * x is lower bounds horizontal axis
     * y is left bounds vertical axis
     */
    private val inclusiveUpperBound: Coordinate = Coordinate(9u, 9u)
    private val ships: HashSet<ShipPlacement> = HashSet()
    private val receivedShots: HashSet<Coordinate> = HashSet()

    fun add(ship: ShipPlacement) {
        val overlappingShip = findOverlappingShip(ship)
        if (overlappingShip != null) {
            throw Error("Tried to place overlapping ships (${ship.ship::class.simpleName}, ${overlappingShip.ship::class.simpleName})")
        }
        if (ship.isOutOfBounds(inclusiveUpperBound)) {
            throw Error("Ship placed out of bounds")
        }
        val duplicateShipType = findShipType(ship.ship)
        if (duplicateShipType != null) {
            throw Error("Tried to place the same ship twice (${duplicateShipType::class.simpleName})")
        }
        ships.add(ship)
    }

    fun verifyAllShipsPresent() {
        val allShipTypes: List<KClass<out Ship>> = Ship::class.sealedSubclasses
        val presentShipTypes = ships.map { it.ship::class }
        if (!presentShipTypes.containsAll(allShipTypes)) {
            throw Error("Not all ships have been placed")
        }
    }

    private fun findShipType(ship: Ship): Ship? {
        return ships.map { it.ship }.find { it::class == ship::class }
    }

    private fun findOverlappingShip(ship: ShipPlacement): ShipPlacement? {
        return ships.find { it.overlaps(ship) }
    }

    fun receiveShot(coordinate: Coordinate): Boolean {
        if (coordinate.outsideOf(inclusiveUpperBound)) {
            throw Error("Shot out of bounds")
        }
        if (receivedShots.contains(coordinate)) {
            throw Error("This coordinate has already been fired at")
        }

        receivedShots.add(coordinate)
        return isShip(coordinate)
    }

    private fun isShip(coordinate: Coordinate): Boolean {
        return ships.any { it.contains(coordinate) }
    }

    fun areAllShipsDestroyed(): Boolean {
        return ships.all { ship -> ship.coordinates.all { receivedShots.contains(it) } }
    }

    override fun toString(): String {
        return UIntRange(0u, inclusiveUpperBound.y).reversed().joinToString("\n") { y ->
            UIntRange(
                0u,
                inclusiveUpperBound.x
            ).joinToString("") { x ->
                val coordinate = Coordinate(x, y)
                if (receivedShots.contains(coordinate)) {
                    "💥"
                } else if (isShip(coordinate)) {
                    "⛵"
                } else {
                    "⬜"
                }
            }
        }
    }
}

private fun ShipPlacement.isOutOfBounds(inclusiveUpperBound: Coordinate): Boolean {
    return this.coordinates.any { it.outsideOf(inclusiveUpperBound) }
}

private fun Coordinate.outsideOf(inclusiveUpperBound: Coordinate): Boolean {
    return this.x > inclusiveUpperBound.x || this.y > inclusiveUpperBound.y
}
