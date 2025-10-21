package sleepermod.cards.skills;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import sleepermod.actions.AwakenAction;
import sleepermod.actions.VisionAction;
import sleepermod.cards.AbstractSleeperCard;
import sleepermod.character.MySleeperPlayer;
import sleepermod.util.CardStats;

//urgent. Recollect 1. exhaust.
public class Vision extends AbstractSleeperCard {
    public static final String ID = makeID(Vision.class.getSimpleName());
    private static Object MyCharacter;
    private static final CardStats info = new CardStats(
            MySleeperPlayer.Meta.CARD_COLOR, 
            CardType.SKILL, 
            CardRarity.UNCOMMON, 
            CardTarget.SELF, 
            0 
    );

    private int baseMagicNumber = 1;

    public Vision() {
        super(ID, info); 
        this.urgent = true;
        setMagic(baseMagicNumber);
//        setExhaust(true,false);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new AwakenAction(magicNumber, new VisionAction(this.upgraded)));
    }
}