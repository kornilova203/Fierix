package com.github.korniloval.fierix.execution

import com.github.kornilova_l.flamegraph.configuration.Configuration
import com.github.korniloval.fierix.FileUtil
import com.github.korniloval.fierix.child
import com.github.korniloval.fierix.configuration.ConfigStorage
import com.github.korniloval.fierix.configuration.PluginConfigManager
import com.intellij.execution.ExecutionException
import com.intellij.execution.configurations.JavaParameters
import com.intellij.execution.configurations.RunProfile
import com.intellij.execution.configurations.RunnerSettings
import com.intellij.execution.impl.DefaultJavaProgramRunner
import com.intellij.execution.runners.ExecutionEnvironment
import com.intellij.openapi.diagnostic.Logger
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

/**
 * ProgramRunner which runs Profiler Executor
 */
class ProfilerProgramRunner : DefaultJavaProgramRunner() {
    private val LOG = Logger.getInstance(ProfilerProgramRunner::class.java)
    private var configuration: Configuration? = null
    private var projectDir: File? = null

    @Throws(ExecutionException::class)
    override fun execute(environment: ExecutionEnvironment) {
        projectDir = File(environment.project.baseDir.path)
        configuration = environment.project.getComponent(ConfigStorage::class.java).state as Configuration
        super.execute(environment)
    }

    override fun patch(javaParameters: JavaParameters,
                       settings: RunnerSettings?,
                       runProfile: RunProfile,
                       beforeExecution: Boolean) {
        val projectDir = projectDir
        if (projectDir == null) {
            LOG.error("Project dir is null")
            return
        }
        val configuration = configuration
        if (configuration == null) {
            LOG.error("Configuration is null")
            return
        }
        val configFile = projectDir.child("Fierix-config.txt")
        PluginConfigManager.exportConfig(configFile, configuration)
        val pathToAgent = FileUtil.getPathToJar("javaagent.jar") ?: return
        /* add javaagent */
        javaParameters.vmParametersList.add(
                "-javaagent:" +
                        pathToAgent +
                        "=" +
                        createOutputFierixFile(runProfile.name, projectDir).absolutePath +
                        "&" +
                        configFile.absolutePath
        )
        /* add Proxy class to classpath. It is for classes that do not have system classloader in chain */
        javaParameters.classPath.add(FileUtil.getPathToJar("proxy.jar"))
    }

    private fun createOutputFierixFile(configurationName: String, projectDir: File): File {
        val name = configurationName + "-" + SimpleDateFormat("yyyy-MM-dd-HH_mm_ss").format(Date())
        return projectDir.child(name)
    }

    override fun getRunnerId(): String {
        return RUNNER_ID
    }

    override fun canRun(executorId: String, profile: RunProfile): Boolean {
        return executorId == ProfilerExecutor.EXECUTOR_ID
    }

    companion object {
        private const val RUNNER_ID = "ProfileRunnerID"
    }
}
