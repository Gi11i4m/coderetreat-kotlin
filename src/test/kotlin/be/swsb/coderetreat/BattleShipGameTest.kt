package be.swsb.coderetreat

import be.swsb.coderetreat.game.BattleShipGame
import be.swsb.coderetreat.game.BattleShipGameInProgress
import be.swsb.coderetreat.game.GameStatus.*
import be.swsb.coderetreat.game.BattleShipGameNotStarted
import be.swsb.coderetreat.location.Direction.RIGHT
import be.swsb.coderetreat.location.Direction.UP
import be.swsb.coderetreat.location.Coordinate
import be.swsb.coderetreat.location.Vector
import be.swsb.coderetreat.ships.*
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertInstanceOf
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

fun aValidGame(): BattleShipGameInProgress {
    return BattleShipGame.new()
        .placePlayerOneShip(Vector(1u, 1u, RIGHT), Carrier())
        .placePlayerOneShip(Vector(3u, 1u, UP), BattleShip())
        .placePlayerOneShip(Vector(3u, 3u, RIGHT), Destroyer())
        .placePlayerOneShip(Vector(5u, 5u, RIGHT), Submarine())
        .placePlayerOneShip(Vector(8u, 7u, UP), PatrolBoat())

        .placePlayerTwoShip(Vector(1u, 1u, RIGHT), Carrier())
        .placePlayerTwoShip(Vector(3u, 1u, UP), BattleShip())
        .placePlayerTwoShip(Vector(3u, 3u, RIGHT), Destroyer())
        .placePlayerTwoShip(Vector(5u, 5u, RIGHT), Submarine())
        .placePlayerTwoShip(Vector(8u, 7u, UP), PatrolBoat())
        .start()
}

class BattleShipGameTest {

    @Test
    fun `new game`() {
        assertInstanceOf(aValidGame()::class.java, BattleShipGameNotStarted::class.java)
    }

    @Test
    fun `player always hits and wins`() {
        val game = aValidGame()
            // Carrier
            .playerOneFire(Coordinate(1u, 1u))
            .playerOneFire(Coordinate(2u, 1u))
            .playerOneFire(Coordinate(3u, 1u))
            .playerOneFire(Coordinate(4u, 1u))
            .playerOneFire(Coordinate(5u, 1u))
            // BattleShip
            .playerOneFire(Coordinate(3u, 1u))
            .playerOneFire(Coordinate(3u, 2u))
            .playerOneFire(Coordinate(3u, 3u))
            .playerOneFire(Coordinate(3u, 4u))
            // Destroyer
            .playerOneFire(Coordinate(3u, 3u))
            .playerOneFire(Coordinate(4u, 3u))
            .playerOneFire(Coordinate(5u, 3u))
            // Submarine
            .playerOneFire(Coordinate(5u, 5u))
            .playerOneFire(Coordinate(6u, 5u))
            .playerOneFire(Coordinate(7u, 5u))
            // PatrolBoat
            .playerOneFire(Coordinate(8u, 7u))
            .playerOneFire(Coordinate(8u, 8u))

        assertEquals(game.status, PLAYER_ONE_WINS)
    }

    @Test
    fun `player hits`() {
        val game = aValidGame()
            .playerOneFire(Coordinate(2u, 1u))

        assertEquals(game.status, PLAYER_ONE_HIT)
    }

    @Test
    fun `player misses and loses turn`() {
        val game = aValidGame()
            .playerOneFire(Coordinate(2u, 2u))

        assertEquals(game.status, PLAYER_ONE_MISSED)
    }

    @Test
    fun `player fires at the same spot twice (miss)`() {
        val game = aValidGame()
            .playerOneFire(Coordinate(1u, 1u))
            .playerOneFire(Coordinate(1u, 1u))

        assertEquals(game.status, PLAYER_TWO_MISSED)
    }

    @Test
    fun `player fires out of turn`() {
        val exception = assertThrows<Error> { aValidGame().playerTwoFire(Coordinate(1u, 1u)) }

        assertEquals(exception.message, "It is not player two's turn")
    }

    @Test
    fun `player fires out of bounds`() {
        val exception = assertThrows<Error> { aValidGame().playerTwoFire(Coordinate(10u, 10u)) }

        assertEquals(exception.message, "Shot out of bounds")
    }

    @Test
    fun `out of bounds ship placement`() {
        val exception =
            assertThrows<Error> { BattleShipGame.new().placePlayerOneShip(Vector(7u, 7u, RIGHT), Carrier()) }

        assertEquals(exception.message, "Ship placed out of bounds")
    }

    @Test
    fun `not all ships placed when starting game`() {
        val exception = assertThrows<Error> {
            BattleShipGame.new()
                .placePlayerOneShip(Vector(1u, 1u, RIGHT), Carrier())
                .placePlayerOneShip(Vector(3u, 1u, UP), BattleShip())
                .placePlayerOneShip(Vector(3u, 3u, RIGHT), Destroyer())
                .placePlayerOneShip(Vector(5u, 5u, RIGHT), Submarine())
                .placePlayerOneShip(Vector(8u, 7u, UP), PatrolBoat())

                .placePlayerTwoShip(Vector(1u, 1u, RIGHT), Carrier())
                .placePlayerTwoShip(Vector(3u, 1u, UP), BattleShip())
                .placePlayerTwoShip(Vector(5u, 5u, RIGHT), Submarine())
                .placePlayerTwoShip(Vector(8u, 7u, UP), PatrolBoat())

                .start()
        }

        assertEquals(exception.message, "Not all ships have been placed")
    }

    @Test
    fun `trying to place a ship twice`() {
        val exception = assertThrows<Error> {
            BattleShipGame.new()
                .placePlayerOneShip(Vector(1u, 1u, RIGHT), Carrier())
                .placePlayerOneShip(Vector(1u, 1u, RIGHT), Carrier())
        }

        assertEquals(exception.message, "Tried to place the same ship twice")
    }

    @Test
    fun `overlapping ships placed`() {
        val exception = assertThrows<Error> {
            BattleShipGame.new()
                .placePlayerOneShip(Vector(3u, 1u, UP), Carrier())
                .placePlayerOneShip(Vector(2u, 2u, RIGHT), BattleShip())
        }

        assertEquals(exception.message, "Tried to place overlapping ships")
    }
}

