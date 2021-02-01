import org.immutables.value.Value;

@Value.Style(
    typeBuilder = "BuilderImpl",
    builderVisibility = Value.Style.BuilderVisibility.PUBLIC
)
@Value.Immutable
interface A {
  static Builder builder() {
    return new Builder();
  }

  class Builder extends ImmutableA.BuilderImpl {
  }
}
