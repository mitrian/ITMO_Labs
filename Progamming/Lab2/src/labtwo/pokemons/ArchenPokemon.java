package labtwo.pokemons;

import labtwo.moves.AgilityMove;
import labtwo.moves.ScaryFaceMove;
import labtwo.moves.SteelWingMove;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Type;

public class ArchenPokemon extends Pokemon {

    public ArchenPokemon(String name, int level){
        super(name, level);
        setType(Type.ROCK, Type.FLYING);
        setStats(55,112,45,74,45,70);
        addMove(new SteelWingMove());
        addMove(new AgilityMove());
        addMove(new ScaryFaceMove());
    }
}

