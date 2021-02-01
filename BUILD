java_plugin(
    name = "immutables_processor",
    generates_api = True,
    javacopts = ["-Xep:UnnecessaryCheckNotNull:OFF"],  # TODO: remove after switching to immutables 2.8.9
    processor_class = "org.immutables.processor.ProxyProcessor",
    deps = [
        "@maven//:org_immutables_value",
    ],
)

java_library(
    name = "a",
    srcs = ["A.java"],
    plugins = [":immutables_processor"],
    deps = ["@maven//:org_immutables_value"],
)

java_library(
    name = "b",
    srcs = ["B.java"],
    deps = [":a"],
)
