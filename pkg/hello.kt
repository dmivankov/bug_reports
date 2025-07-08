package hello

import com.google.devtools.build.runfiles.Runfiles

fun main() {
	println("kt hello")
	val executable = Runfiles.preload().unmapped().rlocation("_main/pkg/sh-bin")
        ProcessBuilder(executable)
            .redirectError(ProcessBuilder.Redirect.INHERIT)
            .redirectOutput(ProcessBuilder.Redirect.INHERIT)
            .start()
	    .waitFor()
}
