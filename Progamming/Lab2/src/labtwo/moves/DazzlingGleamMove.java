package labtwo.moves;

import ru.ifmo.se.pokemon.SpecialMove;
import ru.ifmo.se.pokemon.Type;


public class DazzlingGleamMove extends SpecialMove {

    public DazzlingGleamMove() {super(Type.FAIRY, 80, 100);}

    @Override
    protected String describe()
    {
        return "использует Dazzling Gleam";
    }

}

