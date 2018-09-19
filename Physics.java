/**
 * This interface is useful for physics games objects, or just objects that will need to be updated somehow.
 * Using the interface allows it to be collected in a collection with other Physics based objects.
 */
public interface Physics {

    void update(double delta);

}
