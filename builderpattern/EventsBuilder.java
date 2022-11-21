package builderpattern;

// only one abstraction in the pattern and declares abstract methods to build product
// return of abstract product not required as object of concrete class is used for fetch of final product
public interface EventsBuilder {
    void addClickEvent();
    void addKeyboardEvent();
}
