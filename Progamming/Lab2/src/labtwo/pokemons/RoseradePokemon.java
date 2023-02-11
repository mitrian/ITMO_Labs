package labtwo.pokemons;

import labtwo.moves.DazzlingGleamMove;
import labtwo.moves.GigaDrainMove;
import labtwo.moves.PoisonStingMove;
import labtwo.moves.ShadowBallMove;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Type;

public class RoseradePokemon extends Pokemon {
    public RoseradePokemon (String name, int level){
        super(name, level);
        setType(Type.GRASS, Type.POISON);
        setStats(60,70,65,125,105,90);
        addMove(new ShadowBallMove());
        addMove(new DazzlingGleamMove());
        addMove(new GigaDrainMove());
        addMove(new PoisonStingMove());
    }
}
