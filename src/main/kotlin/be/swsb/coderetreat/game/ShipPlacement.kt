package be.swsb.coderetreat.game

import be.swsb.coderetreat.location.Coordinate
import be.swsb.coderetreat.location.Direction.RIGHT
import be.swsb.coderetreat.location.Direction.UP
import be.swsb.coderetreat.location.Vector
import be.swsb.coderetreat.ships.Ship

class ShipPlacement(private val vector: Vector, val ship: Ship) {
    val coordinates get() = vector.getCoordinatesFor(ship)

    fun overlaps(otherShipPlacement: ShipPlacement): Boolean {
        return vector.getCoordinatesFor(ship).any { otherShipPlacement.contains(it) }
    }

    fun contains(coordinate: Coordinate): Boolean {
        return vector.getCoordinatesFor(ship).contains(coordinate)
    }
}

private fun Vector.getCoordinatesFor(ship: Ship): Set<Coordinate> {
    return UIntRange(0u, ship.length - 1u).map {
        if (this.direction == RIGHT) {
            return@map Coordinate(this.x + it, this.y)
        }
        if (this.direction == UP) {
            return@map Coordinate(this.x, this.y + it)
        }
        return@map Coordinate(this.x, this.y)
    }.toSet()
}
