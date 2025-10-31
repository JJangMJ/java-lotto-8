package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import lotto.dto.LottoPurchaseResult;
import lotto.exception.ErrorMessage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class WinningNumbersTest {

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "  \t"})
    void 당첨_번호가_공백이면_예외_처리한다(String input) {
        assertThatThrownBy(() -> new WinningNumbers(Parser.parseWinningNumbers(input)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.CANNOT_BE_BLANK.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"1,2,3,4,5", "1,2,3,4,5,6,7"})
    void 당첨_번호의_숫자_개수가_6개가_아니면_예외_처리한다(String input) {
        assertThatThrownBy(() -> new WinningNumbers(Parser.parseWinningNumbers(input)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.SHOULD_BE_SIX_NUMBER.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"0,1,2,3,4,5", "1,2,3,4,5,46"})
    void 당첨_번호의_각_숫자는_1부터_45_사이여야_한다(String input) {
        assertThatThrownBy(() -> new WinningNumbers(Parser.parseWinningNumbers(input)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.SHOULD_BE_IN_RANGE.getMessage());
    }

    @Test
    void 당첨_번호의_숫자_중_중복_숫자가_있으면_예외_처리한다() {
        assertThatThrownBy(() -> new WinningNumbers(List.of(1, 2, 3, 3, 4, 5)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.CANNOT_BE_DUPLICATED.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"1,2,3,4,a,6", "1,2,3,4,5,@"})
    void 숫자가_아닌_값이_있으면_예외(String input) {
        assertThatThrownBy(() -> new WinningNumbers(Parser.parseWinningNumbers(input)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.SHOULD_BE_NUMBER.getMessage());
    }

    @Test
    void 발행된_각_로또가_당첨_번호와_비교해서_몇_개의_번호가_일치하는지_계산한다() {
        //given
        List<Lotto> purchasedLottos = new ArrayList<>();
        purchasedLottos.add(new Lotto(List.of(1, 2, 3, 4, 5, 6)));
        purchasedLottos.add(new Lotto(List.of(1, 2, 3, 4, 5, 16)));
        purchasedLottos.add(new Lotto(List.of(1, 2, 3, 4, 15, 16)));
        purchasedLottos.add(new Lotto(List.of(1, 2, 3, 14, 15, 16)));
        purchasedLottos.add(new Lotto(List.of(1, 2, 13, 14, 15, 16)));
        purchasedLottos.add(new Lotto(List.of(1, 12, 13, 14, 15, 16)));
        purchasedLottos.add(new Lotto(List.of(11, 12, 13, 14, 15, 16)));


        //when
        WinningNumbers winningNumbers = new WinningNumbers(List.of(1, 2, 3, 4, 5, 6));
        List<Integer> matchCounts = purchasedLottos.stream()
                .map(purchasedLotto -> (int) purchasedLotto.getNumbers().stream()
                        .filter(winningNumbers::contains)
                        .count())
                .toList();

        //then
        assertThat(matchCounts).containsExactly(6, 5, 4, 3, 2, 1, 0);
    }

    @Test
    void 등수별_개수를_정확히_누적한다() {
        // given
        WinningNumbers winningNumbers = new WinningNumbers(List.of(1, 2, 3, 4, 5, 6));
        BonusNumber bonusNumber = new BonusNumber(7, winningNumbers);

        List<Lotto> purchasedLottos = List.of(
                new Lotto(List.of(1, 2, 3, 4, 5, 6)),
                new Lotto(List.of(1, 2, 3, 4, 5, 7)),
                new Lotto(List.of(1, 2, 3, 4, 5, 8)),
                new Lotto(List.of(1, 2, 3, 4, 8, 9)),
                new Lotto(List.of(1, 2, 3, 8, 9, 10)),
                new Lotto(List.of(1, 2, 8, 9, 10, 11))
        );
        LottoPurchaseResult purchaseResult = new LottoPurchaseResult(purchasedLottos.size(), purchasedLottos);

        // when
        Map<Rank, Integer> rankResults = winningNumbers.getRankResults(purchaseResult, bonusNumber);

        // then
        assertThat(rankResults.get(Rank.FIRST)).isEqualTo(1);
        assertThat(rankResults.get(Rank.SECOND)).isEqualTo(1);
        assertThat(rankResults.get(Rank.THIRD)).isEqualTo(1);
        assertThat(rankResults.get(Rank.FOURTH)).isEqualTo(1);
        assertThat(rankResults.get(Rank.FIFTH)).isEqualTo(1);
        assertThat(rankResults.get(Rank.NOTHING)).isEqualTo(1);
    }

    @Test
    void 보너스_일치는_5개_일치일_때만_2등에_영향을_준다() {
        // given
        WinningNumbers winningNumbers = new WinningNumbers(List.of(1, 2, 3, 4, 5, 6));
        BonusNumber bonusNumber = new BonusNumber(7, winningNumbers);

        Lotto fiveWithBonus = new Lotto(List.of(1, 2, 3, 4, 5, 7));
        Lotto sixNumbers = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        Lotto fourWithBonus = new Lotto(List.of(1, 2, 3, 4, 7, 8));

        LottoPurchaseResult purchaseResult = new LottoPurchaseResult(
                3, List.of(fiveWithBonus, sixNumbers, fourWithBonus)
        );

        // when
        Map<Rank, Integer> rankResults = winningNumbers.getRankResults(purchaseResult, bonusNumber);

        // then
        assertThat(rankResults.get(Rank.SECOND)).isEqualTo(1);
        assertThat(rankResults.get(Rank.FIRST)).isEqualTo(1);
        assertThat(rankResults.get(Rank.FOURTH)).isEqualTo(1);
    }
}
