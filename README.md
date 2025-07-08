With `rules_kotlin` 2.1.4

```console
$ bazel run pkg:kt-bin

kt hello
sh hello
content
```

With `rules_kotlin` 2.1.8
```console
$ bazel run pkg:kt-bin

kt hello
ERROR: cannot find bazel_tools/tools/bash/runfiles/runfiles.bash
```
