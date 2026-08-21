package dio.budgeting.application.output;

import dio.budgeting.domain.Category;

import java.math.BigDecimal;
import java.math.RoundingMode;

public record CategorySpendingOutput(String category, double totalSpent, long transactionCount) {
    public static CategorySpendingOutput from(Category category, long totalSpentInCents, long transactionCount) {
        return new CategorySpendingOutput(
                category.name(),
                BigDecimal.valueOf(totalSpentInCents, 2)
                        .setScale(2, RoundingMode.HALF_UP)
                        .doubleValue(),
                transactionCount);
    }
}
