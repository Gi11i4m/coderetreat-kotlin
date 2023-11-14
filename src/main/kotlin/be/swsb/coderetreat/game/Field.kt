package be.swsb.coderetreat.game

import be.swsb.coderetreat.location.Coordinate
import be.swsb.coderetreat.ships.Ship

class Field {
    /**
     * x is lower bounds horizontal axis
     * y is left bounds vertical axis
     */
    private val inclusiveUpperBound: Coordinate = Coordinate(9u, 9u)
    private val ships: HashSet<ShipPlacement> = HashSet()
    private val receivedShots: HashSet<Coordinate> = HashSet()

    fun add(ship: ShipPlacement) {
        if (ship.isOutOfBounds(inclusiveUpperBound)) {
            throw Error("Ship placed out of bounds")
        }
        if (containsShipType(ship.ship)) {
            throw Error("Tried to place the same ship twice")
        }
        ships.add(ship)
    }

    private fun containsShipType(ship: Ship): Boolean {
        return ships.any { it::class == ship::class } // TODO: compare like this everywhere else (remove all `java...`)
    }

    fun containsOverlappingShipFor(ship: ShipPlacement): ShipPlacement? {
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
        return isHit(coordinate)
    }

    private fun isHit(coordinate: Coordinate): Boolean {
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
                } else if (isHit(coordinate)) {
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
