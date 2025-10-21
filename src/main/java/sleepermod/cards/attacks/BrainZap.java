package sleepermod.cards.attacks;

import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import sleepermod.cards.AbstractSleeperCard;
import sleepermod.util.CardStats;

//Urgent. deal x damage and gain x block. add a copy of this card to your discard. Forget.
public class BrainZap extends AbstractSleeperCard {
    public static final String ID = makeID(BrainZap.class.getSimpleName());
    private static Object MyCharacter;
    private static final CardStats info = new CardStats(
            CardColor.COLORLESS, 
            CardType.ATTACK, 
            CardRarity.SPECIAL, 
            CardTarget.ENEMY, 
            0 
    );

    private static final int DMG = 6;
    private static final int UPG_DMG = 3;

    public BrainZap() {
        super(ID, info); 
        this.exhaust = true;
        setDamage(DMG, UPG_DMG);
//        setBlock(BLOCK, UPG_BLOCK); 
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL)));
    }

    @Override
    public void triggerOnRemembered (AbstractPlayer p, AbstractMonster m, boolean randomTarget) {
        addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL)));
    }

}