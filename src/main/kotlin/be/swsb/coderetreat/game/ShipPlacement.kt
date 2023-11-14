package be.swsb.coderetreat.game

import be.swsb.coderetreat.location.Coordinate
import be.swsb.coderetreat.location.Direction.RIGHT
import be.swsb.coderetreat.location.Direction.UP
import be.swsb.coderetreat.location.Vector
import be.swsb.coderetreat.ships.Ship

class ShipPlacement(vector: Vector, val ship: Ship) {
    val coordinates: Set<Coordinate>
        get() {
            return field
        }

    init {
        coordinates = shipVectorToCoordinateList(ship, vector)
    }

    private fun shipVectorToCoordinateList(ship: Ship, vector: Vector): Set<Coordinate> {
        return UIntRange(0u, ship.length - 1u).map {
            if (vector.direction == RIGHT) {
                return@map Coordinate(vector.x + it, vector.y)
            }
            if (vector.direction == UP) {
                return@map Coordinate(vector.x, vector.y + it)
            }
            return@map Coordinate(vector.x, vector.y)
        }.toSet()
    }

    fun overlaps(otherShipPlacement: ShipPlacement): Boolean {
        return coordinates.any { otherShipPlacement.coordinates.contains(it) }
    }

    fun contains(coordinate: Coordinate): Boolean {
        return coordinates.contains(coordinate)
    }
}
