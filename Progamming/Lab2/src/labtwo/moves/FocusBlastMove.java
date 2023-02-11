package labtwo.moves;

import ru.ifmo.se.pokemon.SpecialMove;
import ru.ifmo.se.pokemon.Effect;
import ru.ifmo.se.pokemon.Stat;
import ru.ifmo.se.pokemon.Type;
import ru.ifmo.se.pokemon.Pokemon;

public class FocusBlastMove extends SpecialMove{
    public FocusBlastMove() {super(Type.FIGHTING, 120, 70);}


    @Override
    protected void applyOppEffects(Pokemon pokemon){
        if ((new Effect()).chance(0.1D).success())
            pokemon.setMod(Stat.SPECIAL_DEFENSE, -1);

        super.applyOppEffects(pokemon);
    }
    @Override
    protected String describe()
    {
        return "использует Focus Blast";
    }



}
