import java.util.ArrayList;

public class FinalProject
{
    //west = false. east = true. maybe not the best for readability but it's so cute
    public static boolean west = Boolean.valueOf("false");
    public static boolean east = Boolean.valueOf("true");
    public static boolean allAreEast = false;
    static ArrayList<StateOfWorld> solution = new ArrayList<>();

    //where solve is first executed
    public static void main(String[] args)
    {
        solve(west, west, west, west);
        //this portion of main() cannot be accessed until allAreEast = true
        for (StateOfWorld state : solution)
        {
            System.out.print(printStateOfWorld("man", state.man)+", ");
            System.out.print(printStateOfWorld("cabbage", state.cabbage)+", ");
            System.out.print(printStateOfWorld("goat", state.goat)+", and ");
            System.out.print(printStateOfWorld("wolf", state.wolf)+".\r\n");
        }

    }

    public static void solve(boolean man, boolean cabbage, boolean goat, boolean wolf)
    {
        //check if all are east FIRST (DUH!!!!)
        if (man == east &
            cabbage == east &
            goat == east &
            wolf == east)
        {
            solution.add(new StateOfWorld(man, cabbage, goat, wolf));//add the current state to list
            allAreEast = true; //not using "east" here :)

        }
        //ILLEGAL STATE: GOAT AND CABBAGE ALONE TOGETHER
        else if (man == west &
                 goat == east &
                 cabbage == east);//no statement -- avoids the "else" block for this runthrough
        //ILLEGAL STATE: GOAT AND CABBAGE ALONE TOGETHER
        else if (man == east &
                 goat == west &
                 cabbage == west);//no statement -- avoids the "else" block for this runthrough
        //ILLEGAL STATE: GOAT AND WOLF ALONE TOGETHER
        else if (man == west &
                 goat == east &
                 wolf == east);//no statement -- avoids the "else" block for this runthrough
        //ILLEGAL STATE: GOAT AND WOLF ALONE TOGETHER
        else if (man == east &
                 goat == west &
                 wolf == west);//no statement -- avoids the "else" block for this runthrough

        //ALREADY VISITED STATE -- to avoid endless loop
        //else if (solution.contains(new StateOfWorld(man, cabbage, goat, wolf)));
        else if (alreadyVisited(new StateOfWorld(man, cabbage, goat, wolf)));   //creates new StateOfWorld object and checks if it exists in solution.
                                                                                //avoids the "else" block for this runthrough if true.
        else//where the magic happens
        {
            solution.add(new StateOfWorld(man, cabbage, goat, wolf));//adds current state to solution because
                                                                     //if it reached this point then it is a valid state.
            //the next several if statements go through every possible movement the man can make.
            //whatever state the man is in, have him try the opposite along with another object on the same bank.
            //non-moving objects retain their bank location.
            //if it reaches this point in a recursive call that means the state was not invalid.
            //it also means that the state will always hit a different "if" statement than it did before.
            if (man == west & cabbage == west)
                solve(east, east, goat, wolf);
            if (man == east & cabbage == east)
                solve(west, west, goat, wolf);
            if (man == west & goat == west)
                solve(east, cabbage, east, wolf);
            if (man == east & goat == east)
                solve(west, cabbage, west, wolf);
            if (man == west & wolf == west)
                solve(east, cabbage, goat, east);
            if (man == east & wolf == east)
                solve(west, cabbage, goat, west);
            if (man == west)//he can also take nothing... of course. what a headache...
                solve(east, cabbage, goat, wolf);
            if (man == east)
                solve(west, cabbage, goat, wolf);

            //program will never get here!! :)
        }

    }

    public static String printStateOfWorld(String object, boolean bank)
    {
        String bankString = new String();
        if (bank == west)
            bankString = "west";
        if (bank == east)
            bankString = "east";
        return ("the "+object+" is on the "+bankString+" bank");
    }

    public static boolean alreadyVisited(StateOfWorld stateOfWorld)
    {
        for (StateOfWorld state : solution)
        {
            if (state.man == stateOfWorld.man &
                state.cabbage == stateOfWorld.cabbage &
                state.goat == stateOfWorld.goat &
                state.wolf == stateOfWorld.wolf)
                return true;
        }
        return false;
    }

}
