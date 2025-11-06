package sleepermod.cards.skills;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import sleepermod.cards.AbstractSleeperCard;
import sleepermod.powers.AmnesiaPower;
import sleepermod.character.MySleeperPlayer;
import sleepermod.util.CardStats;


public class CopingMechanism extends AbstractSleeperCard {
    public static final String ID = makeID(CopingMechanism.class.getSimpleName());
    private static Object MyCharacter;
    private static final CardStats info = new CardStats(
            MySleeperPlayer.Meta.CARD_COLOR, 
            CardType.SKILL, 
            CardRarity.UNCOMMON, 
            CardTarget.SELF, 
            1 
    );

    private int baseMagicNumber = 2;
    private int magicUpgrade = 1;

    public CopingMechanism() {
        super(ID, info); 
        setMagic(baseMagicNumber,magicUpgrade);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToTop(new ApplyPowerAction(p,p,new AmnesiaPower(p,1)));
        addToBot(new GainEnergyAction(magicNumber));
    }
}