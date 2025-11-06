package sleepermod.cardsDeprecated.powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.LoseStrengthPower;
import sleepermod.cards.AbstractSleeperCard;
import sleepermod.character.MySleeperPlayer;
import sleepermod.powers.PsychicStormPower;
import sleepermod.util.CardStats;

//Lose 2 strength. When you remember an attack, its damage is dealt to ALL enemies.
public class PsychicStorm extends AbstractSleeperCard {
    public static final String ID = makeID(PsychicStorm.class.getSimpleName());
    private static Object MyCharacter;
    private static final CardStats info = new CardStats(
            MySleeperPlayer.Meta.CARD_COLOR, 
            CardType.SKILL, 
            CardRarity.UNCOMMON, 
            CardTarget.ENEMY, 
            1 
    );

    private int magicNumber = 2;

    public PsychicStorm() {
        super(ID, info); 
        this.forget = true;
        this.magicNumber = magicNumber;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p,p,new LoseStrengthPower(p,this.magicNumber)));
        addToBot(new ApplyPowerAction(p,p,new PsychicStormPower(p,this.magicNumber)));
    }
}
