package labtwo.moves;

import ru.ifmo.se.pokemon.PhysicalMove;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Status;
import ru.ifmo.se.pokemon.Type;

public class FacadeMove extends PhysicalMove {
    public FacadeMove()
    {
        super(Type.NORMAL, 70, 100);
    }

    @Override
    protected void applyOppDamage(Pokemon p, double v) {
        Status s = p.getCondition();

        if (s == Status.BURN || s == Status.POISON || s == Status.PARALYZE)
            v = 140;
        super.applyOppDamage(p, v);
    }
    @Override
    protected String describe()
    {
        return "использует Facade";
    }
}
