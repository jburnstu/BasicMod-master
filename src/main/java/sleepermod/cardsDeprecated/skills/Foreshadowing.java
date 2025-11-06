package sleepermod.cardsDeprecated.skills;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import sleepermod.actions.AwakenAction;
import sleepermod.cards.AbstractSleeperCard;
import sleepermod.character.MySleeperPlayer;
import sleepermod.powers.ForeshadowingPower;
import sleepermod.util.CardStats;

//Whenever you awaken a skill this turn, apply 1 weak to ALL enemies. Awaken 1 card.
public class Foreshadowing extends AbstractSleeperCard {
    public static final String ID = makeID(Foreshadowing.class.getSimpleName());
    private static Object MyCharacter;
    private static final CardStats info = new CardStats(
            MySleeperPlayer.Meta.CARD_COLOR, 
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            1
    );

    private int baseMagicNumber = 1;
    private int magicUpgrade = 1;

    public Foreshadowing() {
        super(ID, info); 
        setMagic(baseMagicNumber,magicUpgrade);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p,p,new ForeshadowingPower(p,magicNumber)));
        addToBot(new AwakenAction(1,false));
    }
}
