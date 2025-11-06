package sleepermod.cards.statuses;


import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import sleepermod.actions.ForgetAction;
import sleepermod.actions.Move;
import sleepermod.cards.AbstractSleeperCard;
import sleepermod.character.MySleeperPlayer;
import sleepermod.util.CardStats;


// Forget a random card. forget.
public class Dizzy extends AbstractSleeperCard {
    public static final String ID = makeID(Dizzy.class.getSimpleName());
    private static Object MyCharacter;
    private static final CardStats info = new CardStats(
            CardColor.COLORLESS,
            CardType.STATUS, 
            CardRarity.CURSE, 
            CardTarget.SELF, 
            0 
    );

    public static final int magicNumber = 1;


    public Dizzy() {
        super(ID, info); 
        this.urgent = true;
        this.exhaust = true;
        setMagic(magicNumber);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ForgetAction(1,true,false,false));
    }


//    @Override
//    public void triggerOnPlayedFromForgotten(AbstractPlayer p, AbstractMonster m, boolean randomTarget) {
//        addToBot(new ForgetAction(1,true,false,false));
//
//    }
}