package labtwo.moves;

import ru.ifmo.se.pokemon.*;

public class PoisonStingMove extends PhysicalMove{
    public PoisonStingMove(){super(Type.POISON,15,100);}


    @Override
    protected void applyOppEffects(Pokemon pokemon){
        if ((new Effect()).chance(0.3D).success())
            Effect.poison(pokemon);

        super.applyOppEffects(pokemon);

    }
    @Override
    protected String describe()
    {
        return "использует Poison Sting";
    }
}
