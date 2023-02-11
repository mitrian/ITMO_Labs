package labtwo.moves;

import ru.ifmo.se.pokemon.*;


public class SteelWingMove extends PhysicalMove{
    public SteelWingMove (){ super(Type.STEEL, 70, 90);}

    @Override
    protected void  applySelfEffects(Pokemon pokemon)
    {
        if ((new Effect()).chance(0.1D).success())
            pokemon.setMod(Stat.DEFENSE, 1);
        super.applySelfEffects(pokemon);
    }

    @Override
    protected String describe()
    {
        return "использует Steel Wing";
    }
}
