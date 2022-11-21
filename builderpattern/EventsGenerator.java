package builderpattern;

// class that used a provided builder to build final product
public class EventsGenerator {
    private final EventsBuilder eventsBuilder;

    public EventsGenerator(EventsBuilder eventsBuilder) {
        this.eventsBuilder = eventsBuilder;
    }

    public void emit() {
        eventsBuilder.addClickEvent();
        eventsBuilder.addClickEvent();
        eventsBuilder.addKeyboardEvent();
    }
}
