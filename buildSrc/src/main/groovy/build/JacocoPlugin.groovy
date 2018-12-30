package build

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.testing.Test

class JacocoPlugin implements Plugin<Project> {

    @Override
    void apply(Project project) {
        project.with {
            apply plugin: 'jacoco'

            jacoco {
                toolVersion = "0.8.2"
                reportsDir = file("${buildDir}/reports/jacoco/${name}")
            }

            jacocoTestReport {
                reports {
                    html.enabled = true
                    xml.enabled = true
                }
            }

            tasks.jacocoTestReport.dependsOn(tasks.withType(Test))
            tasks.jacocoTestCoverageVerification.dependsOn(tasks.withType(Test))
            tasks.build.dependsOn('jacocoTestReport', 'jacocoTestCoverageVerification')
            project.ext.jacocoExcludes = []

            afterEvaluate {
                tasks.jacocoTestCoverageVerification.configure { task ->
                    violationRules {
                        rule {
                            element = 'CLASS'
                            excludes = project.jacocoExcludes
                            limit {
                                counter = 'LINE'
                                minimum = 1.0
                            }
                            limit {
                                counter = 'BRANCH'
                                minimum = 1.0
                            }
                        }
                    }
                }
            }

        }
    }
}
