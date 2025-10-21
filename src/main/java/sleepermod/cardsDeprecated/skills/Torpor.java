package sleepermod.cardsDeprecated.skills;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;
import sleepermod.cards.AbstractSleeperCard;
import sleepermod.character.MySleeperPlayer;
import sleepermod.powers.AmnesiaPower;
import sleepermod.util.CardStats;

//Urgent. apply 2 weak to all enemies. gain 2 amnesia. exhaust.
public class Torpor extends AbstractSleeperCard {
    public static final String ID = makeID(Torpor.class.getSimpleName());
    private static Object MyCharacter;
    private static final CardStats info = new CardStats(
            MySleeperPlayer.Meta.CARD_COLOR, 
            CardType.SKILL, 
            CardRarity.UNCOMMON,
            CardTarget.SELF_AND_ENEMY,
            0
    );

    private int baseMagicNumber = 2;
    private int magicUpgrade = 1;

    public Torpor() {
        super(ID, info); 
        this.exhaust = true;
        this.urgent = true;
        setMagic(baseMagicNumber,magicUpgrade);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(m,p, new WeakPower(m,magicNumber,false)));
        addToBot(new ApplyPowerAction(p,p, new AmnesiaPower(p,magicNumber)));
    }
}