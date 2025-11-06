package sleepermod.cards.powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import sleepermod.cards.AbstractSleeperCard;
import sleepermod.character.MySleeperPlayer;
import sleepermod.powers.SelfDiscoveryPower;
import sleepermod.util.CardStats;

//Whenever you awaken a card, upgrade it.
public class SelfDiscovery extends AbstractSleeperCard {
    public static final String ID = makeID(SelfDiscovery.class.getSimpleName());
    private static Object MyCharacter;
    private static final CardStats info = new CardStats(
            MySleeperPlayer.Meta.CARD_COLOR, 
            CardType.POWER, 
            CardRarity.RARE, 
            CardTarget.SELF, 
            2 
    );

    public int baseMagicNumber = 1;

    public SelfDiscovery() {
        super(ID, info); 
        setMagic(baseMagicNumber);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new SelfDiscoveryPower(p,magicNumber)));
    }


    public AbstractCard makeCopy() {
        return new SelfDiscovery();
    }
}