package sleepermod.cards.powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.BerserkPower;
import sleepermod.cards.AbstractSleeperCard;
import sleepermod.character.MySleeperPlayer;
import sleepermod.archive.InsomniaPower;
import sleepermod.util.CardStats;

//Gain 4 woozy. At the start of your turn, gain 1 energy
public class AllNighter extends AbstractSleeperCard {
    public static final String ID = makeID(AllNighter.class.getSimpleName());
    private static Object MyCharacter;
    private static final CardStats info = new CardStats(
            MySleeperPlayer.Meta.CARD_COLOR, 
            CardType.POWER, 
            CardRarity.RARE, 
            CardTarget.SELF, 
            1 
    );

    int baseMagicNumber = 5;
    int magicUpgrade = -1;


    public AllNighter() {
        super(ID, info);
        setMagic(baseMagicNumber,magicUpgrade);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new InsomniaPower(p,this.magicNumber)));
        addToBot(new ApplyPowerAction(p,p,new BerserkPower(p,1)));
    }

    public AbstractCard makeCopy() {
        return new AllNighter();
    }
}