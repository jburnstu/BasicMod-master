package sleepermod.cardsDeprecated.skills;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import sleepermod.actions.HardResetAction;
import sleepermod.cards.AbstractSleeperCard;
import sleepermod.character.MySleeperPlayer;
import sleepermod.util.CardStats;

//Urgent. Lose all your block. Gain 2 energy. draw three cards.
public class HardReset extends AbstractSleeperCard {
    public static final String ID = makeID(HardReset.class.getSimpleName());
    private static Object MyCharacter;
    private static final CardStats info = new CardStats(
            MySleeperPlayer.Meta.CARD_COLOR, 
            CardType.SKILL, 
            CardRarity.RARE, 
            CardTarget.SELF, 
            -1 
    );

    private int magicNumber = 2;

    public HardReset() {
        super(ID, info); 
        this.magicNumber = magicNumber;
        this.urgent = true;
        setMagic(magicNumber);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new HardResetAction(p, this.upgraded, this.freeToPlayOnce, this.energyOnUse));
//        addToBot(new RemoveAllBlockAction(p,p)); //how to get this?
//        addToBot(new GainEnergyAction(2));
//        addToBot(new DrawCardAction(3));
    }
}