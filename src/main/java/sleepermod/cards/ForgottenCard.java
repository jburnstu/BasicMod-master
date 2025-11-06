package sleepermod.cards;

import com.megacrit.cardcrawl.actions.utility.NewQueueCardAction;
import com.megacrit.cardcrawl.actions.utility.ShowCardAndPoofAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import sleepermod.actions.RememberAction;
import sleepermod.character.MySleeperPlayer;
import sleepermod.patches.coremechanics.FieldPatchAbstractCardBackForgottenCard;
import sleepermod.powers.EnterTheVoidPower;
import sleepermod.powers.TrancePower;
import sleepermod.util.CardStats;

public class ForgottenCard extends AbstractSleeperCard {
    public static final String ID = makeID(ForgottenCard.class.getSimpleName());
    private static Object MyCharacter;
    private static final CardStats info = new CardStats(
            CardColor.COLORLESS,
            CardType.STATUS, 
            CardRarity.SPECIAL, 
            CardTarget.ENEMY, 
            1 
    );

    public AbstractCard frontForgottenCard;
    MySleeperPlayer p;

    public ForgottenCard(AbstractCard c) {
        super(ID, info); 
        this.frontForgottenCard = c;
        FieldPatchAbstractCardBackForgottenCard.backForgottenCard.set(c,this);
        this.p = (MySleeperPlayer) AbstractDungeon.player;
//        this.purgeOnUse = true;
//        this.dontTriggerOnUseCard = true;

    }

    public ForgottenCard() {
        super(ID, info);
    }

    @Override
    public void applyPowers() {
        super.applyPowers();
        
        /*These lines are here rather than the constructor because of EnterTheVoid Power, which sets
        both attributes to false in RememberAction so that the ForgottenCard itself is treated like
        an actionless card.
        */
        this.purgeOnUse = true;
        this.dontTriggerOnUseCard = true;
        
        if (AbstractDungeon.player.hasPower(TrancePower.POWER_ID)) {
            this.rawDescription = cardStrings.EXTENDED_DESCRIPTION[0] + cardStrings.DESCRIPTION;
            this.urgent = true;
            this.setCostForTurn(0);
        } else {
            this.rawDescription = cardStrings.DESCRIPTION;
            this.urgent = false;
            this.setCostForTurn(this.cost);
        }
        initializeDescription();
    }


    public void onMoveToDiscard() {
        this.rawDescription = cardStrings.DESCRIPTION;
        initializeDescription();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToTop(new RememberAction(m,(MySleeperPlayer) p,this));
    }

//    @Override
//    public void triggerAtStartOfTurn() {
//        this.purgeOnUse = true;
//        this.dontTriggerOnUseCard = true;
//    }

    public void upgrade () {}
}