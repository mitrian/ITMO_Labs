package labtwo.moves;

import ru.ifmo.se.pokemon.Effect;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.SpecialMove;
import ru.ifmo.se.pokemon.Type;

public class FireBlastMove extends SpecialMove {

    public FireBlastMove()
    {
        super(Type.FIRE, 110, 85);
    }

    @Override
    protected void applyOppEffects(Pokemon p)
    {

        if ((new Effect()).chance(0.1D).success())
            Effect.burn(p);

        super.applyOppEffects(p);

    }
    @Override
    protected String describe()
    {
        return "использует Fire Blast";
    }
}
