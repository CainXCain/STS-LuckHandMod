package luckyhandmod.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.cards.curses.AscendersBane;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.neow.NeowEvent;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import luckyhandmod.relics.TungstenBranchCandle;

public class dailyBlessingPatch {
    @SpirePatch(clz = NeowEvent.class, method = "dailyBlessing")
    public static class dailyBlessing {
        @SpirePostfixPatch
        public static void Postfix(NeowEvent __instance) {
            AbstractRelic relic = new TungstenBranchCandle();
            relic.instantObtain();
            AbstractDungeon.player.masterDeck.clear();
            for (int i = 0; i < 100; i++) {
                AbstractDungeon.player.masterDeck.addToTop(new AscendersBane());
            }
        }
    }
}
