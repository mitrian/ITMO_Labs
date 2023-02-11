package labtwo.pokemons;

import labtwo.moves.DazzlingGleamMove;
import labtwo.moves.GigaDrainMove;
import labtwo.moves.ShadowBallMove;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Type;

public class RoseliaPokemon extends Pokemon {

    public RoseliaPokemon(String name, int level){
        super(name,level);
        setType(Type.POISON, Type.GHOST);
        setStats(50,60,45,100,80,65);
        addMove(new ShadowBallMove());
        addMove(new DazzlingGleamMove());
        addMove(new GigaDrainMove());
    }
}
