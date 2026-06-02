plugins {
    id("java")
    id("org.example.custom-gradle-plugin") version "1.0-SNAPSHOT"
}

group = "org.example.plugin"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()

}

customproperty {
    companyDomain.set("gmail.com")
    forbiddenImports.set(
        listOf(
            "java.util.Date",
            "org.example.plugin.forbiddenImports.ForbiddenClass"
        )
    )
}
dependencies {
}


tasks.register("runVerificationTasks"){
    group = "verification"
    description = "Runs two custom tasks: Analyzing forbidden imports, Checking git email domain of a current user"

    doFirst {
        println("Starting custom verification task!")
    }
    dependsOn("checkGitEmail", "analyzeForbiddenImports")

    finalizedBy("verificationReport")


}
tasks.named("checkGitEmail") { finalizedBy("verificationReport") }
tasks.named("analyzeForbiddenImports") { finalizedBy("verificationReport") }

tasks.register("verificationReport"){
    doLast {


        val gitTaskState = tasks.named("checkGitEmail").get().state
        val importsTaskState = tasks.named("analyzeForbiddenImports").get().state

        println(
            """
            ===VERIFICATION REPORT===
            Git Email check executed: Executed: ${gitTaskState.executed}, Failure: ${gitTaskState.failure}
            Import Task check executed: Executed: ${importsTaskState.executed}, Failure: ${importsTaskState.failure}
            
        """.trimIndent()
        )

    }
}

