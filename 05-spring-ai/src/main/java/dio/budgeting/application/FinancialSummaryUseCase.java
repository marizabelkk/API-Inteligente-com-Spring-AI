package dio.budgeting.application;

import dio.budgeting.application.output.CategorySpendingOutput;
import dio.budgeting.application.output.FinancialBalanceOutput;
import dio.budgeting.domain.Category;
import dio.budgeting.domain.Transaction;
import dio.budgeting.domain.TransactionRepository;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class FinancialSummaryUseCase {
    private final TransactionRepository transactionRepository;

    public FinancialSummaryUseCase(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Tool(name = "get-total-balance", description = "Consulta o total gasto e o saldo financeiro atual considerando os gastos registrados")
    public FinancialBalanceOutput getTotalBalance() {
        var transactions = transactionRepository.findAll();
        var totalSpent = transactions.stream().mapToLong(Transaction::getAmount).sum();

        return FinancialBalanceOutput.from(totalSpent, transactions.size());
    }

    @Tool(name = "get-category-spending-summary", description = "Consulta quanto foi gasto em uma categoria financeira")
    public CategorySpendingOutput getCategorySpendingSummary(
            @ToolParam(description = "Categoria financeira desejada") Category category) {
        var transactions = transactionRepository.findAllByCategory(category);
        var totalSpent = transactions.stream().mapToLong(Transaction::getAmount).sum();

        return CategorySpendingOutput.from(category, totalSpent, transactions.size());
    }

    public List<CategorySpendingOutput> getSpendingSummaryByCategory() {
        return Arrays.stream(Category.values())
                .map(this::getCategorySpendingSummary)
                .toList();
    }
}
