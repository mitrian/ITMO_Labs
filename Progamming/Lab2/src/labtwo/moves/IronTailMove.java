package labtwo.moves;

import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.PhysicalMove;
import ru.ifmo.se.pokemon.Type;
import ru.ifmo.se.pokemon.Effect;
import ru.ifmo.se.pokemon.Stat;


public class IronTailMove extends PhysicalMove {
    public IronTailMove(){ super(Type.STEEL, 100, 75 );}


    @Override
    protected void applyOppEffects(Pokemon p)
    {
        if ((new Effect()).chance(0.3D).success())
            p.setMod(Stat.DEFENSE, -1);

        super.applyOppEffects(p);

    }
    @Override
    protected String describe()
    {
        return "использует Iron Tail";
    }
}

