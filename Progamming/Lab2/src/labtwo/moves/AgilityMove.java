package labtwo.moves;

import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Stat;
import ru .ifmo.se.pokemon.StatusMove;
import ru.ifmo.se.pokemon.Type;

public class AgilityMove extends StatusMove {

    public AgilityMove() { super(Type.PSYCHIC, 0, 0);}

    @Override
    protected void applySelfEffects(Pokemon pokemon){
        pokemon.setMod(Stat.SPEED, 2);
        super.applySelfEffects(pokemon);
    }
    @Override
    protected String describe()
    {
        return "использует Agility";
    }
}
