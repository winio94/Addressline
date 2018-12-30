package build

import org.gradle.api.Plugin
import org.gradle.api.Project

class PitestPlugin implements Plugin<Project> {

    @Override
    void apply(Project project) {
        project.with {
            pitest {
                targetClasses = ['com.winio94.*']
                threads = 4
                outputFormats = ['XML', 'HTML']
                timestampedReports = false
                testPlugin = "junit5"
            }

            project.tasks.build.dependsOn('pitest')
        }
    }
}