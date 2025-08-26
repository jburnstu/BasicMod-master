package joeymod.cardBeta.attacks;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import joeymod.actions.ReckoningAction;
import joeymod.cards.AbstractSleeperCard;
import joeymod.cards.ForgottenCard;
import joeymod.character.MySleeperPlayer;
import joeymod.powers.WoozyPower;
import joeymod.util.CardStats;

//X damage for each forgotten card in your hand
public class Reckoning extends AbstractSleeperCard {
    public static final String ID = makeID(Reckoning.class.getSimpleName());
    private static Object MyCharacter;
    private static final CardStats info = new CardStats(
            MySleeperPlayer.Meta.CARD_COLOR, //The card color. If you're making your own character, it'll look something like this. Otherwise, it'll be CardColor.RED or similar for a basegame character color.
            CardType.ATTACK, //The type. ATTACK/SKILL/POWER/CURSE/STATUS
            CardRarity.UNCOMMON, //Rarity. BASIC is for starting cards, then there's COMMON/UNCOMMON/RARE, and then SPECIAL and CURSE. SPECIAL is for cards you only get from events. Curse is for curses, except for special curses like Curse of the Bell and Necronomicurse.
            CardTarget.ENEMY, //The target. Single target is ENEMY, all enemies is ALL_ENEMY. Look at cards similar to what you want to see what to use.
            2 //The card's base cost. -1 is X cost, -2 is no cost for unplayable cards like curses, or Reflex.
    );
    //These will be used in the constructor. Technically you can just use the values directly,
    //but constants at the top of the file are easy to adjust.
    private static final int DMG = 10;
    private static final int UPG_DMG = 4;
    private static int baseMagicNumber = 2;
    private static int magicUpgrade = 1;


    public Reckoning() {
        super(ID, info); //Pass the required information to the BaseCard constructor.
        setDamage(DMG, UPG_DMG); //Sets the card's damage and how much it changes when upgraded.
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ReckoningAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn)));
        this.rawDescription = cardStrings.DESCRIPTION;
        initializeDescription();
    }

    public void applyPowers() {
        super.applyPowers();
        int count = 0;
        for (AbstractCard c : AbstractDungeon.player.hand.group) {
            if (c instanceof ForgottenCard)
                count++;
        }
        this.rawDescription = cardStrings.DESCRIPTION;
        this.rawDescription += cardStrings.EXTENDED_DESCRIPTION[0] + count;
        if (count == 1) {
            this.rawDescription += cardStrings.EXTENDED_DESCRIPTION[1];
        } else {
            this.rawDescription += cardStrings.EXTENDED_DESCRIPTION[2];
        }
        initializeDescription();
    }

    public void onMoveToDiscard() {
        this.rawDescription = cardStrings.DESCRIPTION;
        initializeDescription();
    }
}
