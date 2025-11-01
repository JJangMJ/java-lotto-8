package lotto.domain;

import java.util.Map;

public record WinningStatistic(Map<Rank, Integer> rankResults, double profitRate) {
}
