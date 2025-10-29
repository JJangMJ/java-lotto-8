package lotto.domain.generator;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.Test;

class RandomLottoNumberGeneratorTest {
    private final LottoNumberGenerator lottoNumberGenerator = new RandomLottoNumberGenerator();

    @Test
    void 생성된_로또의_번호는_항상_6개다() {
        List<Integer> numbers = lottoNumberGenerator.generate();

        assertThat(numbers).hasSize(6);
    }

    @Test
    void 생성된_로또의_번호는_오름차순으로_정렬된다() {
        List<Integer> numbers = lottoNumberGenerator.generate();

        assertThat(numbers).isSorted();
    }

    @Test
    void 생성된_로또의_번호는_중복이_없다() {
        List<Integer> numbers = lottoNumberGenerator.generate();

        assertThat(numbers).doesNotHaveDuplicates();
    }

    @Test
    void 생성된_로또의_번호는_모두_1부터_45사이에_포함된다() {
        List<Integer> numbers = lottoNumberGenerator.generate();

        assertThat(numbers).allMatch(number -> number >= 1 && number <= 45);
    }
}
