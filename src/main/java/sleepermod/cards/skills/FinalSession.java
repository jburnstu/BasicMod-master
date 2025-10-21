package sleepermod.cards.skills;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import sleepermod.actions.AwakenAction;
import sleepermod.cards.AbstractSleeperCard;
import sleepermod.character.MySleeperPlayer;
import sleepermod.util.CardStats;

//Recollect 3 cards. exhaust.
public class FinalSession extends AbstractSleeperCard {
    public static final String ID = makeID(FinalSession.class.getSimpleName());
    private static Object MyCharacter;
    private static final CardStats info = new CardStats(
            MySleeperPlayer.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            2
    );

    private int baseMagicNumber = 2;
    public boolean baseExhaust = true;
    public boolean upgradeExhaust = true;


    public FinalSession() {
        super(ID, info); 
        setExhaust(baseExhaust,upgradeExhaust);
        setMagic(baseMagicNumber);
        setCostUpgrade(1);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {

        addToBot(new AwakenAction(baseMagicNumber,false));
    }
}