`rules_java` in MODULE.bazel
- 9.2.0: `bazel coverage //...` works
- 9.3.0: `bazel coverage //...` fails on kotlin target

Extra debugging steps:
- Java
```console
$ bazel clean
$ bazel run --collect_code_coverage //bazel_coverage_test:java_test
$ find -L . -name "*.jar" -type f -exec sh -c 'jar tf "$1" | grep "Offline.class" && echo "$1"' _ {} \;
...only org/jacoco/agent/rt/internal_29a6edd/Offline.class are showing... 
$ bazel coverage --nocache_test_results --test_output=all --test_env=JAVA_TOOL_OPTIONS=-verbose:class //bazel_coverage_test:java_test
...org/jacoco/agent/rt/internal_29a6edd/Offline.class is loading... 
```
- Kotlin
```console
$ bazel clean
$ bazel run --collect_code_coverage //bazel_coverage_test:kotlin_test
$ find -L . -name "*.jar" -type f -exec sh -c 'jar tf "$1" | grep "Offline.class" && echo "$1"' _ {} \;
...some org/jacoco/agent/rt/internal_29a6edd/Offline.class are showing... 
...some org/jacoco/agent/rt/internal_4742761/Offline.class are showing too... 
$ bazel coverage --nocache_test_results --test_output=all --test_env=JAVA_TOOL_OPTIONS=-verbose:class //bazel_coverage_test:java_test
...org/jacoco/agent/rt/internal_29a6edd/Offline.class is not loading... 
...org/jacoco/agent/rt/internal_4742761/Offline.class fails to load... 
```
