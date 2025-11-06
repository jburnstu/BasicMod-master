package sleepermod.cardsDeprecated.skills;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import sleepermod.actions.IgnoranceIsStrengthAction;
import sleepermod.cards.AbstractSleeperCard;
import sleepermod.character.MySleeperPlayer;
import sleepermod.util.CardStats;

//Gain 3 block and draw one card for every forgotten card in your hand.
public class IgnoranceIsStrength extends AbstractSleeperCard {
    public static final String ID = makeID(IgnoranceIsStrength.class.getSimpleName());
    private static Object MyCharacter;
    private static final CardStats info = new CardStats(
            MySleeperPlayer.Meta.CARD_COLOR, 
            CardType.SKILL, 
            CardRarity.UNCOMMON, 
            CardTarget.SELF, 
            1 
    );

    private static final int BLOCK = 4;
    private static final int UPG_BLOCK = 1;

    public IgnoranceIsStrength() {
        super(ID, info); 
        setBlock(BLOCK, UPG_BLOCK); 
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new IgnoranceIsStrengthAction(p,block));
    }
}