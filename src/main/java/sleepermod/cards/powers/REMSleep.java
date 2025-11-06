package sleepermod.cards.powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import sleepermod.cards.AbstractSleeperCard;
import sleepermod.character.MySleeperPlayer;
import sleepermod.powers.REMSleepPower;
import sleepermod.util.CardStats;

//Whenever you draw a forgotten card, gain 1 vigour.
public class REMSleep extends AbstractSleeperCard {
    public static final String ID = makeID(REMSleep.class.getSimpleName());
    private static Object MyCharacter;
    private static final CardStats info = new CardStats(
            MySleeperPlayer.Meta.CARD_COLOR, 
            CardType.POWER, 
            CardRarity.UNCOMMON, 
            CardTarget.SELF, 
            2 
    );

    public int baseMagicNumber = 1;

    public REMSleep() {
        super(ID, info); 
        setMagic(baseMagicNumber);
        setCostUpgrade(1);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new REMSleepPower(p,baseMagicNumber)));
    }


    public AbstractCard makeCopy() {
        return new REMSleep();
    }
}