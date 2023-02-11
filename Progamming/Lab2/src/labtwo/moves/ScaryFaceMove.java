package labtwo.moves;

import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Stat;
import ru.ifmo.se.pokemon.StatusMove;
import ru.ifmo.se.pokemon.Type;

public class ScaryFaceMove extends StatusMove {
    public ScaryFaceMove() {super(Type.NORMAL, 0, 100);}



    @Override
    protected void applyOppEffects(Pokemon pokemon){
        pokemon.setMod(Stat.SPEED, -2);
        super.applyOppEffects(pokemon);
    }

    @Override
    protected String describe()
    {
        return "использует Scary Face";
    }
}
