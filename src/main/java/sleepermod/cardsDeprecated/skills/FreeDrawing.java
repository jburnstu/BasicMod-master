package sleepermod.cardsDeprecated.skills;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import sleepermod.cards.AbstractSleeperCard;
import sleepermod.character.MySleeperPlayer;
import sleepermod.util.CardStats;

//Draw two cards. Forget.
public class FreeDrawing extends AbstractSleeperCard {
    public static final String ID = makeID(FreeDrawing.class.getSimpleName());
    private static Object MyCharacter;
    private static final CardStats info = new CardStats(
            MySleeperPlayer.Meta.CARD_COLOR, 
            CardType.SKILL, 
            CardRarity.COMMON, 
            CardTarget.SELF, 
            0 
    );

    private int baseMagicNumber = 2;
    private int magicUpgrade = 1;

    public FreeDrawing() {
        super(ID, info); 
        this.forget = true;
        setMagic(baseMagicNumber,magicUpgrade);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DrawCardAction(p,magicNumber));
    }
}