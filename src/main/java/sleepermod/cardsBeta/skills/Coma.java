package sleepermod.cardsBeta.skills;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import sleepermod.cards.AbstractSleeperCard;
import sleepermod.character.MySleeperPlayer;
import sleepermod.powers.ComaPower;
import sleepermod.powers.TrancePower;
import sleepermod.util.CardStats;

//Forgotten cards cost zero this turn. This turn, when you draw a draw a non-forgotten card, discard it.
public class Coma extends AbstractSleeperCard {
    public static final String ID = makeID(Coma.class.getSimpleName());
    private static Object MyCharacter;
    private static final CardStats info = new CardStats(
            MySleeperPlayer.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.RARE,
            CardTarget.SELF,
            3
    );

    public boolean baseExhaust = true;
    public boolean upgradeExhaust = false;

    public Coma() {
        super(ID, info); 
        setExhaust(baseExhaust,upgradeExhaust);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p,p,new TrancePower(p,99)));
        addToBot(new ApplyPowerAction(p, p, new ComaPower(p, 1)));

    }
}