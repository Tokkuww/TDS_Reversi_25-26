package commandPrompt

interface CommandPrompt {
    interface Command {
        val name: String
        val description: String
            get() = ""
        val argsName: List<String>
            get() = emptyList()
        val action: (List<String>) -> Unit

        fun displayUsage() =
            println("Usage: $name${argsName.joinToString(prefix = " ") { "<$it>" }}")
    }

    val commands: Map<String, Command>

    fun getCommand(): Pair<Command, List<String>>

    fun loop()
}