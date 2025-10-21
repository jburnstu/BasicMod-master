package sleepermod.cardsBeta.skills;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import sleepermod.cards.AbstractSleeperCard;
import sleepermod.character.MySleeperPlayer;
import sleepermod.powers.ReduceDamageByInsomniaPower;
import sleepermod.util.CardStats;

//X block
public class WardOff extends AbstractSleeperCard {
    public static final String ID = makeID(WardOff.class.getSimpleName());
    private static Object MyCharacter;
    private static final CardStats info = new CardStats(
            MySleeperPlayer.Meta.CARD_COLOR, 
            CardType.SKILL, 
            CardRarity.UNCOMMON, 
            CardTarget.SELF, 
            2 
    );

    private static final int BLOCK = 10;
    private static final int UPG_BLOCK = 5;

    public WardOff() {
        super(ID, info); 
        setBlock(BLOCK, UPG_BLOCK); 
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainBlockAction(p, block));
        addToBot(new ApplyPowerAction(p,p,new ReduceDamageByInsomniaPower(p,magicNumber)));
    }
}