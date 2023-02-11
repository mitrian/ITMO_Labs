package labtwo.moves;

import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.SpecialMove;
import ru.ifmo.se.pokemon.Stat;
import ru.ifmo.se.pokemon.Type;

public class GigaDrainMove extends SpecialMove{

    public GigaDrainMove() { super(Type.GRASS, 75, 100);}

    @Override
    protected void applySelfEffects(Pokemon pokemon){
        pokemon.setMod(Stat.HP, (int) (Math.round(this.power/2.0)));
    }

    @Override
    protected String describe()
    {
        return "использует Giga Drain";
    }
}
