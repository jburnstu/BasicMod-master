package sleepermod.cards.powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import sleepermod.cards.AbstractSleeperCard;
import sleepermod.character.MySleeperPlayer;
import sleepermod.powers.LucidDreamPower;
import sleepermod.util.CardStats;

//At the start of your turn, recollect 1 and gain 1 amnesia.
public class LucidDream extends AbstractSleeperCard {
    public static final String ID = makeID(LucidDream.class.getSimpleName());
    private static Object MyCharacter;
    private static final CardStats info = new CardStats(
            MySleeperPlayer.Meta.CARD_COLOR, 
            CardType.POWER, 
            CardRarity.RARE, 
            CardTarget.SELF, 
            2 
    );

    public int baseMagicNumber = 1;


    public LucidDream() {
        super(ID, info); 
        setMagic(baseMagicNumber);
        setCostUpgrade(1);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new LucidDreamPower(p,magicNumber)));
    }


    public AbstractCard makeCopy() {
        return new LucidDream();
    }
}