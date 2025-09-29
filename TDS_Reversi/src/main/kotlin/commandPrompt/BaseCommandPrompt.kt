package commandPrompt


import kotlin.system.exitProcess

const val PROMPT = "> "

class BaseCommandPrompt(userCommands: Map<String, CommandPrompt.Command>): CommandPrompt {
    companion object {
        val Exit = object: CommandPrompt.Command {
            override val name = "exit"
            override val description = "Exits the prompt"
            override val argsName = emptyList<String>()
            override val action: (List<String>) -> Unit = {
                println("Exiting...")
                exitProcess(0)
            }
        }
    }

    // LS command: just prints command names
    val ls = object: CommandPrompt.Command {
        override val name = "ls"
        override val description = "Lists all available commands"
        override val argsName = emptyList<String>()
        override val action: (List<String>) -> Unit = {
            println("Commands:")
            commands.keys.forEach { println("- $it") }
        }
    }

    // Help command: prints descriptions and usage
    val help = object : CommandPrompt.Command {
        override val name = "help"
        override val description = "Shows help for a command"
        override val argsName = listOf("commandName<Optional>")
        override val action: (List<String>) -> Unit = { args ->
            val cmdName = args.firstOrNull() // safe null if no argument

            if (cmdName == null) {
                // No argument → list all commands
                println("Commands and descriptions:")
                commands.values.forEach { cmd ->
                    println("- ${cmd.name}: ${cmd.description}")
                    cmd.displayUsage()
                }
            }
            else {
                // Argument provided → show that command
                val cmd = commands[cmdName]
                if (cmd != null) {
                    println("${cmd.name}: ${cmd.description}")
                    cmd.displayUsage()
                }
                else
                    println("'$cmdName' command was not found. Use the 'ls' command to list available commands.")
            }
        }
    }

    override val commands: Map<String, CommandPrompt.Command> =
        userCommands + mapOf(
            Exit.name to Exit,
            help.name to help,
            ls.name to ls
        )

    override fun getCommand(): Pair<CommandPrompt.Command, List<String>> {
        fun parseCommand(command: String) =
            command
                .lowercase()
                .split("\\s+".toRegex()).filter { it.isNotBlank() }

        while (true) {
            print(PROMPT)
            val input = readln()
            val tokens = parseCommand(input)

            val tokenAmount = tokens.size

            if (tokenAmount <= 0) {
                println("No command found. Please enter a command. Use the 'ls' command to list available commands.")
                continue
            }

            val commandName = tokens[0]
            val command = commands[commandName]

            if (command == null) {
                println("'$commandName' command was not found. Use the 'ls' command to list available commands.")
                continue
            }

            val requiredArgsAmount = command.argsName.count { !it.endsWith("<Optional>") }
            if (tokenAmount - 1 < requiredArgsAmount) {
                println("Invalid number of arguments for '$commandName'. Use the 'help' command to show help for commands.")
                command.displayUsage()
                continue
            }

            val commandArgs = tokens.subList(1, tokens.size)

            return Pair(command, commandArgs)
        }
    }

    override fun loop() {
        while (true) {
            val (command, args) = getCommand()
            command.action(args)
        }
    }
}