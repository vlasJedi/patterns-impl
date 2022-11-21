package builderpattern;

public class StringFromEventsBuilder implements EventsBuilder{
    private String recordedEvents = "";

    @Override
    public void addClickEvent() {
        recordedEvents += ".";
    }

    @Override
    public void addKeyboardEvent() {
        recordedEvents += "^";
    }

    public String getResult() {
        return recordedEvents;
    }
}
