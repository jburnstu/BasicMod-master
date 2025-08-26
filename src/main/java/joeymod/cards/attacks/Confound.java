package joeymod.cards.attacks;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import joeymod.cards.AbstractSleeperCard;
import joeymod.character.MySleeperPlayer;
import joeymod.powers.WoozyPower;
import joeymod.util.CardStats;

//X damage. Apply 2 woozy.
public class Confound extends AbstractSleeperCard {
    public static final String ID = makeID(Confound.class.getSimpleName());
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
    private static final int DMG = 13;
    private static final int UPG_DMG = 0;
    private static int baseMagicNumber = 2;
    private static int magicUpgrade = 1;


    public Confound() {
        super(ID, info); //Pass the required information to the BaseCard constructor.
        setDamage(DMG, UPG_DMG); //Sets the card's damage and how much it changes when upgraded.
        setMagic(baseMagicNumber,magicUpgrade);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL)));
        addToBot(new ApplyPowerAction(p,p,new WoozyPower(p,magicNumber)));
        };
    }
