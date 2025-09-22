package sleepermod.relics;

import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public abstract class AbstractSleeperRelic extends CustomRelic {
    public AbstractSleeperRelic(String id, AbstractRelic.RelicTier tier, AbstractRelic.LandingSound landingSound) {
            super(id, "", tier, landingSound);
            this.img = ImageMaster.loadImage("sleepermod/images/badge.png");
            this.largeImg = ImageMaster.loadImage("sleepermod/images/badge.png");
            this.outlineImg = ImageMaster.loadImage("sleepermod/images/badge.png");
        }

    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    public void onForget (AbstractCard card) {}

    public void onRecollectWithNoForgotten() {}

    public void onAwaken (AbstractCard card) {}
}