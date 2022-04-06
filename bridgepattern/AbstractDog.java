package bridgepattern;

// main hierarchy for dogs
public abstract class AbstractDog {
    // this is a feature for the hierarchy and basically it separates main hierarchy
    // from the feature hierarchy, and this ref is actually a BRIDGE
    // SO WE DO NOT SUBCLASS DOGS SO WE DO NOT MULTIPLY DIFFERENT SUBCLASSES OF DOGS BY DIFFERENT TRAININGS
    private DogTraining dogTraining;
    abstract public void bark();

    public DogTraining getDogTraining() {
        return dogTraining;
    }

    public void setDogTraining(DogTraining dogTraining) {
        this.dogTraining = dogTraining;
    }
}
