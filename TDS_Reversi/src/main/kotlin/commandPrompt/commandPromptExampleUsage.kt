package commandPrompt
import kotlin.random.Random

fun main() {
    // A simple guessing game: guess a number between 1 and 10
    val guessCommand = object : CommandPrompt.Command {
        override val name = "guess"
        override val description = "Guess a number between 1 and 10"
        override val argsName = listOf("number")
        override val action: (List<String>) -> Unit = action@{ args ->
            val playerGuess = args[0].toIntOrNull()
            if (playerGuess == null || playerGuess !in 1..10) {
                println("Please enter a valid number between 1 and 10.")
                return@action
            }

            val secret = Random.nextInt(1, 11)
            if (playerGuess == secret) {
                println("🎉 Correct! The number was $secret.")
            } else {
                println("❌ Wrong! The number was $secret.")
            }
        }
    }

    // Another fun command: roll a dice
    val rollCommand = object : CommandPrompt.Command {
        override val name = "roll"
        override val description = "Rolls a 6-sided dice"
        override val argsName = emptyList<String>()
        override val action: (List<String>) -> Unit = {
            val result = Random.nextInt(1, 7)
            println("🎲 You rolled a $result!")
        }
    }

    // Create the command prompt with our game commands
    val prompt = BaseCommandPrompt(
        userCommands = mapOf(
            guessCommand.name to guessCommand,
            rollCommand.name to rollCommand
        )
    )

    println("Welcome to the Mini Game Prompt!")
    println("Type 'ls' to list commands, 'help' for usage info, or 'exit' to quit.")
    prompt.loop()
}

