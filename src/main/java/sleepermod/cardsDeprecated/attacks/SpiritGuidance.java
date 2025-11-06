package sleepermod.cardsDeprecated.attacks;


import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import sleepermod.cards.AbstractSleeperCard;
import sleepermod.character.MySleeperPlayer;
import sleepermod.util.CardStats;

// Deal X damage. If played from forgotten, gain 2 energy.
public class SpiritGuidance extends AbstractSleeperCard {
    public static final String ID = makeID(SpiritGuidance.class.getSimpleName());
    private static Object MyCharacter;
    private static final CardStats info = new CardStats(
            MySleeperPlayer.Meta.CARD_COLOR, 
            CardType.ATTACK, 
            CardRarity.COMMON, 
            CardTarget.ENEMY, 
            2 
    );

    private static final int DAMAGE = 10;
    private static final int UPG_DAMAGE = 4;
    public static final int magicNumber = 2;


    public SpiritGuidance() {
        super(ID, info);
        setDamage(DAMAGE, UPG_DAMAGE); 
        this.exhaust = true;
        setMagic(magicNumber);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_VERTICAL));
    }

    @Override
    public void triggerOnRemembered(AbstractPlayer p, AbstractMonster m, boolean randomTarget) {
        addToBot((AbstractGameAction)new GainEnergyAction(magicNumber));
      }


}