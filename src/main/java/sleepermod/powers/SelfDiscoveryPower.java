package sleepermod.powers;

import com.megacrit.cardcrawl.actions.common.ReduceCostForTurnAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;

import static sleepermod.SleeperMod.makeID;


// Whenever you forget a card, draw a card.
public class SelfDiscoveryPower extends AbstractSleeperPower {
    public static final String POWER_ID = makeID(SelfDiscoveryPower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;

    public boolean usedThisTurn = false;

    public SelfDiscoveryPower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
    }

    @Override
    public void atStartOfTurn() {
        usedThisTurn = false;
    }

    @Override
    public void onAwaken(AbstractCard card) {
        if (!usedThisTurn) {
            addToTop(new ReduceCostForTurnAction(card, amount));
            usedThisTurn = true;
        }
    }
}