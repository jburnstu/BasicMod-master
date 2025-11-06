package sleepermod.cards.skills;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import sleepermod.cards.AbstractSleeperCard;
import sleepermod.character.MySleeperPlayer;
import sleepermod.powers.FreeCardPower;
import sleepermod.util.CardStats;

//Urgent. X block. the next card costs 0.
public class MemorySkip extends AbstractSleeperCard {
    public static final String ID = makeID(MemorySkip.class.getSimpleName());
    private static Object MyCharacter;
    private static final CardStats info = new CardStats(
            MySleeperPlayer.Meta.CARD_COLOR, 
            CardType.SKILL, 
            CardRarity.UNCOMMON, 
            CardTarget.SELF, 
            2 
    );

    private int baseMagicNumber = 1;
    private static final int BLOCK = 11;
    private static final int UPG_BLOCK = 4;

    public MemorySkip() {
        super(ID, info);
        this.urgent = true;
        setBlock(BLOCK,UPG_BLOCK);
        setMagic(baseMagicNumber);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainBlockAction(p,block));
        addToBot(new ApplyPowerAction(p,p, new FreeCardPower(p,magicNumber),magicNumber));
    }
}