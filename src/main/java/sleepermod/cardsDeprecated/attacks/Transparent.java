package sleepermod.cardsDeprecated.attacks;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import sleepermod.cards.AbstractSleeperCard;
import sleepermod.cards.ForgottenCard;
import sleepermod.character.MySleeperPlayer;
import sleepermod.util.CardStats;

//Costs one less for each forgotten card in your hand. X block
public class Transparent extends AbstractSleeperCard {
    public static final String ID = makeID(Transparent.class.getSimpleName());
    private static Object MyCharacter;
    private static final CardStats info = new CardStats(
            MySleeperPlayer.Meta.CARD_COLOR, //The card color. If you're making your own character, it'll look something like this. Otherwise, it'll be CardColor.RED or similar for a basegame character color.
            CardType.SKILL, //The type. ATTACK/SKILL/POWER/CURSE/STATUS
            CardRarity.UNCOMMON, //Rarity. BASIC is for starting cards, then there's COMMON/UNCOMMON/RARE, and then SPECIAL and CURSE. SPECIAL is for cards you only get from events. Curse is for curses, except for special curses like Curse of the Bell and Necronomicurse.
            CardTarget.ENEMY, //The target. Single target is ENEMY, all enemies is ALL_ENEMY. Look at cards similar to what you want to see what to use.
            4 //The card's base cost. -1 is X cost, -2 is no cost for unplayable cards like curses, or Reflex.
    );
    //These will be used in the constructor. Technically you can just use the values directly,
    //but constants at the top of the file are easy to adjust.
    private static final int BLOCK = 10;
    private static final int UPG_BLOCK = 5;

    public Transparent() {
        super(ID, info); //Pass the required information to the BaseCard constructor.
        setBlock(BLOCK, UPG_BLOCK); //Sets the card's damage and how much it changes when upgraded.
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainBlockAction(p, block));
    }

    public void didDiscard() {
        setCostForTurn(this.costForTurn - 1);
    }

    @Override
    public void onPlayCard(AbstractCard c, AbstractMonster m) {
        if (c instanceof ForgottenCard) {
            setCostForTurn(this.cost - 1);
        }
    }

    @Override
    public void triggerOnOtherCardDrawn(AbstractCard c) {
        if (c instanceof ForgottenCard) {
            setCostForTurn(this.cost -1);
        }
    }


    public void triggerWhenDrawn() {
        super.triggerWhenDrawn();
        int forgottenCardsInHand = 0;
        for (AbstractCard c :AbstractDungeon.player.hand.group) {
            if (c instanceof ForgottenCard) {
                forgottenCardsInHand += 1;
            }
        }
        setCostForTurn(this.cost - forgottenCardsInHand);
    }

    public void atTurnStart() {
        resetAttributes();
        applyPowers();
    }



}