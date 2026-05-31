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

    doLast {
        println("Custom verification task has been finished!")
    }


}

