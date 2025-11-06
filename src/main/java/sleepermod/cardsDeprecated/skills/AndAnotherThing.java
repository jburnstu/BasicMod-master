package sleepermod.cardsDeprecated.skills;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import sleepermod.actions.AndAnotherThingAction;
import sleepermod.actions.AwakenAction;
import sleepermod.cards.AbstractSleeperCard;
import sleepermod.character.MySleeperPlayer;
import sleepermod.util.CardStats;

//Recollect 1. If the chosen card costs 2+, recollect again.
public class AndAnotherThing extends AbstractSleeperCard {
    public static final String ID = makeID(AndAnotherThing.class.getSimpleName());
    private static Object MyCharacter;
    private static final CardStats info = new CardStats(
            MySleeperPlayer.Meta.CARD_COLOR, 
            CardType.SKILL, 
            CardRarity.UNCOMMON, 
            CardTarget.SELF, 
            2 
    );

    private int magicNumber = 2;
    public boolean baseExhaust = true;
    public boolean upgradeExhaust = false;


    public AndAnotherThing() {
        super(ID, info); 
        setMagic(magicNumber);
        setExhaust(baseExhaust,upgradeExhaust);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new AwakenAction(1,new AndAnotherThingAction(magicNumber)));
    }
}