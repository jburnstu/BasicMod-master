package sleepermod.cards.skills;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import sleepermod.actions.ForgetAction;
import sleepermod.cards.AbstractSleeperCard;
import sleepermod.character.MySleeperPlayer;
import sleepermod.util.CardStats;

//Urgent, gain x block, forget a card.
public class SleepingPill extends AbstractSleeperCard {
    public static final String ID = makeID(SleepingPill.class.getSimpleName());
    private static Object MyCharacter;
    private static final CardStats info = new CardStats(
            MySleeperPlayer.Meta.CARD_COLOR, 
            CardType.SKILL, 
            CardRarity.COMMON, 
            CardTarget.SELF, 
            0 
    );

    private int baseMagicNumber = 1;
    private static final int BLOCK = 6;
    private static final int UPG_BLOCK = 3;

    public SleepingPill() {
        super(ID, info); 
        this.urgent = true;
        setBlock(BLOCK, UPG_BLOCK);
        setMagic(baseMagicNumber);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainBlockAction(p,this.block));
        addToTop(new ForgetAction(magicNumber,false,false,false));
        }
}
