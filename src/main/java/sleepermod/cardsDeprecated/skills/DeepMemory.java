package sleepermod.cardsDeprecated.skills;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import sleepermod.actions.DeepMemoryAction;
import sleepermod.cards.AbstractSleeperCard;
import sleepermod.character.MySleeperPlayer;
import sleepermod.util.CardStats;

//Forgotten cards cost zero this turn. This turn, when you draw a draw a non-forgotten card, discard it.
public class DeepMemory extends AbstractSleeperCard {
    public static final String ID = makeID(DeepMemory.class.getSimpleName());
    private static Object MyCharacter;
    private static final CardStats info = new CardStats(
            MySleeperPlayer.Meta.CARD_COLOR, 
            CardType.SKILL, 
            CardRarity.RARE, 
            CardTarget.SELF, 
            1 
    );

    public boolean baseExhaust = true;
    public boolean upgradeExhaust = false;

    public DeepMemory() {
        super(ID, info); 
        setExhaust(baseExhaust,upgradeExhaust);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DeepMemoryAction());
    }


}