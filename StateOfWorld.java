public class StateOfWorld
{

    //whether object is on west or east side is represented by false or true respectively.
    public boolean man;
    public boolean cabbage;
    public boolean goat;
    public boolean wolf;



    public StateOfWorld(boolean manNewSide, boolean cabbageNewSide, boolean goatNewSide, boolean wolfNewSide)
    {
        //loads new StateOfWorld object with current location of the four objects
        man = manNewSide;
        cabbage = cabbageNewSide;
        goat = goatNewSide;
        wolf = wolfNewSide;
    }
}