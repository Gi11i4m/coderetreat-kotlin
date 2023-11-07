package be.swsb.coderetreat

import be.swsb.coderetreat.Direction.RIGHT
import be.swsb.coderetreat.Direction.UP
import org.junit.jupiter.api.Test

fun aValidGame(): BattleShipGame {
    return BattleShipGame()
        .placePlayerOneShip(Vector(1, 1, RIGHT), CarrierShip())
        .placePlayerOneShip(Vector(3, 1, UP), BattleShip())
        .placePlayerOneShip(Vector(3, 3, RIGHT), DestroyerShip())
        .placePlayerOneShip(Vector(5, 5, RIGHT), SubmarineShip())
        .placePlayerOneShip(Vector(8, 9, UP), PatrolShip())
        .placePlayerTwoShip(Vector(1, 1, RIGHT), CarrierShip())
        .placePlayerTwoShip(Vector(3, 1, UP), BattleShip())
        .placePlayerTwoShip(Vector(3, 3, RIGHT), DestroyerShip())
        .placePlayerTwoShip(Vector(5, 5, RIGHT), SubmarineShip())
        .placePlayerTwoShip(Vector(8, 9, UP), PatrolShip())
}

class BattleShipGameTest {

    @Test
    fun scenario() {
        val game = aValidGame()
            .playerOneFire(Coordinate())
            .playerTwoFire(Coordinate())
    }

    @Test
    fun `scenario - out of bounds ship placements`() {
        TODO()
    }

    @Test
    fun `scenario - not all ships placed`() {
        TODO()
    }

    @Test
    fun `scenario - overlapping ships placed`() {
        TODO()
    }
}

