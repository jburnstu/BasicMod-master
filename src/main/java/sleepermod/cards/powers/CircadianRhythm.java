package sleepermod.cards.powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import sleepermod.cards.AbstractSleeperCard;
import sleepermod.character.MySleeperPlayer;
import sleepermod.powers.CircadianRhythmPower;
import sleepermod.util.CardStats;

//Draw one card at the start of each turn. Forget 1 card at the end of each turn.
public class CircadianRhythm extends AbstractSleeperCard {
    public static final String ID = makeID(CircadianRhythm.class.getSimpleName());
    private static Object MyCharacter;
    private static final CardStats info = new CardStats(
            MySleeperPlayer.Meta.CARD_COLOR, 
            CardType.POWER, 
            CardRarity.RARE, 
            CardTarget.SELF, 
            1 
    );


    public int baseMagicNumber = 1;
    int magicUpgrade = 0;
    public boolean innate = false;
    public boolean innateUpgraded = true;

    public CircadianRhythm() {
        super(ID, info);
        setMagic(baseMagicNumber, magicUpgrade);
        setInnate(innate,innateUpgraded);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p,p,new CircadianRhythmPower(p,magicNumber)));
    }

    public AbstractCard makeCopy() {
        return new CircadianRhythm();
    }
}