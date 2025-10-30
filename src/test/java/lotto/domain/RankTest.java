package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class RankTest {
    @ParameterizedTest
    @CsvSource({
            "6, false, FIRST",
            "6, true,  FIRST",
            "5, true,  SECOND",
            "5, false, THIRD",
            "4, false, FOURTH",
            "4, true,  FOURTH",
            "3, false, FIFTH",
            "3, true,  FIFTH",
            "2, false, NOTHING",
            "2, true,  NOTHING",
            "1, false, NOTHING",
            "0, false, NOTHING"
    })
    void 일치하는_번호_개수와_보너스_번호_일치_여부에_따라_1등부터_5등까지의_등수_중_하나로_결정한다(int matchCount, boolean hasBonus, Rank expectedRank) {
        assertThat(Rank.calculateRank(matchCount, hasBonus)).isEqualTo(expectedRank);
    }
}
