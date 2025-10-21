package sleepermod.cardsDeprecated.skills;

import com.megacrit.cardcrawl.actions.common.ReduceCostAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import sleepermod.cards.AbstractSleeperCard;
import sleepermod.character.MySleeperPlayer;
import sleepermod.util.CardStats;

//Reduce the cost of a Forgotten Card by 1 this combat.
public class InnerChild extends AbstractSleeperCard {
    public static final String ID = makeID(InnerChild.class.getSimpleName());
    private static Object MyCharacter;
    private static final CardStats info = new CardStats(
            MySleeperPlayer.Meta.CARD_COLOR, 
            CardType.SKILL, 
            CardRarity.UNCOMMON, 
            CardTarget.SELF, 
            1
    );

    private int baseMagicNumber = 1;

    public InnerChild() {
        super(ID, info); 
        this.forget = true;
        setMagic(baseMagicNumber);
        setCostUpgrade(0);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractCard longestForgottenCard = ((MySleeperPlayer) p).forgottenPile.group.get(0);
        addToBot(new ReduceCostAction(longestForgottenCard.uuid,magicNumber));
    }
}