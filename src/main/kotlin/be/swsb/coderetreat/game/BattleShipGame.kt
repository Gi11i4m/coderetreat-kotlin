package be.swsb.coderetreat.game

sealed class BattleShipGame(
    val playerOneField: Field,
    val playerTwoField: Field
) {
    companion object {
        fun new(): BattleShipGameNotStarted {
            return BattleShipGameNotStarted()
        }
    }
}