package sleepermod.cards.skills;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import sleepermod.cards.AbstractSleeperCard;
import sleepermod.character.MySleeperPlayer;
import sleepermod.powers.HazePower;
import sleepermod.util.CardStats;

//X block. when you're attacked, apply 1 woozy
public class Haze extends AbstractSleeperCard {
    public static final String ID = makeID(Haze.class.getSimpleName());
    private static Object MyCharacter;
    private static final CardStats info = new CardStats(
            MySleeperPlayer.Meta.CARD_COLOR, 
            CardType.SKILL, 
            CardRarity.UNCOMMON, 
            CardTarget.SELF, 
            2 
    );

    private static final int BLOCK = 12;
    private static final int UPG_BLOCK = 0;
    public static final int baseMagicNumber = 2;
    public static final int magicUpgrade = 1;

    public Haze() {
        super(ID, info); 
        setBlock(BLOCK, UPG_BLOCK); 
        setMagic(baseMagicNumber,magicUpgrade);
//        this.exhaust = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainBlockAction(p, block));
        addToBot(new ApplyPowerAction(p,p,new HazePower(p,magicNumber)));
    }
}