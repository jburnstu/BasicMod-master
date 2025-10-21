package sleepermod.cards.attacks;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import sleepermod.cards.AbstractSleeperCard;
import sleepermod.character.MySleeperPlayer;
import sleepermod.archive.InsomniaPower;
import sleepermod.util.CardStats;

//  X damage to all enemies. If remembered, 1 woozy to all enemies (something else? vullnerable /weak?)
public class MassSuggestion extends AbstractSleeperCard {
    public static final String ID = makeID(MassSuggestion.class.getSimpleName());
    private static Object MyCharacter;
    private static final CardStats info = new CardStats(
            MySleeperPlayer.Meta.CARD_COLOR, 
            CardType.ATTACK, 
            CardRarity.UNCOMMON, 
            CardTarget.ALL_ENEMY, 
            2 
    );

    private static final int DAMAGE = 10;
    private static final int UPG_DAMAGE = 4;
    private static final int baseMagicNumber = 2;

    public MassSuggestion() {
        super(ID, info); 
        setDamage(DAMAGE, UPG_DAMAGE); 
        this.isMultiDamage = true;
        setMagic(baseMagicNumber);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAllEnemiesAction(p, damage, DamageInfo.DamageType.NORMAL, AbstractGameAction.AttackEffect.SLASH_VERTICAL));
//        for (AbstractCreature mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
//            addToBot(new ApplyPowerAction(mo, p, new InsomniaPower(m,magicNumber)));
//        }
    }

    @Override
    public void triggerOnRemembered(AbstractPlayer p, AbstractMonster m, boolean randomTarget) {
        for (AbstractCreature mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
            addToBot(new ApplyPowerAction(mo, p, new InsomniaPower(m,magicNumber)));
        }
    }

    @Override
    public void changeForgetForUpgrade() {
        this.forget = true;
    }
}

