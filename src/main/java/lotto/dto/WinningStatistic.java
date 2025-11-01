package lotto.dto;

import java.util.Map;
import lotto.domain.Rank;

public record WinningStatistic(Map<Rank, Integer> rankResults, double profitRate) {
}
