package labtwo.pokemons;

import labtwo.moves.AgilityMove;
import labtwo.moves.FocusBlastMove;
import labtwo.moves.ScaryFaceMove;
import labtwo.moves.SteelWingMove;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Type;

public class ArcheopsPokemon extends Pokemon {

    public ArcheopsPokemon (String name, int level){
        super(name, level);
        setType(Type.ROCK, Type.FLYING);
        setStats(75,140 , 65,112,65,110);
        addMove(new SteelWingMove());
        addMove(new AgilityMove());
        addMove(new ScaryFaceMove());
        addMove(new FocusBlastMove());
    }
}
