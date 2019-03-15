package com.github.korniloval.fierix

import com.intellij.openapi.diagnostic.Logger
import java.io.File
import java.net.URISyntaxException
import java.nio.file.Paths


object FileUtil {
    private val LOG = Logger.getInstance(FileUtil::class.java)

    fun getPathToJar(jarName: String): String? {
        val url = javaClass.getResource("/$jarName")
        try {
            return Paths.get(url.toURI()).toString()
        } catch (e: URISyntaxException) {
            LOG.error(e)
        }
        return null
    }
}

fun File.child(name: String): File = toPath().resolve(name).toFile()
