package sleepermod.cardsDeprecated.skills;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import sleepermod.cards.AbstractSleeperCard;
import sleepermod.character.MySleeperPlayer;
import sleepermod.powers.TrancePower;
import sleepermod.util.CardStats;

//urgent. Gain 1 vision. draw 1 card. exhaust.
public class FlashForward extends AbstractSleeperCard {
    public static final String ID = makeID(FlashForward.class.getSimpleName());
    private static Object MyCharacter;
    private static final CardStats info = new CardStats(
            MySleeperPlayer.Meta.CARD_COLOR, 
            CardType.SKILL, 
            CardRarity.UNCOMMON, 
            CardTarget.SELF, 
            0 
    );

    private int baseMagicNumber = 1;
    private int magicUpgrade = 0;

    public FlashForward() {
        super(ID, info); 
        this.urgent = true;
        setMagic(baseMagicNumber,magicUpgrade);
        setExhaust(true,false);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p,p,new TrancePower(p,1)));
        addToBot(new DrawCardAction(magicNumber));
    }
}