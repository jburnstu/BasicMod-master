package sleepermod.cards.skills;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import sleepermod.actions.IncreaseCostAction;
import sleepermod.cards.AbstractSleeperCard;
import sleepermod.character.MySleeperPlayer;
import sleepermod.util.CardStats;

//Gain X block. Increase the cost of this card by 1 this combat
public class HideUnderTheCovers extends AbstractSleeperCard {
    public static final String ID = makeID(HideUnderTheCovers.class.getSimpleName());
    private static Object MyCharacter;
    private static final CardStats info = new CardStats(
            MySleeperPlayer.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.COMMON,
            CardTarget.SELF,
            0
    );

    private static final int BLOCK = 9;
    private static final int UPG_BLOCK = 3;
    private static final int baseMagicNumber = 1;

    public HideUnderTheCovers() {
        super(ID, info); 
        setBlock(BLOCK, UPG_BLOCK);
        setMagic(baseMagicNumber);
        this.forget = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainBlockAction(p, block));
        addToBot(new IncreaseCostAction(this.uuid, magicNumber));
    }
}