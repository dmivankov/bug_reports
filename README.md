Building `JavaBuilder_deploy_*.jar` files from source
```console
# checkout bazel source code, then
$ bazel build //src/java_tools/buildjar:JavaBuilder_deploy.jar
...
# copy bazel-bin/src/java_tools/buildjar/JavaBuilder_deploy.jar to appropriate location in this repo
```

Start local buildbarn remote instance

```console
$ git clone https://github.com/buildbarn/bb-deployments.git
$ cd bb-deployments
$ git checkout eae07e972336ff6bbd470a994cbb3264a7e59692
$ cd docker-compose
$ ./run.sh
```

Run bazel coverage locally
```console
$ bazel clean
# --nohome_rc is to remove --disk_cache if you have it
$ bazel --nohome_rc coverage //:javatest

//:javatest                                                              PASSED in 0.7s
```

Run bazel coverage using `fuse` worker
```console
$ bazel clean
# --nohome_rc is to remove --disk_cache if you have it
$ bazel --nohome_rc coverage --config=remote-fuse //:javatest

Remote server execution message: Action details (uncached result): http://localhost:7984/fuse/blobs/sha256/historical_execute_response/fdfbff8141888814c5690774f4249e5dd0096b055577ea4ceaff45454538effa-1248/
java.nio.file.FileAlreadyExistsException: bazel-out/k8-fastbuild/bin/bazel-out/k8-fastbuild/bin/liblib.jar-coverage-metadata/com/cognite/Lib.class.uninstrumented
	at java.base/sun.nio.fs.UnixFileSystem.move(UnixFileSystem.java:906)
	at java.base/sun.nio.fs.UnixFileSystemProvider.move(UnixFileSystemProvider.java:309)
	at java.base/java.nio.file.Files.move(Files.java:1430)
	at com.google.devtools.build.buildjar.instrumentation.JacocoInstrumentationProcessor$1.visitFile(JacocoInstrumentationProcessor.java:136)
	at com.google.devtools.build.buildjar.instrumentation.JacocoInstrumentationProcessor$1.visitFile(JacocoInstrumentationProcessor.java:113)
	at java.base/java.nio.file.Files.walkFileTree(Files.java:2786)
	at java.base/java.nio.file.Files.walkFileTree(Files.java:2857)
	at com.google.devtools.build.buildjar.instrumentation.JacocoInstrumentationProcessor.instrumentRecursively(JacocoInstrumentationProcessor.java:111)
	at com.google.devtools.build.buildjar.instrumentation.JacocoInstrumentationProcessor.processRequest(JacocoInstrumentationProcessor.java:85)
	at com.google.devtools.build.buildjar.SimpleJavaLibraryBuilder.buildJar(SimpleJavaLibraryBuilder.java:151)
	at com.google.devtools.build.buildjar.SimpleJavaLibraryBuilder.run(SimpleJavaLibraryBuilder.java:120)
	at com.google.devtools.build.buildjar.BazelJavaBuilder.build(BazelJavaBuilder.java:111)
	at com.google.devtools.build.buildjar.BazelJavaBuilder.parseAndBuild(BazelJavaBuilder.java:91)
	at com.google.devtools.build.buildjar.BazelJavaBuilder.main(BazelJavaBuilder.java:75)
```

Run bazel coverage using `fuse` worker with fix from https://github.com/bazelbuild/bazel/pull/25273
```console
$ bazel clean
# --nohome_rc is to remove --disk_cache if you have it
$ bazel --nohome_rc coverage --config=remote-fuse --config=fix //:javatest

//:javatest                                                              PASSED in 0.7s
```

Run bazel coverage using `hardlinking` worker
```console
$ bazel clean
# --nohome_rc is to remove --disk_cache if you have it
$ bazel --nohome_rc coverage --config=remote-hardlinking //:javatest

//:javatest                                                              PASSED in 0.7s
```
