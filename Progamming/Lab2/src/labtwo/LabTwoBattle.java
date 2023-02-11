package labtwo;

import labtwo.pokemons.*;
import ru.ifmo.se.pokemon.Battle;

public class LabTwoBattle {


    public static void main(String[] args) {
        Battle b = new Battle();

        b.addFoe(new ArchenPokemon("Арсен",1));
        b.addFoe(new BudewPokemon("Покемон-капуста",1));
        b.addFoe(new NecrozmaPokemon("Камешек",1));

        b.addAlly(new ArcheopsPokemon("Кеша",1));
        b.addAlly(new RoseliaPokemon("Шипастая",1));
        b.addAlly(new RoseradePokemon("Блонда",1));

        b.go();

    }
}
