package sleepermod.cards.skills;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import sleepermod.actions.FlashBeforeMyEyesAction;
import sleepermod.cards.AbstractSleeperCard;
import sleepermod.character.MySleeperPlayer;
import sleepermod.util.CardStats;

//Urgent, draw 5 cards, exhaust
public class FlashBeforeMyEyes extends AbstractSleeperCard {
    public static final String ID = makeID(FlashBeforeMyEyes.class.getSimpleName());
    private static Object MyCharacter;
    private static final CardStats info = new CardStats(
            MySleeperPlayer.Meta.CARD_COLOR, 
            CardType.SKILL, 
            CardRarity.UNCOMMON, 
            CardTarget.SELF, 
            1 
    );

    private int baseMagicNumber = 3;
    private int magicUpgrade = 1;

    public FlashBeforeMyEyes() {
        super(ID, info); 
        this.urgent = true;
//        this.exhaust = true;
        setMagic(baseMagicNumber,magicUpgrade);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToTop(new DrawCardAction(magicNumber,new FlashBeforeMyEyesAction()));
    }
}
