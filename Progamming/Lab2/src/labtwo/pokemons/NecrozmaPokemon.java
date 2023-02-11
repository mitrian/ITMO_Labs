package labtwo.pokemons;

import labtwo.moves.*;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Type;


public class NecrozmaPokemon extends Pokemon {

    public NecrozmaPokemon (String name, int level){
        super(name, level);
        setType(Type.PSYCHIC);
        setStats(97, 107, 101,127,89,79);
        addMove(new FireBlastMove());
        addMove(new FacadeMove());
        addMove(new IronTailMove());
        addMove(new CrunchMove());
    }
    
}
