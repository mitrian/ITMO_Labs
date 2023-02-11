package labtwo.moves;

import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.PhysicalMove;
import ru.ifmo.se.pokemon.Type;
import ru.ifmo.se.pokemon.Effect;
import ru.ifmo.se.pokemon.Stat;

public class CrunchMove extends PhysicalMove {
    public CrunchMove() {super(Type.DARK, 80, 100); }


    @Override
    protected void applyOppEffects(Pokemon pokemon)
    {
        if ((new Effect()).chance(0.2D).success())
            pokemon.setMod(Stat.DEFENSE, -1);
        super.applyOppEffects(pokemon);
    }

    @Override
    protected String describe()
    {
        return "использует Crunch";
    }

}
