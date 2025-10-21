package sleepermod.cardsBeta.skills;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveAllBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import sleepermod.cards.AbstractSleeperCard;
import sleepermod.character.MySleeperPlayer;
import sleepermod.util.CardStats;


public class Perfectionist extends AbstractSleeperCard {
    public static final String ID = makeID(Perfectionist.class.getSimpleName());
    private static Object MyCharacter;
    private static final CardStats info = new CardStats(
            MySleeperPlayer.Meta.CARD_COLOR, 
            CardType.SKILL, 
            CardRarity.RARE, 
            CardTarget.SELF, 
            1 
    );

    private int baseMagicNumber = 1;
    private int magicUpgrade = 0;

    public Perfectionist() {
        super(ID, info);
        this.urgent = true;
        this.forget = true;
        setMagic(baseMagicNumber,magicUpgrade);
        setCostUpgrade(0);
        setExhaust(true,false);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToTop(new RemoveAllBlockAction(p,p));
        addToBot(new ApplyPowerAction(p,p,new DexterityPower(p,1)));
    }
}