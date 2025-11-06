package sleepermod.cards.skills;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import sleepermod.actions.BreakTheCycleAction;
import sleepermod.actions.ForgetAction;
import sleepermod.cards.AbstractSleeperCard;
import sleepermod.character.MySleeperPlayer;
import sleepermod.util.CardStats;

//Forget an attack. gain its base damage as block.",
public class BreakTheCycle extends AbstractSleeperCard {
    public static final String ID = makeID(BreakTheCycle.class.getSimpleName());
    private static Object MyCharacter;
    private static final CardStats info = new CardStats(
            MySleeperPlayer.Meta.CARD_COLOR, 
            CardType.SKILL, 
            CardRarity.UNCOMMON, 
            CardTarget.SELF, 
            0 
    );

    private int baseMagicNumber = 1;
    public int magicUpgrade = 0;

    public BreakTheCycle() {
        super(ID, info); 
//        setExhaust(true,false);
        setMagic(baseMagicNumber, magicUpgrade);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ForgetAction(magicNumber,false,this.upgraded,false,true,new BreakTheCycleAction()));
    }
}