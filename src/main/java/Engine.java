import core.DataViewer;
import core.EntityEngine;

/**
 * The Engine is the start point of the simulation and provides means of interacting with it and providing views on the data
 */
public class Engine {

    private EntityEngine entityEngine;

    private Engine(){
        entityEngine = new EntityEngine();
    }

    public DataViewer getDataViewer(){
        return null; //TODO implement this stuff
    }
}
