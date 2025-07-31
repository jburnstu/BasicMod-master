package joeymod.powers;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.powers.AbstractPower;
import joeymod.cards.ForgottenCard;

public class DaydreamPower extends AbstractPower {

    AbstractPlayer p;

    public DaydreamPower(AbstractPlayer p) {
        this.p = p;
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        for (AbstractCard c:  p.hand.group) {
            if (c instanceof ForgottenCard) {
                c.retain = true;
                break;
            }
        }
    }
}
