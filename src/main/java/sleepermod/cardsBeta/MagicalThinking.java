package sleepermod.cardsBeta;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import sleepermod.actions.MagicalThinkingAction;
import sleepermod.cards.AbstractSleeperCard;
import sleepermod.character.MySleeperPlayer;
import sleepermod.util.CardStats;

//Swap the costs of this card and another card in your hand until the end of combat.
public class MagicalThinking extends AbstractSleeperCard {
    public static final String ID = makeID(MagicalThinking.class.getSimpleName());
    private static Object MyCharacter;
    private static final CardStats info = new CardStats(
            MySleeperPlayer.Meta.CARD_COLOR, 
            CardType.SKILL, 
            CardRarity.RARE, 
            CardTarget.SELF, 
            0 
    );


    public MagicalThinking() {
        super(ID, info);
        this.urgent = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToTop(new MagicalThinkingAction(this));
    }

    @Override
    public void changeForgetForUpgrade() {
        this.forget = true;
    }
}