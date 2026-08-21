package dio.budgeting.application.output;

import java.math.BigDecimal;
import java.math.RoundingMode;

public record FinancialBalanceOutput(double totalSpent, double balance, long transactionCount) {
    public static FinancialBalanceOutput from(long totalSpentInCents, long transactionCount) {
        var totalSpent = BigDecimal.valueOf(totalSpentInCents, 2)
                .setScale(2, RoundingMode.HALF_UP)
                .doubleValue();

        return new FinancialBalanceOutput(totalSpent, -totalSpent, transactionCount);
    }
}
