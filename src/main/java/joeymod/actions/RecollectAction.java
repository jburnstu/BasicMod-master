package joeymod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;

public class RecollectAction extends AbstractGameAction {
    public RecollectAction(AbstractPlayer p, int magicNumber) {
        // for card in ((MySleeperPlayer) AbstractDungeon.player).forgottenPile.group:
        // create temporary view
        // pick one / more
        // move them from there to your hand
        // search discard, draw pile, and hand for the forgotten card corresponding to it, and purge it.
        // or rather, maybe do this loop first, and once the corresponding card is found, do the moving?
    }
}
