package sleepermod.cards.powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import sleepermod.cards.AbstractSleeperCard;
import sleepermod.character.MySleeperPlayer;
import sleepermod.powers.TorporPower;
import sleepermod.util.CardStats;

//When you remember a skill, apply 1 woozy to the targeted enemy.
public class Torpor extends AbstractSleeperCard {
    public static final String ID = makeID(Torpor.class.getSimpleName());
    private static Object MyCharacter;
    private static final CardStats info = new CardStats(
            MySleeperPlayer.Meta.CARD_COLOR,
            CardType.POWER,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            1
    );

    public int baseMagicNumber = 2;
    public int magicUpgrade = 1;

    public Torpor() {
        super(ID, info); 
        setMagic(baseMagicNumber, magicUpgrade);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new TorporPower(p,magicNumber)));
    }


    public AbstractCard makeCopy() {
        return new Torpor();
    }
}