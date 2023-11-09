package be.swsb.coderetreat.game

sealed class BattleShipGame(
    val shipPlacementsPlayerOne: HashSet<ShipPlacement>,
    val shipPlacementsPlayerTwo: HashSet<ShipPlacement>
) {
    companion object {
        fun new(): BattleShipGameNotStarted {
            return BattleShipGameNotStarted()
        }
    }
}